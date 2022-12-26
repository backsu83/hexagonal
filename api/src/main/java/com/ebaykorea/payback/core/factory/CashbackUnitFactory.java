package com.ebaykorea.payback.core.factory;

import static com.ebaykorea.payback.core.exception.PaybackExceptionCode.DOMAIN_ENTITY_010;
import static com.ebaykorea.payback.util.PaybackInstants.getDefaultEnableDate;

import com.ebaykorea.payback.core.domain.entity.cashback.member.Member;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.CashbackUnit;
import com.ebaykorea.payback.core.domain.entity.order.ItemSnapshot;
import com.ebaykorea.payback.core.domain.entity.order.OrderUnit;
import com.ebaykorea.payback.core.domain.entity.order.OrderUnitKey;
import com.ebaykorea.payback.core.domain.entity.payment.Payment;
import com.ebaykorea.payback.core.domain.entity.reward.RewardBackendCashbackPolicy;
import com.ebaykorea.payback.core.domain.entity.reward.RewardCashbackPolicies;
import com.ebaykorea.payback.core.domain.entity.reward.RewardCashbackPolicy;
import com.ebaykorea.payback.core.exception.PaybackException;
import com.ebaykorea.payback.core.factory.impl.ChargePayCashbackCreator;
import com.ebaykorea.payback.core.factory.impl.ClubDayCashbackCreator;
import com.ebaykorea.payback.core.factory.impl.DefaultCashbackCreator;
import com.ebaykorea.payback.core.factory.impl.ItemCashbackCreator;
import com.ebaykorea.payback.core.factory.impl.SellerCashbackCreator;
import com.ebaykorea.payback.core.factory.impl.SmilePayCashbackCreator;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashbackUnitFactory {
  private final SellerCashbackCreator sellerCashbackCreator;
  private final ItemCashbackCreator itemCashbackCreator;
  private final SmilePayCashbackCreator smilePayCashbackCreator;
  private final ChargePayCashbackCreator chargePayCashbackCreator;
  private final ClubDayCashbackCreator clubDayCashbackCreator;
  private final DefaultCashbackCreator defaultCashbackCreator;

  /**
   * 주문단위(주문번호)별 캐시백 목록을 가져옵니다
   */
  public List<CashbackUnit> createCashbackUnits(
      final Instant orderDate,
      final OrderUnitKey orderUnitKey,
      final OrderUnit orderUnit,
      final Member member,
      final Payment payment,
      final ItemSnapshot itemSnapshot,
      final BigDecimal bundleDiscountPrice,
      final RewardCashbackPolicies rewardCashbackPolicies
  ) {
    return Stream.of(
            Stream.of(createSellerCashbackUnit(orderDate, orderUnit, itemSnapshot, bundleDiscountPrice)),
            createOtherCashbackUnitStream(orderDate, orderUnitKey, orderUnit, member, payment, itemSnapshot, bundleDiscountPrice, rewardCashbackPolicies))
        .flatMap(s -> s)
        .collect(Collectors.toUnmodifiableList());
  }

  //판매자 캐시백
  CashbackUnit createSellerCashbackUnit(
      final Instant orderDate,
      final OrderUnit orderUnit,
      final ItemSnapshot itemSnapshot,
      final BigDecimal bundleDiscountPrice
  ) {
    return sellerCashbackCreator.create(
        getDefaultEnableDate(orderDate),
        itemSnapshot,
        orderUnit.orderUnitPrice(bundleDiscountPrice, itemSnapshot.getBuyerMileageRate()),
        orderUnit.orderUnitPrice(bundleDiscountPrice)
    );
  }

  Stream<CashbackUnit> createOtherCashbackUnitStream(
      final Instant orderDate,
      final OrderUnitKey orderUnitKey,
      final OrderUnit orderUnit,
      final Member member,
      final Payment payment,
      final ItemSnapshot itemSnapshot,
      final BigDecimal bundleDiscountPrice,
      final RewardCashbackPolicies rewardCashbackPolicies) {

    return rewardCashbackPolicies
        .findRewardCashbackPolicies(orderUnitKey.getBuyOrderNo()) //주문 단위에 해당하는 캐시백 정책만을 가져옵니다
        .stream()
        .map(policy -> {
              final var useEnableDate = rewardCashbackPolicies.toUseEnableDate(orderDate);
              final var cashbackAmount = rewardCashbackPolicies.getCashbackAmount(policy.getPolicyKey(), policy.getCashbackCd());
              final var basisAmount = orderUnit.orderUnitPrice(bundleDiscountPrice);

              final var backendCashbackPolicy = rewardCashbackPolicies.findBackendRewardCashbackPolicy(policy.getPolicyKey())
                  .orElseThrow(() -> new PaybackException(DOMAIN_ENTITY_010, "backendCashbackPolicy"));

              return createCashbackUnit(
                  useEnableDate,
                  member,
                  payment,
                  itemSnapshot,
                  cashbackAmount,
                  basisAmount,
                  policy,
                  backendCashbackPolicy
              );
            }
        );
  }

  CashbackUnit createCashbackUnit(
      final Instant useEnableDate,
      final Member member,
      final Payment payment,
      final ItemSnapshot itemSnapshot,
      final BigDecimal cashbackAmount,
      final BigDecimal basisAmount,
      final RewardCashbackPolicy rewardCashbackPolicy,
      final RewardBackendCashbackPolicy backendCashbackPolicy) {

    switch (rewardCashbackPolicy.getCashbackCd()) {
      case Item:
        return itemCashbackCreator.createItemCashback(
            useEnableDate,
            payment,
            itemSnapshot,
            cashbackAmount,
            basisAmount,
            rewardCashbackPolicy
        );

      case SmilePay:
        return smilePayCashbackCreator.create(
            useEnableDate,
            payment,
            itemSnapshot,
            cashbackAmount,
            basisAmount,
            rewardCashbackPolicy
        );
      case ChargePay:
        return chargePayCashbackCreator.create(
            useEnableDate,
            payment,
            itemSnapshot,
            cashbackAmount,
            basisAmount,
            rewardCashbackPolicy,
            backendCashbackPolicy
        );
      case ClubDay:
        return clubDayCashbackCreator.create(
            useEnableDate,
            member,
            payment,
            itemSnapshot,
            cashbackAmount,
            basisAmount,
            rewardCashbackPolicy,
            backendCashbackPolicy
        );
      default:
        //TODO: 기록이나 로깅이 필요할거 같음
        return defaultCashbackCreator.create(
            useEnableDate,
            itemSnapshot,
            cashbackAmount,
            basisAmount,
            rewardCashbackPolicy
        );
    }
  }
}
