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
public class SellerCashback extends CashbackUnit {

  public SellerCashback(
      final String itemNo,
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
        CashbackApplyStrategy.defaultCashbackStrategy(amount),
        cashbackPolicy);
  }

  @Override
  public CashbackType getCashbackType() {
    return CashbackType.Seller;
  }
}
