package com.ebaykorea.payback.consumer.domain;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCompletedEvent {

  /** 주문 Key */
  @NotEmpty(message = "orderKey is empty")
  String orderKey;

  /** 거래 Key */
  @NotEmpty(message = "txKey is empty")
  String txKey;

}
