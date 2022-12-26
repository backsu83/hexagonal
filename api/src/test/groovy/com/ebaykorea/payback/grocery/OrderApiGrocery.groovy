package com.ebaykorea.payback.grocery

import com.ebaykorea.payback.constant.TestConstant
import com.ebaykorea.payback.core.domain.constant.MemberType
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.BundleDiscountDto
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.BuyerDto
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.ItemSnapshotDto
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.ItemTypeDto
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.OrderBaseDto
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.OrderItemAdditionDto
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.OrderItemDto
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.OrderItemOptionDto
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.OrderQueryResponseDto
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.OrderUnitBranchDto
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.OrderUnitCouponDto
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.OrderUnitDiscountDto
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.OrderUnitDto

import java.time.Instant

class OrderApiGrocery {
  static def OrderQueryResponseDto_생성(Map map = [:]) {
    new OrderQueryResponseDto().tap {
      orderKey = (map.orderKey ?: "orderKey") as String
      paySeq = (map.paySeq ?: 1L) as Long
      orderBase = (map.orderBase ?: OrderBaseDto_생성(map)) as OrderBaseDto
      buyer = (map.buyer ?: BuyerDto_생성(map)) as BuyerDto
      orderUnits = (map.orderUnits ?: [OrderUnitDto_생성(map)]) as List<OrderUnitDto>
      bundleDiscounts = (map.bundleDiscounts ?: null) as List<BundleDiscountDto>
    }
  }

  static def OrderBaseDto_생성(Map map = [:]) {
    new OrderBaseDto().tap {
      orderDate = (map.orderDate ?: TestConstant.ORDER_DATE) as Instant
    }
  }

  static def BuyerDto_생성(Map map = [:]) {
    new BuyerDto().tap {
      buyerId = (map.buyerId ?: "buyerId") as String
      buyerNo = (map.buyerNo ?: "buyerNo") as String
      memberType = (map.memberType ?: MemberType.NormalMember) as MemberType
      smileClubMembership = (map.smileClubMembership ?: SmileClubMembershipDto_생성()) as BuyerDto.SmileClubMembershipDto
    }
  }

  static def SmileClubMembershipDto_생성(Map map = [:]) {
    new BuyerDto.SmileClubMembershipDto().tap{
      smileClubMember = (map.smileClubMember ?: false) as boolean
    }
  }

  static def OrderUnitDto_생성(Map map = [:]) {
    new OrderUnitDto().tap {
      orderUnitKey = (map.orderUnitKey ?: "orderUnitKey1") as String
      orderItem = (map.orderItem ?: OrderItemDto_생성(map)) as OrderItemDto
      itemDiscounts = (map.itemDiscounts ?: []) as List<OrderUnitDiscountDto>
      coupons = (map.coupons ?: []) as List<OrderUnitCouponDto>
    }
  }

  static def OrderItemDto_생성(Map map = [:]) {
    new OrderItemDto().tap {
      snapshotKey = (map.snapshotKey ?: "itemSnapshotKey1") as String
      itemNo = (map.itemNo ?: "itemNo1") as String
      basePrice = (map.basePrice ?: 1000L) as BigDecimal
      quantity = (map.quantity ?: 1) as int
      options = (map.options ?: []) as List<OrderItemOptionDto>
      additions = (map.additions ?: []) as List<OrderItemAdditionDto>
      branch = (map.branch ?: null) as OrderUnitBranchDto
    }
  }


  static def OrderItemOptionDto_생성(Map map = [:]) {
    new OrderItemOptionDto().tap {
      optionNo = (map.optionNo ?: 1L) as long
      sellPrice = (map.sellPrice ?: 100L) as BigDecimal
    }
  }

  static def OrderItemAdditionDto_생성(Map map = [:]) {
    new OrderItemAdditionDto().tap {
      additionNo = (map.additionNo ?: 1L) as long
      quantity = (map.quantity ?: 1) as Integer
      sellPrice = (map.sellPrice ?: 100L) as BigDecimal
    }
  }

  static def OrderUnitBranchDto_생성(Map map = [:]) {
    new OrderUnitBranchDto().tap{
      branchPrice = (map.branchPrice ?: 100L) as BigDecimal
    }
  }

  static def OrderUnitDiscountDto_생성(Map map = [:]) {
    new OrderUnitDiscountDto().tap {
      snapshotKey = (map.snapshotKey ?: "discountSnapshotKey1") as String
      discountAmount = (map.discountAmount ?: 100L) as BigDecimal
    }
  }

  static def OrderUnitCouponDto_생성(Map map = [:]) {
    new OrderUnitCouponDto().tap {
      snapshotKey = (map.snapshotKey ?: "couponSnapshotKey1") as String
      couponAmount = (map.couponAmount ?: 100L) as BigDecimal
    }
  }

  static def BundleDiscountDto_생성(Map map = [:]) {
    new BundleDiscountDto().tap {
      snapshotKey = (map.snapshotKey ?: "bundleDiscountSnapshotKey1") as String
      bundleDiscountUnits = (map.bundleDiscountUnits ?: [BundleDiscountUnitDto_생성(map)]) as List<BundleDiscountDto.BundleDiscountUnitDto>
    }
  }

  static def BundleDiscountUnitDto_생성(Map map = [:]) {
    new BundleDiscountDto.BundleDiscountUnitDto().tap{
      orderUnitKey = (map.orderUnitKey ?: "orderUnitKey1") as String
      discountAmount = (map.discountAmount ?: 100L) as BigDecimal
    }
  }

  static def ItemSnapshotDto_생성(Map map = [:]) {
    new ItemSnapshotDto().tap{
      snapshotKey = (map.snapshotKey ?: "itemSnapshotKey1") as String
      itemNo = (map.itemNo ?: "itemNo1") as String
      sellerCustNo = (map.sellerCustNo ?: "sellerCustNo") as String
      itemLargeCategoryCode = (map.itemLargeCategoryCode ?: "1") as String
      itemMediumCategoryCode = (map.itemMediumCategoryCode ?: "2") as String
      itemSmallCategoryCode = (map.itemSmallCategoryCode ?: "3") as String
      itemType = (map.itemType ?: ItemTypeDto_생성(map)) as ItemTypeDto
      buyerMileageRate = (map.buyerMileageRate ?: 0L) as BigDecimal
    }
  }

  static def ItemTypeDto_생성(Map map = [:]) {
    new ItemTypeDto().tap {
      isMoneyCategory = (map.isMoneyCategory ?: false) as Boolean
      isSmileDelivery = (map.isSmileDelivery ?: false) as Boolean
    }
  }
}
