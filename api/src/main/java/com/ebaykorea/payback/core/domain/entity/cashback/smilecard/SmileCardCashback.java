package com.ebaykorea.payback.core.domain.entity.cashback.smilecard;

import static com.ebaykorea.payback.util.PaybackDecimals.summarizing;

import com.ebaykorea.payback.core.domain.constant.ShopType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class SmileCardCashback {

  BigDecimal cashbackAmount;
  SmileCardCashbackApplyStrategy strategy;
  List<T2T3SmileCardCashback> t2t3Cashbacks;

  public static SmileCardCashback of(
      final BigDecimal cashbackAmount,
      final boolean isSmileCard,
      final boolean isFreeInstallment,
      final List<T2T3SmileCardCashback> t2t3Cashbacks) {
    return new SmileCardCashback(
        cashbackAmount,
        SmileCardCashbackApplyStrategy.defaultSmileCardCashbackStrategy(isSmileCard, isFreeInstallment, cashbackAmount),
        t2t3Cashbacks);
  }

  private SmileCardCashback(
      final BigDecimal cashbackAmount,
      final SmileCardCashbackApplyStrategy strategy,
      final List<T2T3SmileCardCashback> t2t3Cashbacks) {
    this.cashbackAmount = cashbackAmount;
    this.strategy = strategy;
    this.t2t3Cashbacks = t2t3Cashbacks;
  }

  public BigDecimal sumT2T3Amount() {
    return t2t3Cashbacks
        .stream()
        .map(T2T3SmileCardCashback::getAmount)
        .collect(summarizing());
  }

  public ShopType toShopType() {
    boolean isSmileDelivery = t2t3Cashbacks.stream().anyMatch(T2T3SmileCardCashback::isSmileDelivery);
    boolean isSmileFresh = t2t3Cashbacks.stream().anyMatch(T2T3SmileCardCashback::isSmileFresh);
    return isSmileDelivery ? ShopType.SmileDelivery : isSmileFresh ? ShopType.SmileFresh : ShopType.Unknown;
  }

  public boolean isApply() {
    return strategy.isApply();
  }

  public boolean isApplyT2T3() {
    return t2t3Cashbacks.stream().anyMatch(T2T3SmileCardCashback::isApply);
  }
}
