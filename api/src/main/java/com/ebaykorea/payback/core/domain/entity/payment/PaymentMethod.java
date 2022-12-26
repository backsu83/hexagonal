package com.ebaykorea.payback.core.domain.entity.payment;

import com.ebaykorea.payback.core.domain.constant.PaymentCode;
import com.ebaykorea.payback.core.domain.constant.SmileCardType;
import com.ebaykorea.payback.util.PaybackStrings;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

import static com.ebaykorea.payback.core.domain.constant.PaymentCode.PaymentMethodMediumCode.*;
import static com.ebaykorea.payback.core.domain.constant.PaymentCode.PaymentMethodSmallCode.*;

@Value
@Builder
public class PaymentMethod {
  /**
   * 결제 금액
   */
  BigDecimal amount;
  /**
   * 중분류코드
   */
  String mediumCode;
  /**
   * 소분류코드
   */
  String smallCode;

  // 스마일페이 여부
  public boolean isSmilePay() {
    return hasMediumCode(NewSmilePayCard, NewSmilePayCMS, NewSmilePayMobile);
  }

  // 캐시 충전 여부
  public boolean isChargePay() {
    //캐시 충전은 mediumCode값이 NewSmilePayCMS값으로 넘어옵니다
    return isSmilePay() && hasSmallCode(SmilePayReCharge);
  }

  public boolean isSmileCard() {
    return hasSmallCode(SmileCard) || isT1T2T3();
  }
  public boolean isT1T2T3() {
    return hasSmallCode(SmileCardT1) || isT2T3();
  }
  public boolean isT2T3() {
    return hasSmallCode(SmileCardT2, SmileCardT3);
  }

  private boolean hasMediumCode(final String ...mediumCodes) {
    for (String code : mediumCodes) {
      if (code.equals(mediumCode)) {
        return true;
      }
    }
    return false;
  }

  private boolean hasSmallCode(final String ...smallCodes) {
    for (String code : smallCodes) {
      if (code.equals(smallCode)) {
        return true;
      }
    }
    return false;
  }

  public SmileCardType toSmileCardType() {
    switch (smallCode) {
      case PaymentCode.PaymentMethodSmallCode.SmileCard:
        return SmileCardType.T0;
      case PaymentCode.PaymentMethodSmallCode.SmileCardT1:
        return SmileCardType.T1;
      case SmileCardT2:
        return SmileCardType.T2;
      case SmileCardT3:
        return SmileCardType.T3;
      default:
        return SmileCardType.Unknown;
    }
  }
}
