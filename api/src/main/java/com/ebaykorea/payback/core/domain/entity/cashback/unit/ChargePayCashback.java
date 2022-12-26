package com.ebaykorea.payback.core.domain.entity.cashback.unit;

import com.ebaykorea.payback.core.domain.constant.CashbackType;
import com.ebaykorea.payback.core.domain.constant.ShopType;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.CashbackPolicy;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChargePayCashback extends CashbackUnit {

  private final BigDecimal clubAmount;
  private final BigDecimal nonClubAmount;

  public ChargePayCashback(
      final String itemNo,
      final ShopType shopType,
      final BigDecimal amount,
      final BigDecimal basisAmount,
      final Instant useEnableDate,
      final BigDecimal clubAmount,
      final BigDecimal nonClubAmount,
      final boolean isChargePay,
      final CashbackPolicy cashbackPolicy) {
    super(
        itemNo,
        shopType,
        amount,
        basisAmount,
        useEnableDate,
        CashbackApplyStrategy.chargePayCashbackStrategy(amount, isChargePay),
        cashbackPolicy);

    this.clubAmount = clubAmount;
    this.nonClubAmount = nonClubAmount;
  }

  @Override
  public CashbackType getCashbackType() {
    return CashbackType.ChargePay;
  }

  @Override
  public BigDecimal getClubAmount() {
    return clubAmount;
  }

  @Override
  public BigDecimal getNonClubAmount() {
    return nonClubAmount;
  }

}
