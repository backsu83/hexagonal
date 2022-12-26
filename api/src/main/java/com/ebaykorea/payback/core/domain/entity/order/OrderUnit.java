package com.ebaykorea.payback.core.domain.entity.order;

import static com.ebaykorea.payback.core.domain.constant.PaybackConstants.BASIS_MONEY_RATE;
import static com.ebaykorea.payback.util.PaybackCollections.orEmptyStream;
import static com.ebaykorea.payback.util.PaybackDecimals.summarizing;
import static com.ebaykorea.payback.util.PaybackObjects.orElse;

import java.math.BigDecimal;

import com.ebaykorea.payback.util.PaybackStrings;
import lombok.*;

import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Value
@Builder
public class OrderUnit {

  /**
   * 주문 단위 Key
   */
  String orderUnitKey;

  /**
   * 주문 Item 정보
   */
  OrderItem orderItem;

  /**
   * 상품 할인 정보
   */
  List<OrderUnitDiscount> itemDiscounts;

  /**
   * 쿠폰 정보
   */
  List<OrderUnitCoupon> coupons;

  public BigDecimal orderUnitPrice() {
    return orderItem.orderItemPrice()
        .subtract(itemDiscountPrice())
        .subtract(couponPrice());
  }

  public BigDecimal orderUnitPriceExcludingCouponPrice() {
    return orderItem.orderItemPrice()
        .subtract(itemDiscountPrice());
  }

  public BigDecimal orderUnitPrice(final BigDecimal bundleDiscountPrice) {
    return orderUnitPrice()
        .subtract(orElse(bundleDiscountPrice, BigDecimal.ZERO));
  }

  public BigDecimal orderUnitPrice(final BigDecimal bundleDiscountPrice, final BigDecimal buyerMileageRate) {
    return orderUnitPrice(bundleDiscountPrice)
        .multiply(buyerMileageRate)
        .multiply(BASIS_MONEY_RATE)
        .setScale(0, RoundingMode.FLOOR);
  }

  public BigDecimal itemDiscountPrice() {
    return orEmptyStream(itemDiscounts)
        .map(OrderUnitDiscount::getDiscountAmount)
        .collect(summarizing());
  }

  public BigDecimal couponPrice() {
    return orEmptyStream(coupons)
        .map(OrderUnitCoupon::getCouponAmount)
        .collect(summarizing());
  }

  private Optional<OrderItem> findOrderItem() {
    return Optional.ofNullable(orderItem);
  }

  public String getItemSnapshotKey() {
    return findOrderItem()
        .map(OrderItem::getItemSnapshotKey)
        .orElse(PaybackStrings.EMPTY);
  }
}
