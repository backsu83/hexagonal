package com.ebaykorea.payback.core.domain.entity.cashback.unit.policy;

import com.ebaykorea.payback.core.domain.constant.CashbackType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClubDayCashbackPolicy extends CashbackPolicy {
  private final BigDecimal clubDayMaxSaveMoney;
  private final BigDecimal clubDayMaxSaveRate;

  public ClubDayCashbackPolicy(
      final long policyNo,
      final CashbackType type,
      final String name,
      final String subType,
      final String payType,
      final BigDecimal saveRate,
      final BigDecimal maxLimitMoney,
      final BigDecimal clubDayMaxSaveMoney,
      final BigDecimal clubDayMaxSaveRate
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
    this.clubDayMaxSaveMoney = clubDayMaxSaveMoney;
    this.clubDayMaxSaveRate = clubDayMaxSaveRate;
  }
}
