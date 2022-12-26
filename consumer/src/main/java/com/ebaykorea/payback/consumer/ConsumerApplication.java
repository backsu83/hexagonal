package com.ebaykorea.payback.consumer;

import com.ebaykorea.saturn.moa.EnableMoA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableMoA
@EnableFeignClients
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConsumerApplication.class, args);
  }

}
