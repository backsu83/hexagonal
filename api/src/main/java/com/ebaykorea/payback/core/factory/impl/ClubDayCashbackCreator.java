package com.ebaykorea.payback.core.factory.impl;

import com.ebaykorea.payback.core.domain.constant.CashbackPayType;
import com.ebaykorea.payback.core.domain.entity.cashback.member.Member;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.CashbackUnit;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.ClubDayCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.ClubDayCashbackPolicy;
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
public class ClubDayCashbackCreator {

  public CashbackUnit create(
      final Instant useEnableDate,
      final Member member,
      final Payment payment,
      final ItemSnapshot itemSnapshot,
      final BigDecimal cashbackAmount,
      final BigDecimal basisAmount,
      final RewardCashbackPolicy rewardCashbackPolicy,
      final RewardBackendCashbackPolicy rewardBackendCashbackPolicy
  ) {
    return new ClubDayCashback(
        itemSnapshot.getItemNo(),
        itemSnapshot.toShopType(),
        cashbackAmount,
        basisAmount,
        useEnableDate,
        payment.isSmilePayPayment(),
        member.isSmileClubMember(),
        createCashbackPolicy(rewardCashbackPolicy, rewardBackendCashbackPolicy)
    );
  }

  private CashbackPolicy createCashbackPolicy(
      final RewardCashbackPolicy rewardCashbackPolicy,
      final RewardBackendCashbackPolicy rewardBackendCashbackPolicy) {
    return new ClubDayCashbackPolicy(
        rewardCashbackPolicy.getCashbackSeq(),
        rewardCashbackPolicy.getCashbackCd(),
        rewardCashbackPolicy.getCashbackTitle(),
        CashbackPayType.FixRate.getCode(),
        rewardCashbackPolicy.getPayType(),
        rewardCashbackPolicy.getPayRate(),
        rewardCashbackPolicy.getPayMaxMoney(),
        orZero(rewardBackendCashbackPolicy.getClubDayPayRate()),
        orZero(BigDecimal.valueOf(rewardBackendCashbackPolicy.getClubDaySaveMaxMoney(), 0))
    );
  }
}
