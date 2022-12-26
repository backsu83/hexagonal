package com.ebaykorea.payback.infrastructure.gateway.client.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderUnitDiscountDto {
  /** 상품 할인 Snapshot Key */
  private String snapshotKey;

  /** 상품 할인 금액 */
  private BigDecimal discountAmount;
}
