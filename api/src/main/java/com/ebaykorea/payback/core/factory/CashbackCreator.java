package com.ebaykorea.payback.core.factory;

import static com.ebaykorea.payback.core.exception.PaybackExceptionCode.DOMAIN_ENTITY_002;

import com.ebaykorea.payback.core.domain.entity.cashback.Cashback;
import com.ebaykorea.payback.core.domain.entity.cashback.member.Member;
import com.ebaykorea.payback.core.domain.entity.order.ItemSnapshots;
import com.ebaykorea.payback.core.domain.entity.order.KeyMap;
import com.ebaykorea.payback.core.domain.entity.order.Order;
import com.ebaykorea.payback.core.domain.entity.payment.Payment;
import com.ebaykorea.payback.core.domain.entity.reward.RewardCashbackPolicies;
import com.ebaykorea.payback.core.exception.PaybackException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CashbackCreator {

  private final CashbackUnitFactory cashbackUnitFactory;

  public List<Cashback> createCashbacks(
      final KeyMap keyMap,
      final Order order,
      final Member member,
      final Payment payment,
      final ItemSnapshots itemSnapshots,
      final RewardCashbackPolicies rewardCashbackPolicies
  ) {
    return order.getOrderUnits().stream()
        .map(orderUnit -> {
          final var orderUnitKey = keyMap.findBy(orderUnit.getOrderUnitKey())
              .orElseThrow(() -> new PaybackException(DOMAIN_ENTITY_002 , "orderUnitKey"));
          final var itemSnapshot = itemSnapshots.findBy(orderUnit.getItemSnapshotKey())
              .orElseThrow(() -> new PaybackException(DOMAIN_ENTITY_002 , "itemSnapshot"));
          final var bundleDiscountPrice = order.getBundleDiscountPrice(orderUnit.getOrderUnitKey());

          //주문단위(주문번호)별 캐시백 목록
          final var cashbackUnits = cashbackUnitFactory.createCashbackUnits(
              order.getOrderDate(),
              orderUnitKey,
              orderUnit,
              member,
              payment,
              itemSnapshot,
              bundleDiscountPrice,
              rewardCashbackPolicies);

          return Cashback.of(orderUnitKey.getBuyOrderNo(), cashbackUnits);
        })
        .collect(Collectors.toUnmodifiableList());
  }
}
