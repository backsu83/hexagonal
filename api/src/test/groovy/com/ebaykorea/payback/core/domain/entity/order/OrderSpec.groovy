package com.ebaykorea.payback.core.domain.entity.order

import spock.lang.Specification

import static com.ebaykorea.payback.grocery.OrderGrocery.BundleDiscount_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.OrderUnit_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.Order_생성

class OrderSpec extends Specification {
  def "Order 겁증"() {
    expect:
    def order = 주문정보
    order.getBundleDiscountMap() == 복수할인정보
    order.findItemSnapshotKeys() == 상품키
    order.isForCashback() == 캐시백주문대상여부

    where:
    desc      | 주문정보                                                                                                    | 복수할인정보                  | 상품키                                      | 캐시백주문대상여부
    "기본"      | Order_생성()                                                                                              | [:]                     | ["itemSnapshotKey1"]                     | false
    "회원,여러상품" | Order_생성(member: true, orderUnits: [OrderUnit_생성(), OrderUnit_생성(itemSnapshotKey: "itemSnapshotKey2")]) | [:]                     | ["itemSnapshotKey1", "itemSnapshotKey2"] | true
    "복수할인주문"  | Order_생성(bundleDiscounts: [BundleDiscount_생성()])                                                        | ["orderUnitKey1": 100L] | ["itemSnapshotKey1"]                     | false
  }
}
