package com.ebaykorea.payback.core.domain.constant;

import com.ebaykorea.payback.util.PaybackEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public enum SmileCardType {
  Unknown(""),
  T0("T0"),
  T1("T1"),
  T2("T2"),
  T3("T3");

  private String code;

  private static final transient Map<String, SmileCardType> map = PaybackEnums.reverseMap(SmileCardType.class, SmileCardType::getCode);

  public static SmileCardType codeOf(final String code) {
    return map.getOrDefault(code, Unknown);
  }
}
