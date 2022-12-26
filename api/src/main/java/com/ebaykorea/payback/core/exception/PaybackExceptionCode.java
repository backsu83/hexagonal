package com.ebaykorea.payback.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaybackExceptionCode {

  DOMAIN_ENTITY_001("불변식 오류: {0}"),
  DOMAIN_ENTITY_002("{0}를 찾을 수 없습니다"),
  DOMAIN_ENTITY_004("{0} 값이 없습니다"),
  DOMAIN_ENTITY_008("{0}는 null일 수 없습니다"),
  DOMAIN_ENTITY_010("{0} 정보가 없습니다"),
  DOMAIN_ENTITY_011("결제수단이 없습니다"),
  DOMAIN_ENTITY_013("캐시백 적립을 할 수 없습니다"),

  API_GATEWAY_001("처리 실패 : {0}"),
  API_GATEWAY_002("API 조회 : {0}");

  private final String message;
}
