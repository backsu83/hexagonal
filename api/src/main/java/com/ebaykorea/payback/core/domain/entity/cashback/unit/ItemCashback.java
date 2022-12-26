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
public class ItemCashback extends CashbackUnit {

  public ItemCashback(
      final String itemNo,
      final ShopType shopType,
      final BigDecimal amount, //cashback_order와 detail.ITEM_AMOUNT 저장하는 금액 소스가 다른데 통일할수 없는지 확인 필요
      final BigDecimal basisAmount,
      final Instant useEnableDate,
      final Boolean isSmilePay,
      final CashbackPolicy cashbackPolicy
      ) {
    super(
        itemNo,
        shopType,
        amount,
        basisAmount,
        useEnableDate,
        CashbackApplyStrategy.cashbackAvailableStrategy(amount, isSmilePay),
        cashbackPolicy);
  }

  @Override
  public CashbackType getCashbackType() {
    return CashbackType.Item;
  }
}
