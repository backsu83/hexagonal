package com.ebaykorea.payback.core.domain.entity.order;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderUnitKey {
  String orderUnitKey;
  long buyOrderNo;
  long contrNo;
}
