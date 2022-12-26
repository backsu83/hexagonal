package com.ebaykorea.payback.core.domain.entity.order;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class OrderUnitCoupon {
  /** 쿠폰 snapshotKey */
  String snapshotKey;

  /** 쿠폰 금액 */
  BigDecimal couponAmount;
}
