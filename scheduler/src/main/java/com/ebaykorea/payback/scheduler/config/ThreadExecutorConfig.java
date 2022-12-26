package com.ebaykorea.payback.scheduler.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadExecutorConfig {

  @Bean
  public ExecutorService taskExecutor() {
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
        .setNameFormat("scheduler-task-unit-%d")
        .build();
    ThreadPoolExecutor threadUnit = new ThreadPoolExecutor(
        10,
        20,
        6000, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(1000)
    );
    threadUnit.setThreadFactory(namedThreadFactory);
    return threadUnit;
  }

}
