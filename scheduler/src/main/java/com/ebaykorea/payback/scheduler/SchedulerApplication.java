package com.ebaykorea.payback.scheduler;

import com.ebaykorea.saturn.datasource.EnableSaturnDataSource;
import com.ebaykorea.saturn.moa.EnableMoA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableMoA
@EnableFeignClients
@EnableScheduling
@EnableSaturnDataSource
@SpringBootApplication
public class SchedulerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SchedulerApplication.class, args);
  }
}
