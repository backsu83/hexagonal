package com.ebaykorea.payback.core.domain.entity.order;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class OrderItemAddition {
  long additionNo;

  /** 추가 구성 주문 수량 */
  Integer quantity;

  /** 추가 구성 추가 금액 */
  BigDecimal sellPrice;
}
