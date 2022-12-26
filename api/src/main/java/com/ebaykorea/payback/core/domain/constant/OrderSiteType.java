package com.ebaykorea.payback.core.domain.constant;

import com.ebaykorea.payback.util.PaybackEnums;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public enum OrderSiteType {
  Unknown(null, ""),
  Gmarket(0, "G"),
  G9(1, "G9"), //TODO: retire 대상
  G9Short(9, "9"); //TODO: retire 대상

  private final Integer code;
  private final String shortCode;

  private static transient Map<Integer, OrderSiteType> map = PaybackEnums.reverseMap(OrderSiteType.class, OrderSiteType::getCode);

  @JsonCreator
  public static OrderSiteType forValue(Integer value) {
    return map.getOrDefault(value, Unknown);
  }

  @JsonValue
  public Integer toValue() {
    return this.code;
  }
}
