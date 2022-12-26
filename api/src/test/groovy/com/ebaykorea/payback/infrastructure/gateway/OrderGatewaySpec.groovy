package com.ebaykorea.payback.infrastructure.gateway

import com.ebaykorea.payback.infrastructure.gateway.client.order.OrderApiClient
import com.ebaykorea.payback.infrastructure.gateway.mapper.OrderGatewayMapper
import org.mapstruct.factory.Mappers
import spock.lang.Specification

import static com.ebaykorea.payback.grocery.OrderApiGrocery.BundleDiscountDto_생성
import static com.ebaykorea.payback.grocery.OrderApiGrocery.ItemSnapshotDto_생성
import static com.ebaykorea.payback.grocery.OrderApiGrocery.OrderItemAdditionDto_생성
import static com.ebaykorea.payback.grocery.OrderApiGrocery.OrderItemOptionDto_생성
import static com.ebaykorea.payback.grocery.OrderApiGrocery.OrderQueryResponseDto_생성
import static com.ebaykorea.payback.grocery.OrderApiGrocery.OrderUnitBranchDto_생성
import static com.ebaykorea.payback.grocery.OrderApiGrocery.OrderUnitCouponDto_생성
import static com.ebaykorea.payback.grocery.OrderApiGrocery.OrderUnitDiscountDto_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.BundleDiscount_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.ItemSnapshot_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.ItemSnapshots_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.OrderItemAddition_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.OrderItemOption_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.OrderUnitCoupon_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.OrderUnitDiscount_생성
import static com.ebaykorea.payback.grocery.OrderGrocery.Order_생성

class OrderGatewaySpec extends Specification {
  def orderApiClient = Stub(OrderApiClient)
  def orderGatewayMapper = Mappers.getMapper(OrderGatewayMapper)
  def orderGatewayImpl = new OrderGatewayImpl(orderApiClient, orderGatewayMapper)

  def "Order로의 변환이 잘 되는지 확인한다"() {
    setup:
    orderApiClient.findOrder(_ as String, _ as String) >> Optional.of(response)

    expect:
    def result = orderGatewayImpl.getOrder("orderKey")
    result != null
    result == expectResult

    where:
    desc          | response                                                                                                                              | expectResult
    "기본"          | OrderQueryResponseDto_생성()                                                                                                            | Order_생성(member: true)
    "옵션, 추가구성 사용" | OrderQueryResponseDto_생성(options: [OrderItemOptionDto_생성()], additions: [OrderItemAdditionDto_생성()], branch: OrderUnitBranchDto_생성()) | Order_생성(member: true, options: [OrderItemOption_생성()], additions: [OrderItemAddition_생성()], branchPrice: 100L)
    "할인 적용"       | OrderQueryResponseDto_생성(itemDiscounts: [OrderUnitDiscountDto_생성()], coupons: [OrderUnitCouponDto_생성()])                              | Order_생성(member: true, itemDiscounts: [OrderUnitDiscount_생성()], coupons: [OrderUnitCoupon_생성()])
    "복수 할인 적용"    | OrderQueryResponseDto_생성(bundleDiscounts: [BundleDiscountDto_생성()])                                                                   | Order_생성(member: true, bundleDiscounts: [BundleDiscount_생성()])
  }

  def "ItemSnapshot으로의 변환이 잘 되는지 확인한다"() {
    setup:
    orderApiClient.findItemSnapshots(_ as List<String>) >> response

    expect:
    def resultAsync = orderGatewayImpl.getItemSnapshotAsync(List.of("itemSnapshotKey1"))

    def result = resultAsync.join()
    result != null
    result == expectResult

    where:
    desc | response                                                                                           | expectResult
    "단일" | [ItemSnapshotDto_생성()]                                                                             | ItemSnapshots_생성(itemSnapshots: [ItemSnapshot_생성()])
    "복수" | [ItemSnapshotDto_생성(), ItemSnapshotDto_생성(snapshotKey: "itemSnapshotKey2", isMoneyCategory: true)] | ItemSnapshots_생성(itemSnapshots: [ItemSnapshot_생성(), ItemSnapshot_생성(snapshotKey: "itemSnapshotKey2", isMoneyCategory: true)])
  }
}
