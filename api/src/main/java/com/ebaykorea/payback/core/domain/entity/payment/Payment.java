package com.ebaykorea.payback.core.domain.entity.payment;

import static com.ebaykorea.payback.core.exception.PaybackExceptionCode.DOMAIN_ENTITY_011;
import static com.ebaykorea.payback.util.PaybackCollections.orEmptyStream;
import static com.ebaykorea.payback.util.PaybackDecimals.isGreaterThanZero;

import com.ebaykorea.payback.core.domain.constant.SmileCardType;
import com.ebaykorea.payback.core.exception.PaybackException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.Value;

@Value
public class Payment {
  /**
   * 결제 순번
   **/
  Long paymentSequence;
  /**
   * main 결제 코드
   */
  PaymentMethod mainPaymentMethod;
  /**
   * sub 결제 코드
   */
  List<PaymentMethodSub> subPaymentMethods;

  SmilePay smilePay;

  Card card;

  public static Payment of(
      final Long paymentSequence,
      final PaymentMethod mainPaymentMethod,
      final List<PaymentMethodSub> subPaymentMethods,
      final SmilePay smilePay,
      final Card card
  ) {
    return new Payment(paymentSequence, mainPaymentMethod, subPaymentMethods, smilePay, card);
  }

  private Payment(
      final Long paymentSequence,
      final PaymentMethod mainPaymentMethod,
      final List<PaymentMethodSub> subPaymentMethods,
      final SmilePay smilePay,
      final Card card
  ) {
    this.paymentSequence = paymentSequence;
    this.mainPaymentMethod = mainPaymentMethod;
    this.subPaymentMethods = subPaymentMethods;
    this.smilePay = smilePay;
    this.card = card;

    validate();
  }

  private void validate() {
    if (!hasMainPaymentMethod() && !hasSubPaymentMethods()) {
      throw new PaybackException(DOMAIN_ENTITY_011);
    }
  }

  private Optional<PaymentMethod> findMainPaymentMethod() {
    return Optional.ofNullable(mainPaymentMethod);
  }

  // 주 결제 수단 존재 여부
  private boolean hasMainPaymentMethod() {
    return findMainPaymentMethod().isPresent();
  }

  // 부 결제 수단 존재 여부
  private boolean hasSubPaymentMethods() {
    return orEmptyStream(subPaymentMethods).findAny().isPresent();
  }

  public BigDecimal getMainPaymentAmount() {
    return findMainPaymentMethod()
        .map(PaymentMethod::getAmount)
        .orElse(BigDecimal.ZERO);
  }

  public boolean hasMainPaymentAmount() {
    return isGreaterThanZero(getMainPaymentAmount());
  }

  private Optional<Card> findCard() {
    return Optional.ofNullable(card);
  }

  // 수기 결제 여부
  public boolean isManualCardPayment() {
    return findCard()
        .map(Card::isManualPayment)
        .orElse(false);
  }

  // 자동 충전 여부
  public boolean isChargePayment() {
    return findMainPaymentMethod()
        .map(PaymentMethod::isChargePay)
        .orElse(false);
  }

  public boolean isSmilePayPayment() {
    return findMainPaymentMethod()
        .map(PaymentMethod::isSmilePay)
        .orElse(false);
  }

  // 무이자 할부 여부
  public boolean isFreeInstallment() {
    return isCardFreeInstallment() || isSmilePayFreeInstallment();
  }

  private boolean isCardFreeInstallment() {
    return findCard()
        .map(Card::isFreeInstallment)
        .orElse(false);
  }

  private boolean isSmilePayFreeInstallment() {
    return Optional.ofNullable(smilePay)
        .map(SmilePay::isFreeInstallment)
        .orElse(false);
  }


  public SmileCardType toSmileCardType() {
    return findMainPaymentMethod()
        .map(PaymentMethod::toSmileCardType)
        .orElse(SmileCardType.Unknown);
  }

  public boolean isT2T3SmileCard() {
    return findMainPaymentMethod()
        .map(PaymentMethod::isT2T3)
        .orElse(false);
  }

  public boolean isT1T2T3SmileCard() {
    return findMainPaymentMethod()
        .map(PaymentMethod::isT1T2T3)
        .orElse(false);
  }

  public boolean isSmileCard() {
    return findMainPaymentMethod()
        .map(PaymentMethod::isSmileCard)
        .orElse(false);
  }
}
