package com.ebaykorea.payback.core.factory;

import com.ebaykorea.payback.core.domain.entity.cashback.*;
import com.ebaykorea.payback.core.domain.entity.cashback.member.Member;
import com.ebaykorea.payback.core.domain.entity.order.*;
import com.ebaykorea.payback.core.domain.entity.payment.Payment;

import com.ebaykorea.payback.core.domain.entity.reward.RewardCashbackPolicies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayCashbackCreator {
  private final CashbackCreator cashbackCreator;
  private final SmileCardCashbackCreator smileCardCashbackCreator;

  public PayCashback create(
      final KeyMap keyMap,
      final Order order,
      final Member member,
      final Payment payment,
      final ItemSnapshots itemSnapshots,
      final RewardCashbackPolicies rewardCashbackPolicies
  ) {
    return PayCashback.of(
        keyMap.getPackNo(),
        order.getOrderDate(),
        member,
        cashbackCreator.createCashbacks(keyMap, order, member, payment, itemSnapshots, rewardCashbackPolicies),
        smileCardCashbackCreator.createSmileCardCashback(keyMap, order, payment, itemSnapshots, rewardCashbackPolicies)
    );
  }
}
