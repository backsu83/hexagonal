package com.ebaykorea.payback.scheduler.service;

import static com.ebaykorea.payback.scheduler.service.entity.ProcessType.COMPLETED;
import static com.ebaykorea.payback.scheduler.service.entity.ProcessType.FAIL;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.ebaykorea.payback.scheduler.repository.CashbackOrderBatchRepository;
import com.ebaykorea.payback.scheduler.client.PaybackApiClient;
import com.ebaykorea.payback.scheduler.client.dto.PaybackRequestDto;
import com.ebaykorea.payback.scheduler.client.dto.PaybackResponseDto;
import com.ebaykorea.payback.scheduler.service.mapper.PaybackBatchRecordMapper;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaybackBatchService {
  public static final String updOprt = "paybackScheduler";
  private final CashbackOrderBatchRepository cashbackOrderBatchRepository;
  private final PaybackBatchRecordMapper paybackBatchRecordMapper;
  private final PaybackApiClient paybackApiClient;
  private final ExecutorService taskExecutor;

  public void updateRecords() {

    final List<CompletableFuture<PaybackResponseDto>> paybacksFuture = Lists.newArrayList();
    final var records = cashbackOrderBatchRepository.findNoCompleted()
        .stream()
        .filter(f-> Objects.nonNull(f))
        .map(paybackBatchRecordMapper::map)
        .collect(Collectors.toList());

    if(isEmpty(records)) {
      log.info("scheduler - cashback not found records");
      return;
    }

    records.stream()
        .filter(f-> f.getRetryCount() < 3)
        .forEach( unit-> {
      final var request = PaybackRequestDto.builder()
          .orderKey(unit.getOrderKey())
          .txKey(unit.getTxKey())
          .build();

      paybacksFuture.add(CompletableFuture
          .supplyAsync(() -> paybackApiClient.saveCashbacks(request), taskExecutor)
          .exceptionally(ex -> {
            fail(unit.getOrderKey() , unit.getTxKey(), unit.getRetryCount());
            log.error("scheduler - fail to payback api orderKey:{} / txKey:{} / error:{}",
                unit.getOrderKey(),
                unit.getTxKey(),
                ex.getLocalizedMessage());
            return null;
          }));
    });

    final var paybacks = CompletableFuture.allOf(
        paybacksFuture.toArray(new CompletableFuture[paybacksFuture.size()]))
        .thenApply(s -> paybacksFuture.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList())
        ).join();

    success(paybacks);
  }

  public void success(List<PaybackResponseDto> paybacks) {
    paybacks.stream()
        .filter(f->Objects.nonNull(f))
        .map(o->o.getData())
        .forEach(unit-> cashbackOrderBatchRepository.updateStatus(
            unit.getOrderKey() ,
            unit.getTxKey(),
            COMPLETED.name() ,
            0L, updOprt)
        );
  }

  public void fail(String orderKey , String txKey , long retryCount) {
    final var count = retryCount + 1L;
    cashbackOrderBatchRepository.updateStatus(orderKey , txKey, FAIL.name() , count, updOprt);
  }
}
