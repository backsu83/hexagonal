package com.ebaykorea.payback.grocery

import com.ebaykorea.payback.constant.TestConstant
import com.ebaykorea.payback.core.domain.entity.order.BundleDiscount
import com.ebaykorea.payback.core.domain.entity.order.BundleDiscountUnit
import com.ebaykorea.payback.core.domain.entity.order.ItemSnapshot
import com.ebaykorea.payback.core.domain.entity.order.ItemSnapshots
import com.ebaykorea.payback.core.domain.entity.order.KeyMap
import com.ebaykorea.payback.core.domain.entity.order.Order
import com.ebaykorea.payback.core.domain.entity.order.Buyer
import com.ebaykorea.payback.core.domain.entity.order.OrderItem
import com.ebaykorea.payback.core.domain.entity.order.OrderItemAddition
import com.ebaykorea.payback.core.domain.entity.order.OrderItemOption
import com.ebaykorea.payback.core.domain.entity.order.OrderUnit
import com.ebaykorea.payback.core.domain.entity.order.OrderUnitCoupon
import com.ebaykorea.payback.core.domain.entity.order.OrderUnitDiscount
import com.ebaykorea.payback.core.domain.entity.order.OrderUnitKey

import java.time.Instant

class OrderGrocery {
  static def Order_생성(Map map = [:]) {
    Order.of(
        (map.orderKey ?: "orderKey") as String,
        (map.paySeq ?: 1L) as Long,
        (map.buyer ?: Buyer_생성(map)) as Buyer,
        (map.orderDate ?: TestConstant.ORDER_DATE) as Instant,
        (map.orderUnits ?: [OrderUnit_생성(map)]) as List<OrderUnit>,
        (map.bundleDiscounts ?: []) as List<BundleDiscount>
    )
  }

  static def Buyer_생성(Map map = [:]) {
    Buyer.builder()
        .buyerNo((map.buyerNo ?: "buyerNo") as String)
        .buyerId((map.buyerId ?: "buyerId") as String)
        .member((map.member) as boolean)
        .smileClubMember((map.smileClubMember ?: false) as boolean)
        .build()
  }

  static def OrderUnit_생성(Map map = [:]) {
    OrderUnit.builder()
        .orderUnitKey((map.orderUnitKey ?: "orderUnitKey1") as String)
        .orderItem((map.orderItem ?: OrderItem_생성(map)) as OrderItem)
        .itemDiscounts((map.itemDiscounts ?: []) as List<OrderUnitDiscount>)
        .coupons((map.coupons ?: []) as List<OrderUnitCoupon>)
        .build()
  }

  static def OrderItem_생성(Map map = [:]) {
    OrderItem.builder()
        .itemSnapshotKey((map.itemSnapshotKey ?: "itemSnapshotKey1") as String)
        .itemNo((map.itemNo ?: "itemNo1") as String)
        .basePrice((map.basePrice ?: 1000L) as BigDecimal)
        .quantity((map.quantity ?: 1) as int)
        .options((map.options ?: []) as List<OrderItemOption>)
        .additions((map.additions ?: []) as List<OrderItemAddition>)
        .branchPrice((map.branchPrice ?: null) as BigDecimal)
        .build()
  }

  static def OrderItemOption_생성(Map map = [:]) {
    OrderItemOption.builder()
        .optionNo((map.optionNo ?: 1L) as long)
        .sellPrice((map.sellPrice ?: 100L) as BigDecimal)
        .build()
  }

  static def OrderItemAddition_생성(Map map = [:]) {
    OrderItemAddition.builder()
        .additionNo((map.additionNo ?: 1L) as long)
        .quantity((map.quantity ?: 1L) as Integer)
        .sellPrice((map.sellPrice ?: 100L) as BigDecimal)
        .build()
  }

  static def OrderUnitDiscount_생성(Map map = [:]) {
    OrderUnitDiscount.builder()
        .snapshotKey((map.snapshotKey ?: "discountSnapshotKey1") as String)
        .discountAmount((map.discountAmount ?: 100L) as BigDecimal)
        .build()
  }

  static def OrderUnitCoupon_생성(Map map = [:]) {
    OrderUnitCoupon.builder()
        .snapshotKey((map.snapshotKey ?: "couponSnapshotKey1") as String)
        .couponAmount((map.couponAmount ?: 100L) as BigDecimal)
        .build()
  }

  static def BundleDiscount_생성(Map map = [:]) {
    BundleDiscount.builder()
        .snapshotKey((map.snapshotKey ?: "bundleDiscountSnapshotKey1") as String)
        .bundleDiscountUnits((map.bundleDiscountUnits ?: [BundleDiscountUnit_생성(map)]) as List<BundleDiscountUnit>)
        .build()
  }

  static def BundleDiscountUnit_생성(Map map = [:]) {
    BundleDiscountUnit.builder()
        .orderUnitKey((map.orderUnitKey ?: "orderUnitKey1") as String)
        .discountAmount((map.discountAmount ?: 100L) as BigDecimal)
        .build()
  }

  static def ItemSnapshots_생성(Map map = [:]) {
    ItemSnapshots.of(
        (map.itemSnapshots ?: []) as List<ItemSnapshot>
    )
  }

  static def ItemSnapshot_생성(Map map = [:]) {
    ItemSnapshot.builder()
        .snapshotKey((map.snapshotKey ?: "itemSnapshotKey1") as String)
        .itemNo((map.itemNo ?: "itemNo1") as String)
        .sellerCustNo((map.sellerCustNo ?: "sellerCustNo") as String)
        .itemLargeCategoryCode((map.itemLargeCategoryCode ?: "1") as String)
        .itemMediumCategoryCode((map.itemMediumCategoryCode ?: "2") as String)
        .itemSmallCategoryCode((map.itemSmallCategoryCode ?: "3") as String)
        .isMoneyCategory((map.isMoneyCategory ?: false) as boolean)
        .isSmileDelivery((map.isSmileDelivery ?: false) as boolean)
        .isSmileFresh((map.isSmileFresh ?: false) as boolean)
        .buyerMileageRate((map.buyerMileageRate ?: 0L) as BigDecimal)
        .build()
  }

  static def KeyMap_생성(Map map = [:]) {
    KeyMap.of(
        (map.txKey ?: "txKey") as String,
        (map.orderKey ?: "orderKey") as String,
        (map.packNo ?: 1L) as long,
        (map.orderUnitKeys ?: [OrderUnitKey_생성(map)]) as List<OrderUnitKey>,
    )
  }

  static def OrderUnitKey_생성(Map map = [:]) {
    OrderUnitKey.builder()
        .orderUnitKey((map.orderUnitKey ?: "orderUnitKey1") as String)
        .buyOrderNo((map.buyOrderNo ?: 1L) as long)
        .contrNo((map.contrNo ?: 1L) as long)
        .build()
  }
}
