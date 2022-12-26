package com.ebaykorea.payback.core.domain.constant;

import com.ebaykorea.payback.util.PaybackEnums;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public enum CashbackType {
  Unknown(null, ""),
  Item(1, "I"),
  SmilePay(2, "P"),
  Seller(3, "S"),
  Cart(4, "4"),
  ChargePay(5, "A"),
  ClubDay(6, "D");

  private final Integer code;
  private final String dbCode;

  private static transient Map<Integer, CashbackType> map = PaybackEnums.reverseMap(CashbackType.class, CashbackType::getCode);

  @JsonCreator
  public static CashbackType forValue(Integer value) {
    return map.getOrDefault(value, Unknown);
  }

  @JsonValue
  public Integer toValue() {
    return this.code;
  }
}
