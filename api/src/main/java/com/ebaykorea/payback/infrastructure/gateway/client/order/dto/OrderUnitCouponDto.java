package com.ebaykorea.payback.infrastructure.gateway.client.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderUnitCouponDto {
  /** 쿠폰 snapshotKey */
  private String snapshotKey;

  /** 쿠폰 금액 */
  private BigDecimal couponAmount;
}
