package com.ebaykorea.payback.core.domain.entity.cashback.unit;

import java.math.BigDecimal;
import static com.ebaykorea.payback.util.PaybackDecimals.isGreaterThanZero;

@FunctionalInterface
public interface CashbackApplyStrategy {
  boolean isApply();

  static CashbackApplyStrategy defaultCashbackStrategy(final BigDecimal amount) {
    return () -> isGreaterThanZero(amount);
  }

  static CashbackApplyStrategy cashbackAvailableStrategy(final BigDecimal amount, final boolean isSmilePay) {
    return () -> isGreaterThanZero(amount) && isSmilePay;
  }

  static CashbackApplyStrategy chargePayCashbackStrategy(final BigDecimal amount, final boolean isChargePay) {
    return () -> isGreaterThanZero(amount) && isChargePay;
  }

  static CashbackApplyStrategy clubDayCashbackStrategy(final BigDecimal amount, final boolean isSmilePay, final boolean isClubMember) {
    return () -> isGreaterThanZero(amount) && isSmilePay && isClubMember;
  }

  //저장 대상이 아님
  static CashbackApplyStrategy notForSaveStrategy() {
    return () -> false;
  }
}
