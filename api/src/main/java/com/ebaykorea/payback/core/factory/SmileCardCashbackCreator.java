package com.ebaykorea.payback.core.factory;

import static com.ebaykorea.payback.core.exception.PaybackExceptionCode.DOMAIN_ENTITY_002;

import com.ebaykorea.payback.core.domain.entity.cashback.smilecard.SmileCardCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.smilecard.T2T3SmileCardCashback;
import com.ebaykorea.payback.core.domain.entity.order.ItemSnapshots;
import com.ebaykorea.payback.core.domain.entity.order.KeyMap;
import com.ebaykorea.payback.core.domain.entity.order.Order;
import com.ebaykorea.payback.core.domain.entity.payment.Payment;
import com.ebaykorea.payback.core.domain.entity.reward.RewardCashbackPolicies;
import com.ebaykorea.payback.core.exception.PaybackException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SmileCardCashbackCreator {

  public SmileCardCashback createSmileCardCashback(
      final KeyMap keyMap,
      final Order order,
      final Payment payment,
      final ItemSnapshots itemSnapshots,
      final RewardCashbackPolicies rewardCashbackPolicies
  ) {
    final var t2t3Cashbacks = createT2T3SmileCardCashbacks(keyMap, order, payment, itemSnapshots, rewardCashbackPolicies);

    final var smileCardCashbackAmount = payment.isT1T2T3SmileCard() ? rewardCashbackPolicies.getNewSmileCardCashbackAmount() : rewardCashbackPolicies.getSmileCardCashbackAmount();

    return SmileCardCashback.of(
        smileCardCashbackAmount,
        payment.isSmileCard(),
        payment.isFreeInstallment(),
        t2t3Cashbacks
    );
  }

  private List<T2T3SmileCardCashback> createT2T3SmileCardCashbacks(
      final KeyMap keyMap,
      final Order order,
      final Payment payment,
      final ItemSnapshots itemSnapshots,
      final RewardCashbackPolicies rewardCashbackPolicies
  ) {
    return rewardCashbackPolicies.getSmileCardCashbackPolicyMap().entrySet().stream()
        .map(entry -> {
          final var orderUnitKey = keyMap.findByOrderNo(entry.getKey())
              .orElseThrow(() -> new PaybackException(DOMAIN_ENTITY_002, "orderUnitKey"));
          final var orderUnit = order.findOrderUnitBy(orderUnitKey.getOrderUnitKey())
              .orElseThrow(() -> new PaybackException(DOMAIN_ENTITY_002, "orderUnit"));
          final var itemSnapshot = itemSnapshots.findBy(orderUnit.getItemSnapshotKey())
              .orElseThrow(() -> new PaybackException(DOMAIN_ENTITY_002, "itemSnapshot"));

          return T2T3SmileCardCashback.of(
              entry.getValue().getPolicyKey(),
              itemSnapshot.toShopType(),
              entry.getValue().getCashbackAmount(),
              orderUnit.orderUnitPriceExcludingCouponPrice(),
              payment.toSmileCardType(),
              payment.isT2T3SmileCard(),
              payment.isFreeInstallment()
          );
        })
        .collect(Collectors.toUnmodifiableList());
  }
}
