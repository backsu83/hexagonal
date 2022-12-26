package com.ebaykorea.payback.core.domain.constant;

import com.ebaykorea.payback.util.PaybackEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public enum ShopType {
  Unknown(null),
  SmileDelivery("SD"),
  SmileFresh("SF");

  private String code;

  private static final transient Map<String, ShopType> map = PaybackEnums.reverseMap(ShopType.class, ShopType::getCode);

  public static ShopType codeOf(final String code) {
    return map.getOrDefault(code, Unknown);
  }
}
