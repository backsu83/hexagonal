package com.ebaykorea.payback.infrastructure.gateway.client.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemAdditionDto {
  private long additionNo;

  /** 추가 구성 주문 수량 */
  private Integer quantity;

  /** 추가 구성 추가 금액 */
  private BigDecimal sellPrice;
}
