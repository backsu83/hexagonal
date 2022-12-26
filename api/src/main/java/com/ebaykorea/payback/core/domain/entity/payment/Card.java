package com.ebaykorea.payback.core.domain.entity.payment;

import com.ebaykorea.payback.core.domain.constant.InstallmentType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Card {
  InstallmentType freeInstallmentType;
  boolean isManualPayment;

  // 무이자할부 여부
  public boolean isFreeInstallment() {
    return freeInstallmentType == InstallmentType.InterestFree
        || freeInstallmentType == InstallmentType.InterestOnGmkt;
  }
}
