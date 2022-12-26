package com.ebaykorea.payback.core.domain.entity.order;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class BundleDiscount {
  /** 복수 구매 할인 Snapshot 키 */
  String snapshotKey;

  /** orderUnit 별로 적용된 bundle Discount 상세 */
  List<BundleDiscountUnit> bundleDiscountUnits;
}
