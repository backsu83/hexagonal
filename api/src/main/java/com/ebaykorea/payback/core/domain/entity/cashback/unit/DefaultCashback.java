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
public class DefaultCashback extends CashbackUnit {

  private CashbackType type;

  public DefaultCashback(
      final String itemNo,
      final CashbackType type,
      final ShopType shopType,
      final BigDecimal amount,
      final BigDecimal basisAmount,
      final Instant useEnableDate,
      final CashbackPolicy cashbackPolicy
  ) {
    super(
        itemNo,
        shopType,
        amount,
        basisAmount,
        useEnableDate,
        CashbackApplyStrategy.notForSaveStrategy(),
        cashbackPolicy);
    this.type = type;
  }

  @Override
  public CashbackType getCashbackType() {
    return this.type;
  }
}
