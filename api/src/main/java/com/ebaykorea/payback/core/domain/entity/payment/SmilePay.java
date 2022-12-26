package com.ebaykorea.payback.core.domain.entity.payment;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SmilePay {
  boolean isFreeInstallment;
}
