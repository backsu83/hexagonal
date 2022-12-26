package com.ebaykorea.payback;

import com.ebaykorea.payback.api.CashbackController;
import com.ebaykorea.payback.api.dto.CashbackResponseDto;
import com.ebaykorea.payback.api.dto.SaveCashbackRequestDto;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.*;
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderEntity;
import com.ebaykorea.payback.infrastructure.query.data.SavedCashbackQueryResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.specto.hoverfly.junit5.HoverflyExtension;
import io.specto.hoverfly.junit5.api.HoverflyConfig;
import io.specto.hoverfly.junit5.api.HoverflySimulate;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static com.ebaykorea.payback.core.domain.constant.ResponseMessageType.CASHBACK_CREATED;
import static com.ebaykorea.payback.support.TestConstants.*;
import static io.specto.hoverfly.junit5.api.HoverflySimulate.SourceType.FILE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@HoverflySimulate(
    config = @HoverflyConfig(
        proxyLocalHost = true
    ),
    source = @HoverflySimulate.Source(value = HOVERFLY_ROOT + HOVERFLY_FILE, type = FILE), //외부 api 결과를 resource/hoverfly/hoverfly-stubs에 정의 되어 있는 값들을 리턴하도록
    enableAutoCapture = true)
@ExtendWith(HoverflyExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaybackComponentTest {

  @Autowired
  CashbackController cashbackController;
  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  CashbackOrderDetailRepository cashbackOrderDetailRepository;
  @MockBean
  CashbackOrderMemberRepository cashbackOrderMemberRepository;
  @MockBean
  CashbackOrderPolicyRepository cashbackOrderPolicyRepository;
  @MockBean
  CashbackOrderRepository cashbackOrderRepository;
  @MockBean
  SmilecardCashbackOrderRepository smilecardCashbackOrderRepository;
  @MockBean
  SmilecardT2T3CashbackRepository smilecardT2T3CashbackRepository;

  @Value("classpath:" + CASHBACK_QUERY_FILE)
  Resource resourceFile;

  private static final String txKey = "txKey";
  private static final String orderKey = "orderKey";

  @Test
  @Transactional
  @DisplayName("캐시백 적립이 성공한다")
  void saveCashback() {

    doNothing().when(cashbackOrderDetailRepository).save(any());
    doNothing().when(cashbackOrderMemberRepository).save(any());
    doNothing().when(cashbackOrderPolicyRepository).save(any());
    doNothing().when(cashbackOrderRepository).save(any());
    doNothing().when(smilecardCashbackOrderRepository).save(any());
    doNothing().when(smilecardT2T3CashbackRepository).save(any());

    final var result = cashbackController.saveCashbacks(new SaveCashbackRequestDto(txKey, orderKey));

    assertEquals(CASHBACK_CREATED.name(), result.getMessage());
    assertEquals(0, result.getCode());
    assertEquals(CashbackResponseDto.of(txKey, orderKey), result.getData());
  }

  @Test
  @DisplayName("캐시백 조회가 성공한다")
  void getCashbacks() {
    when(cashbackOrderRepository.findByPackNo(anyLong()))
        .thenReturn(List.of(
            CashbackOrderEntity.builder()
                .cashbackType("I")
                .amount(BigDecimal.valueOf(2295L))
                .itemNo("1100439676")
                .buyerNo("132870993")
                .tradeStatus("30")
                .smileClubYn("Y")
                .useEnableDt(Timestamp.valueOf("2023-01-04 00:00:00.0"))
                .build()));

    final var result = cashbackController.getSavedCashbacks(null, txKey, orderKey);
    final var expected = getExpectResult();

    assertEquals(expected, result);
  }

  private SavedCashbackQueryResult getExpectResult() {
    try {
      return objectMapper.readValue(new ClassPathResource(CASHBACK_QUERY_FILE).getFile(), SavedCashbackQueryResult.class);
    } catch (Exception ex) {
      return null;
    }
  }

}
