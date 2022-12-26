package com.ebaykorea.payback.core.domain.entity.order;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class BundleDiscountUnit {
  /** 복수 구매 할인 적용된 주문 단위 키 */
  String orderUnitKey;

  /** 복수 구매 할인 금액 */
  BigDecimal discountAmount;
}
