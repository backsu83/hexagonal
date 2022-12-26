package com.ebaykorea.payback.core.factory.impl;

import com.ebaykorea.payback.core.domain.constant.CashbackPayType;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.CashbackUnit;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.ChargePayCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.ChargePayCashbackPolicy;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.CashbackPolicy;
import com.ebaykorea.payback.core.domain.entity.order.ItemSnapshot;
import com.ebaykorea.payback.core.domain.entity.payment.Payment;
import com.ebaykorea.payback.core.domain.entity.reward.RewardBackendCashbackPolicy;
import com.ebaykorea.payback.core.domain.entity.reward.RewardCashbackPolicy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

import static com.ebaykorea.payback.util.PaybackDecimals.orZero;

@Component
public class ChargePayCashbackCreator {

  public CashbackUnit create(
      final Instant useEnableDate,
      final Payment payment,
      final ItemSnapshot itemSnapshot,
      final BigDecimal cashbackAmount,
      final BigDecimal basisAmount,
      final RewardCashbackPolicy rewardCashbackPolicy,
      final RewardBackendCashbackPolicy rewardBackendCashbackPolicy) {
    return new ChargePayCashback(
        itemSnapshot.getItemNo(),
        itemSnapshot.toShopType(),
        cashbackAmount,
        basisAmount,
        useEnableDate,
        rewardCashbackPolicy.getAutoChargeClubAmount(),
        rewardCashbackPolicy.getAutoChargeAmount(),
        payment.isChargePayment(),
        createCashbackPolicy(rewardCashbackPolicy, rewardBackendCashbackPolicy)
    );
  }

  private CashbackPolicy createCashbackPolicy(
      final RewardCashbackPolicy rewardCashbackPolicy,
      final RewardBackendCashbackPolicy rewardBackendCashbackPolicy) {
    return new ChargePayCashbackPolicy(
        rewardCashbackPolicy.getCashbackSeq(),
        rewardCashbackPolicy.getCashbackCd(),
        rewardCashbackPolicy.getCashbackTitle(),
        CashbackPayType.FixRate.getCode(),
        rewardCashbackPolicy.getPayType(),
        rewardCashbackPolicy.getPayRate(),
        rewardCashbackPolicy.getPayMaxMoney(),
        orZero(rewardBackendCashbackPolicy.getChargePayRewardRate()),
        orZero(BigDecimal.valueOf(rewardBackendCashbackPolicy.getChargePayRewardMaxMoney())),
        orZero(rewardBackendCashbackPolicy.getChargePayRewardClubRate()),
        orZero(BigDecimal.valueOf(rewardBackendCashbackPolicy.getChargePayRewardClubMaxMoney()))
    );
  }
}
