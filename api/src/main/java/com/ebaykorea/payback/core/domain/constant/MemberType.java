package com.ebaykorea.payback.core.domain.constant;

import com.ebaykorea.payback.util.PaybackEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public enum MemberType {
  Unknown(""),
  NormalMember("NormalMember"),
  SimpleMember("SimpleMember"),
  NonMember("NonMember");

  private final String code;

  private static final transient Map<String, MemberType> map = PaybackEnums.reverseMap(MemberType.class, MemberType::getCode);

  public static MemberType codeOf(String code) {
    return map.getOrDefault(code, Unknown);
  }
}
