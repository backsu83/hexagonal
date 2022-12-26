package com.ebaykorea.payback.grocery

import com.ebaykorea.payback.core.domain.constant.CashbackType
import com.ebaykorea.payback.infrastructure.gateway.client.reward.dto.*

class RewardApiGrocery {
  static def CashbackRequestDataDto_생성(Map map = [:]) {
    new CashbackRewardRequestDto(
      (map.totalPrice ?: 17000) as Integer,
      (map.goods ?: [CashbackRewardGoodRequestDto_생성()]) as List<CashbackRewardGoodRequestDto>
    )
  }

  static def CashbackRewardGoodRequestDto_생성(Map map = [:]) {
    new CashbackRewardGoodRequestDto().tap {
      key = (map.key ?: "1") as String
      siteCd = (map.siteCd ?: 0) as Integer
      gdNo = (map.gdNo ?: "itemNo1") as String
      gdlcCd = (map.gdlcCd ?: "1") as String
      gdmcCd = (map.gdmcCd ?: "2") as String
      gdscCd = (map.gdscCd ?: "3") as String
      scNo = (map.scNo ?: "sellerCustNo") as String
      isSmileClub = (map.isSmileClub ?: false) as Boolean
      isSmileDelivery = (map.isSmileDelivery ?: false) as Boolean
      isSmileFresh = (map.isSmileFresh ?: false) as Boolean
      qty = (map.qty ?: 1) as Integer
      price = (map.price ?: 1000) as Integer
      marketabilityItemYn = (map.marketabilityItemYn ?: "N") as String
    }
  }

  static def CashbackResponseDataDto_생성(Map map = [:]) {
    new CashbackRewardResponseDto().tap {
      totalItemCashbackAmount = (map.totalItemCashbackAmount ?: 0) as Integer
      totalNSPCashbackAmount = (map.totalNSPCashbackAmount ?: 0) as Integer
      ifSmileCardCashbackAmount = (map.ifSmileCardCashbackAmount ?: 0) as Integer
      ifNewSmileCardCashbackAmount = (map.ifNewSmileCardCashbackAmount ?: 0) as Integer
      useEnableDate = (map.useEnableDate ?: "2023-10-17") as String
      goods = (map.goods ?: [CashbackRewardGoodResponseDto_생성(map)]) as List<CashbackRewardGoodResponseDto>
    }
  }

  static def CashbackRewardGoodResponseDto_생성(Map map = [:]) {
    new CashbackRewardGoodResponseDto().tap {
      clubDayExpectSaveAmount = (map.clubDayExpectSaveAmount ?: 0) as Integer
      clubDayExpectSaveRate = (map.clubDayExpectSaveRate ?: 0) as Integer
      key = (map.key ?: "1") as String
      gdNo = (map.gdNo ?: "itemNo1") as String
      ifSmileClubCashbackAmount = (map.ifSmileClubCashbackAmount ?: 0) as Integer
      cashbackInfo = (map.cashbackInfo ?: [CashbackInfoDto_생성()]) as List<CashbackInfoDto>
      itemCashbackInfo = (map.itemCashbackInfo ?: null) as ItemCashbackInfoDto
      NSPCashbackInfo = (map.NSPCashbackInfo ?: NspCashbackInfoDto_생성()) as NspCashbackInfoDto
      ifSmileCardT2T3CashbackAmount = (map.ifSmileCardT2T3CashbackAmount ?: 0) as Integer
      clubDayCashbackInfo = (map.clubDayCashbackInfo ?: null) as ClubDayCashbackInfoDto
    }
  }

  static def CashbackInfoDto_생성(Map map = [:]) {
    new CashbackInfoDto().tap{
      cashbackCd = (map.cashbackCd ?: CashbackType.Item) as CashbackType
      cashbackAmount = (map.cashbackAmount ?: 1000) as Integer
      cashbackSeq = (map.cashbackSeq ?: 1) as Integer
      payType = (map.payType ?: "P") as String
      payRate = (map.payRate ?: 0L) as BigDecimal
      payMaxMoney = (map.payMaxMoney ?: 0L) as BigDecimal
      cashbackTitle = (map.cashbackTitle ?: "cashbackTitle") as String
      etcTitle = (map.etcTitle ?: "etcTitle") as String
      etcContent = (map.etcContent ?: "etcContent") as String
    }
  }

  static def NspCashbackInfoDto_생성(Map map = [:]) {
    new NspCashbackInfoDto().tap {
      payAmount = (map.payAmount ?: 0) as Integer
      clubAmount = (map.clubAmount ?: 0) as Integer
      autoChargeAmount = (map.autoChargeAmount ?: 0) as Integer
      autoChargeClubAmount = (map.autoChargeClubAmount ?: 0) as Integer
    }
  }

  static def CashbackRewardBackendResponseDto_생성(Map map = [:]) {
    new CashbackRewardBackendResponseDto().tap {
      key = (map.key ?: "1") as String
      gdNo = (map.gdNo ?: "itemNo1") as String
      cashbackSeq = (map.cashbackSeq ?: 1) as Integer
      cashbackCode = (map.cashbackCode ?: CashbackType.Item) as CashbackType
      cashbackTitle = (map.cashbackTitle ?: "cashbackTitle") as String
      useEnableDate = (map.useEnableDate ?: "2023-10-17") as String
      cashbackMoney = (map.cashbackMoney ?: 1000) as Integer
      payType = (map.payType ?: 1) as Integer
      payRate = (map.payRate ?: 0) as BigDecimal
      payMaxMoney = (map.payMaxMoney ?: 0) as Integer
      chargePayRewardRate = (map.chargePayRewardRate ?: 0L) as BigDecimal
      chargePayRewardClubRate = (map.chargePayRewardClubRate ?: 0L) as BigDecimal
      chargePayRewardSmileDeliveryRate = (map.chargePayRewardSmileDeliveryRate ?: 0) as Integer
      chargePayRewardSmileDeliveryClubRate = (map.chargePayRewardSmileDeliveryClubRate ?: 0) as Integer
      chargePayRewardMaxMoney = (map.chargePayRewardMaxMoney ?: 0) as Integer
      chargePayRewardClubMaxMoney = (map.chargePayRewardClubMaxMoney ?: 0) as Integer
      chargeCashbackMoney = (map.chargeCashbackMoney ?: 0) as Integer
      chargeClubCashbackMoney = (map.chargeClubCashbackMoney ?: 0) as Integer
      clubDay = (map.clubDay ?: "0001000") as String
      clubDayPayRate = (map.clubDayPayRate ?: 0) as BigDecimal
      clubDaySaveMaxMoney = (map.clubDaySaveMaxMoney ?: 0) as Integer
      clubDayCashbackMoney = (map.clubDayCashbackMoney ?: 0) as Integer
    }
  }

}
