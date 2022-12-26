package com.ebaykorea.payback.grocery

import com.ebaykorea.payback.core.domain.constant.ComplexType
import com.ebaykorea.payback.core.domain.constant.InstallmentType
import com.ebaykorea.payback.core.domain.constant.PaymentCode
import com.ebaykorea.payback.core.domain.entity.payment.Card
import com.ebaykorea.payback.core.domain.entity.payment.Payment
import com.ebaykorea.payback.core.domain.entity.payment.PaymentMethod
import com.ebaykorea.payback.core.domain.entity.payment.PaymentMethodSub
import com.ebaykorea.payback.core.domain.entity.payment.SmilePay

class PaymentGrocery {
  static def 기본_Payment_생성(Map map = [:]) {
    Payment.of(
        (map.paymentSequence ?: "38876501") as Long,
        (map.mainPaymentMethod ?: null) as PaymentMethod,
        (map.subPaymentMethods ?: []) as List<PaymentMethodSub>,
        (map.smilePay ?: null) as SmilePay,
        (map.card ?: null) as Card,
    )
  }

  static def 스마일페이_Payment_생성(Map map = [:]) {
    기본_Payment_생성(
        mainPaymentMethod: paymentMethod_생성(map),
        smilePay: smilePay_생성(map)
    )
  }

  static def 카드_Payment_생성(Map map = [:]) {
    기본_Payment_생성(
        mainPaymentMethod: paymentMethod_생성(mediumCode: PaymentCode.PaymentMethodMediumCode.CreditCard),
        card: card_생성(map)
    )
  }

  static def paymentMethod_생성(Map map = [:]) {
    PaymentMethod.builder()
        .amount((map.amount ?: 17000L) as BigDecimal)
        .mediumCode((map.mediumCode ?: PaymentCode.PaymentMethodMediumCode.NewSmilePayCMS) as String)
        .smallCode((map.smallCode ?: PaymentCode.PaymentMethodSmallCode.SmilePayReCharge) as String)
        .build()
  }

  static def PaymentMethodSub_생성(Map map = [:]) {
    PaymentMethodSub.builder()
        .complexType((map.complexType ?: ComplexType.SmileCash) as ComplexType)
        .amount((map.amount ?: 1000L) as BigDecimal)
        .mediumCode((map.mediumCode ?: "200000036") as String)
        .smallCode((map.mediumCode ?: "200000036") as String)
        .build()
  }

  static def smilePay_생성(Map map = [:]) {
    SmilePay.builder()
        .isFreeInstallment((map.isFreeInstallment ?: false) as boolean)
        .build()
  }

  static def card_생성(Map map = [:]) {
    Card.builder()
        .freeInstallmentType((map.freeInstallmentType ?: InstallmentType.Default) as InstallmentType)
        .isManualPayment((map.isManualPayment ?: false) as boolean)
        .build()
  }


}
