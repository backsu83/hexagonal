package com.ebaykorea.payback.scheduler.crontab;

import com.ebaykorea.payback.scheduler.service.PaybackBatchService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaybackScheduler {

  private final PaybackBatchService paybackSchedulerService;

  @Scheduled(initialDelay = 10 , fixedDelay = 10 , timeUnit = TimeUnit.MINUTES)
  public void init() {
    log.info("scheduler start ...");
    paybackSchedulerService.updateRecords();
  }

}
