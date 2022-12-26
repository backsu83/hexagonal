package com.ebaykorea.payback.core.factory.impl;

import com.ebaykorea.payback.core.domain.constant.CashbackPayType;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.SellerCashback;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.CashbackPolicy;
import com.ebaykorea.payback.core.domain.entity.cashback.unit.policy.SellerCashbackPolicy;
import com.ebaykorea.payback.core.domain.entity.order.ItemSnapshot;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

import static com.ebaykorea.payback.core.domain.constant.CashbackType.Seller;

@Component
public class SellerCashbackCreator {
  private static final long SELLER_CASHBACK_POLICY_NO = 0;
  private static final String SELLER_CASHBACK_POLICY_NAME = "판매자 제공 적립";

  public SellerCashback create(
      final Instant useEnableDate,
      final ItemSnapshot itemSnapshot,
      final BigDecimal cashbackAmount,
      final BigDecimal basisAmount
  ) {
    return new SellerCashback(
        itemSnapshot.getItemNo(),
        itemSnapshot.toShopType(),
        cashbackAmount,
        basisAmount,
        useEnableDate,
        createCashbackPolicy(itemSnapshot)
    );
  }

  private CashbackPolicy createCashbackPolicy(final ItemSnapshot itemSnapshot) {
    return new SellerCashbackPolicy(
        SELLER_CASHBACK_POLICY_NO,
        Seller,
        SELLER_CASHBACK_POLICY_NAME,
        null,
        CashbackPayType.FixRate.getCode(),
        itemSnapshot.getBuyerMileageRate(),
        null
    );
  }
}
