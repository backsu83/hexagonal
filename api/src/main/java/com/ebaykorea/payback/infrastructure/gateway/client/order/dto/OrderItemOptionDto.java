package com.ebaykorea.payback.infrastructure.gateway.client.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemOptionDto {
  private long optionNo;
  /** 옵션 추가 금액 */
  private BigDecimal sellPrice;
}
