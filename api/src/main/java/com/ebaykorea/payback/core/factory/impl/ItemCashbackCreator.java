package com.ebaykorea.payback.core.factory.impl;

import com.ebaykorea.payback.core.domain.entity.cashback.unit.CashbackUnit;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.ItemCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.ItemCashbackPolicy;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.CashbackPolicy;
import com.ebaykorea.payback.core.domain.entity.order.ItemSnapshot;
import com.ebaykorea.payback.core.domain.entity.payment.Payment;
import com.ebaykorea.payback.core.domain.entity.reward.RewardCashbackPolicy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

@Component
public class ItemCashbackCreator {

  public CashbackUnit createItemCashback(
      Instant useEnableDate,
      Payment payment,
      ItemSnapshot itemSnapshot,
      BigDecimal cashbackAmount,
      BigDecimal basisAmount,
      RewardCashbackPolicy rewardCashbackPolicy
  ) {
    return new ItemCashback(
        itemSnapshot.getItemNo(),
        itemSnapshot.toShopType(),
        cashbackAmount,
        basisAmount,
        useEnableDate,
        payment.isSmilePayPayment(),
        createCashbackPolicy(rewardCashbackPolicy)
    );
  }

  private CashbackPolicy createCashbackPolicy(final RewardCashbackPolicy rewardCashbackPolicy) {
    return new ItemCashbackPolicy(
        rewardCashbackPolicy.getCashbackSeq(),
        rewardCashbackPolicy.getCashbackCd(),
        rewardCashbackPolicy.getCashbackTitle(),
        null,
        rewardCashbackPolicy.getPayType(),
        rewardCashbackPolicy.getPayRate(),
        rewardCashbackPolicy.getPayMaxMoney()
    );
  }
}
