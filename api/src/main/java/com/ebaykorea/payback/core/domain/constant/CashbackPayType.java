package com.ebaykorea.payback.core.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 캐쉬백 페이 타입 */
@AllArgsConstructor
@Getter
public enum CashbackPayType {
  Unknown(""),
  FixRate("P"),
  FixAmount("W");

  private String code;
}
