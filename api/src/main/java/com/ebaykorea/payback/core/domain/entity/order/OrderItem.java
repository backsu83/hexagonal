package com.ebaykorea.payback.core.domain.entity.order;

import static com.ebaykorea.payback.util.PaybackCollections.orEmptyStream;
import static com.ebaykorea.payback.util.PaybackDecimals.summarizing;
import static com.ebaykorea.payback.util.PaybackObjects.orElse;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class OrderItem {
  /** 상품 snapshot key */
  String itemSnapshotKey;

  /** 상품 번호 */
  String itemNo;

  /** 상품 판매 가격 */
  BigDecimal basePrice;

  /** 주문수량, 참고로 주문옵션(orderOptions) 도 이 수량을 따라간다. */
  int quantity;

  /** 주문 옵션 */
  List<OrderItemOption> options;

  /** 추가 구성 */
  List<OrderItemAddition> additions;

  /** 지점 추가 금액 */
  BigDecimal branchPrice;

  public BigDecimal orderItemPrice() {
    return basePrice.multiply(BigDecimal.valueOf(quantity))
        .add(optionPrice())
        .add(additionPrice())
        .add(orElse(branchPrice, BigDecimal.ZERO).multiply(BigDecimal.valueOf(quantity)));
  }

  public BigDecimal optionPrice() {
    return orEmptyStream(options)
        .map(OrderItemOption::getSellPrice)
        .map(price -> price.multiply(BigDecimal.valueOf(quantity)))
        .collect(summarizing());
  }

  public BigDecimal additionPrice() {
    return orEmptyStream(additions)
        .map(addition ->
            orElse(addition.getSellPrice(), BigDecimal.ZERO)
                .multiply(BigDecimal.valueOf(addition.getQuantity())))
        .collect(summarizing());
  }
}
