package com.ebaykorea.payback.core.factory.impl;

import com.ebaykorea.payback.core.domain.constant.CashbackPayType;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.CashbackUnit;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.SmilePayCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.CashbackPolicy;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.SmilePayCashbackPolicy;
import com.ebaykorea.payback.core.domain.entity.order.ItemSnapshot;
import com.ebaykorea.payback.core.domain.entity.payment.Payment;
import com.ebaykorea.payback.core.domain.entity.reward.RewardCashbackPolicy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

@Component
public class SmilePayCashbackCreator {

  public CashbackUnit create(
      final Instant useEnableDate,
      final Payment payment,
      final ItemSnapshot itemSnapshot,
      final BigDecimal cashbackAmount,
      final BigDecimal basisAmount,
      final RewardCashbackPolicy rewardCashbackPolicy
  ) {
    return new SmilePayCashback(
        itemSnapshot.getItemNo(),
        itemSnapshot.toShopType(),
        cashbackAmount,
        basisAmount,
        useEnableDate,
        payment.isSmilePayPayment(),
        createCashbackPolicy(rewardCashbackPolicy),
        rewardCashbackPolicy.getClubCashbackAmount()
    );
  }

  private CashbackPolicy createCashbackPolicy(final RewardCashbackPolicy rewardCashbackPolicy) {
    return new SmilePayCashbackPolicy(
        rewardCashbackPolicy.getCashbackSeq(),
        rewardCashbackPolicy.getCashbackCd(),
        rewardCashbackPolicy.getCashbackTitle(),
        CashbackPayType.FixRate.getCode(),
        rewardCashbackPolicy.getPayType(),
        rewardCashbackPolicy.getPayRate(),
        rewardCashbackPolicy.getPayMaxMoney()
    );
  }

}
