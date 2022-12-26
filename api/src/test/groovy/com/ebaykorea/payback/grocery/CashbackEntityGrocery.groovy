package com.ebaykorea.payback.grocery

import com.ebaykorea.payback.constant.TestConstant
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderDetailEntity
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderEntity
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderMemberEntity
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.CashbackOrderPolicyEntity
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.SmilecardCashbackOrderEntity
import com.ebaykorea.payback.infrastructure.persistence.repository.stardb.entity.SmilecardT2T3CashbackEntity
import com.ebaykorea.payback.util.PaybackTimestamps

import javax.persistence.Column
import javax.persistence.Id
import java.sql.Timestamp

class CashbackEntityGrocery {
  static def CashbackOrderEntity_생성(Map map = [:]) {
    new CashbackOrderEntity().tap {
      orderNo = (map.buyOrderNo ?: 1L) as long
      cashbackType = (map.cashbackType ?: "I") as String
      tradeCd = (map.tradeCd ?: "SV") as String
      amount = (map.amount ?: 1000L) as BigDecimal
      basisAmount = (map.basisAmount ?: 1000L) as BigDecimal
      itemNo = (map.itemNo ?: "itemNo1") as String
      packNo = (map.packNo ?: 1L) as long
      buyerNo = (map.buyerNo ?: "buyerNo") as String
      userKey = (map.userKey ?: "") as String
      tradeStatus = (map.tradeStatus ?: "10") as String
      useEnableDt = (map.useEnableDt ?: PaybackTimestamps.from(TestConstant.USE_ENABLE_DATE)) as Timestamp
      siteType = (map.siteType ?: "G") as String
      smileClubYn = (map.smileClubYn ?: "N") as String
      shopType = (map.shopType ?: null) as String
      regId = (map.regId ?: "buyerNo") as String
      regDt = (map.regDt ?: PaybackTimestamps.from(TestConstant.ORDER_DATE)) as Timestamp
      chgId = (map.chgId ?: "buyerNo") as String
    }
  }

  static def CashbackOrderPolicyEntity_생성(Map map = [:]) {
    new CashbackOrderPolicyEntity().tap {
      orderNo = (map.orderNo ?: 1L) as long
      type = (map.type ?: "I") as String
      policyNo = (map.policyNo ?: 0L) as Long
      name = (map.name ?: "cashbackTitle") as String
      subType = (map.subType ?: null) as String
      saveRate = (map.saveRate ?: 0L) as BigDecimal
      payType = (map.payType ?: "P") as String
      maxLimitMoney = map.maxLimitMoney as BigDecimal
      regId = (map.regId ?: "buyerNo") as String
      regDt = (map.regDt ?: PaybackTimestamps.from(TestConstant.ORDER_DATE)) as Timestamp
      chargePaySaveRate = (map.chargePaySaveRate ?: null) as BigDecimal
      chargePayClubSaveRate = (map.chargePayClubSaveRate ?: null) as BigDecimal
      chargePayMaxMoney = (map.chargePayMaxMoney ?: null) as BigDecimal
      chargePayClubMaxMoney = (map.chargePayClubMaxMoney ?: null) as BigDecimal
      clubDayMaxSaveRate = (map.clubDayMaxSaveRate ?: null) as BigDecimal
      clubDayMaxSaveMoney = (map.clubDayMaxSaveMoney ?: null) as BigDecimal
    }
  }

  static def CashbackOrderDetailEntity_생성(Map map = [:]) {
    new CashbackOrderDetailEntity().tap {
      orderNo = (map.orderNo ?: 1L) as long
      itemAmount = (map.itemAmount ?: 0L) as BigDecimal
      sellerAmount = (map.sellerAmount ?: 0L) as BigDecimal
      payAmount = (map.payAmount ?: 0L) as BigDecimal
      clubAmount = (map.clubAmount ?: 0L) as BigDecimal
      itemCashbackApplyYn = (map.itemCashbackApplyYn ?: "N") as String
      payCashbackApplyYn = (map.payCashbackApplyYn ?: "N") as String
      clubCashbackApplyYn = (map.clubCashbackApplyYn ?: "N") as String
      regId = (map.regId ?: "buyerNo") as String
      regDt = (map.regDt ?: PaybackTimestamps.from(TestConstant.ORDER_DATE)) as Timestamp
      chgId = (map.chgId ?: "buyerNo") as String
      chgDt = (map.chgDt ?: PaybackTimestamps.from(TestConstant.ORDER_DATE)) as Timestamp
      chargePayReward = (map.chargePayReward ?: 0L) as BigDecimal
      chargePayRewardClub = (map.chargePayRewardClub ?: 0L) as BigDecimal
      clubDayAmount = (map.clubDayAmount ?: 0L) as BigDecimal
      clubDayApplyYn = (map.clubDayApplyYn ?: "N") as String
    }
  }

  static def CashbackOrderMemberEntity_생성(Map map = [:]) {
    new CashbackOrderMemberEntity().tap {
      packNo = (map.packNo ?: 1L) as Long
      buyerNo = (map.buyerNo ?: "buyerNo") as String
      regSite = (map.regSite ?: null) as String
      payType = (map.payType ?: null) as String
      memberGrade = (map.memberGrade ?: null) as String
      clubCheckYn = (map.clubCheckYn ?: "N") as String
      insOprt = (map.insOprt ?: "buyerNo") as String
      insDate = (map.insDate ?: PaybackTimestamps.from(TestConstant.ORDER_DATE)) as Timestamp
      updOprt = (map.updOprt ?: "buyerNo") as String
      updDate = (map.updDate ?: PaybackTimestamps.from(TestConstant.ORDER_DATE)) as Timestamp
    }
  }

  static def SmilecardCashbackOrderEntity_생성(Map map = [:]) {
    new SmilecardCashbackOrderEntity().tap {
      packNo = (map.packNo ?: 1L) as Long
      cashbackAmount = (map.cashbackAmount ?: 1000L) as BigDecimal
      applyYn = (map.applyYn ?: "N") as String
      regId = (map.regId ?: "buyerNo") as String
      regDt = (map.regDt ?: PaybackTimestamps.from(TestConstant.ORDER_DATE)) as Timestamp
      chgId = (map.chgId ?: "buyerNo") as String
      chgDt = (map.chgDt ?: PaybackTimestamps.from(TestConstant.ORDER_DATE)) as Timestamp
      t2t3CashbackAmount = (map.t2t3CashbackAmount ?: 0L) as BigDecimal
      t2t3ApplyYn = (map.t2t3ApplyYn ?: "N") as String
      itemType = (map.itemType ?: null) as String
    }
  }

  static def SmilecardT2T3CashbackEntity_생성(Map map = [:]) {
    new SmilecardT2T3CashbackEntity().tap {
      packNo = (map.packNo ?: 1L) as Long
      orderNo = (map.orderNo ?: 1L) as Long
      itemPrice = (map.itemPrice ?: 1000L) as BigDecimal
      t2t3CashbackAmount = (map.t2t3CashbackAmount ?: 1000L) as BigDecimal
      smileCardType = (map.smileCardType ?: "") as String
      siteCode = (map.siteCode ?: "") as String
      insDate = (map.insDate ?: PaybackTimestamps.from(TestConstant.ORDER_DATE)) as Timestamp
      updDate = (map.updDate ?: PaybackTimestamps.from(TestConstant.ORDER_DATE)) as Timestamp
      insOprt = (map.insOprt ?: "buyerNo") as String
      updOprt = (map.updOprt ?: "buyerNo") as String
      itemType = (map.itemType ?: null) as String
    }
  }
}
