package com.ebaykorea.payback.core.domain.entity.order;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class OrderItemOption {
  long optionNo;
  /** 옵션 추가 금액 */
  BigDecimal sellPrice;
}
