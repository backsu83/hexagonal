package com.ebaykorea.payback.core.domain.entity.cashback.unit.policy;

import com.ebaykorea.payback.core.domain.constant.CashbackType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChargePayCashbackPolicy extends CashbackPolicy {

  private final BigDecimal chargePaySaveRate;
  private final BigDecimal chargePayClubSaveRate;
  private final BigDecimal chargePayMaxMoney;
  private final BigDecimal chargePayClubMaxMoney;

  public ChargePayCashbackPolicy(
      final long policyNo,
      final CashbackType type,
      final String name,
      final String subType,
      final String payType,
      final BigDecimal saveRate,
      final BigDecimal maxLimitMoney,
      final BigDecimal chargePaySaveRate,
      final BigDecimal chargePayClubSaveRate,
      final BigDecimal chargePayMaxMoney,
      final BigDecimal chargePayClubMaxMoney
  ) {
    super(
        policyNo,
        type,
        name,
        subType,
        payType,
        saveRate,
        maxLimitMoney
    );
    this.chargePaySaveRate = chargePaySaveRate;
    this.chargePayClubSaveRate = chargePayClubSaveRate;
    this.chargePayMaxMoney = chargePayMaxMoney;
    this.chargePayClubMaxMoney = chargePayClubMaxMoney;
  }
}
