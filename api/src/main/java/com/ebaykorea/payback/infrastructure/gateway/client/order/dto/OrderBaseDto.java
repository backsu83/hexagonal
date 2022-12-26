package com.ebaykorea.payback.infrastructure.gateway.client.order.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class OrderBaseDto {
  /** 주문 일자 */
  private Instant orderDate;
}
