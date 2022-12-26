package com.ebaykorea.payback.grocery

import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.PaymentAuthDto
import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.PaymentDto
import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.PaymentMainDto
import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.PaymentSubDto
import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.auth.CardDto
import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.auth.SmilePayDto

class PaymentApiGrocery {

  static def paymentDto_생성(Map map = [:]) {
    new PaymentDto(
        (map.paymentSequence ?: "38876501") as Long,
        (map.txKey ?: "15d399b007000300ftlsxgk") as String,
        (map.buyerNo ?: "132872352") as String,
        (map.buyerId ?: "seunghbaek") as String,
        (map.partnershipCode ?: "200011415") as String,
        (map.mainPaymentMethod ?: new PaymentMainDto(
            (map.amount ?: 17000L) as BigDecimal,
            (map.mediumCode ?: "200000036") as String,
            (map.smallCode ?: "300000290") as String,
        )) as PaymentMainDto,
        (map.subPaymentMethods ?: []) as List<PaymentSubDto>,
        (map.authentications ?: new PaymentAuthDto(
            (map.smilePay ?: smilePayDto_생성()) as SmilePayDto,
            (map.card ?: null) as CardDto
        )) as PaymentAuthDto
    )
  }

  static def smilePayDto_생성(Map map = [:]) {
    new SmilePayDto(
        (map.certificationId ?: "1100000017276736S001") as String,
        (map.smilePayToken ?: "token") as String,
        (map.totalMoney ?: 17000L) as BigDecimal,
        (map.cardRequestMoney ?: 0L) as BigDecimal,
        (map.cashRequestMoney ?: 0L) as BigDecimal,
        (map.mobileRequestMoney ?: 0L) as BigDecimal,
        (map.etcRequestMoney ?: 0L) as BigDecimal,
        (map.prepayRequestMoney ?: 0L) as BigDecimal,
        (map.isFreeInstallment ?: false) as Boolean,
        (map.settleGroupSequence ?: null) as Long,
        (map.smilePayContractCode ?: [100L]) as List<Long>,
        (map.smilePayItemType ?: 1) as Integer
    )
  }

}