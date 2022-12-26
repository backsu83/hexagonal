package com.ebaykorea.payback.core.domain.entity.order;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class OrderUnitDiscount {
  /** 상품 할인 Snapshot Key */
  String snapshotKey;

  /** 상품 할인 금액 */
  BigDecimal discountAmount;
}
