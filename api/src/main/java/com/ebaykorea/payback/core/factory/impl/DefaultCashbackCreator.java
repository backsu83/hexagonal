package com.ebaykorea.payback.core.factory.impl;

import com.ebaykorea.payback.core.domain.entity.cashback.unit.CashbackUnit;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.DefaultCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.DefaultCashbackPolicy;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.CashbackPolicy;
import com.ebaykorea.payback.core.domain.entity.order.ItemSnapshot;
import com.ebaykorea.payback.core.domain.entity.reward.RewardCashbackPolicy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

@Component
public class DefaultCashbackCreator {

  public CashbackUnit create(
      Instant useEnableDate,
      ItemSnapshot itemSnapshot,
      BigDecimal cashbackAmount,
      BigDecimal basisAmount,
      RewardCashbackPolicy rewardCashbackPolicy) {
    return new DefaultCashback(
        itemSnapshot.getItemNo(),
        rewardCashbackPolicy.getCashbackCd(),
        itemSnapshot.toShopType(),
        cashbackAmount,
        basisAmount,
        useEnableDate,
        createCashbackPolicy(rewardCashbackPolicy)
    );
  }

  private CashbackPolicy createCashbackPolicy(final RewardCashbackPolicy rewardCashbackPolicy) {
    return new DefaultCashbackPolicy(
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
