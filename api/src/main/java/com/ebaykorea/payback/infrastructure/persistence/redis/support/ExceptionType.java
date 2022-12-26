package com.ebaykorea.payback.infrastructure.persistence.redis.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionType {

    REDIS_NOT_MATCHED_KEY("레디스조회 매칭 실패"),
    REDIS_EXIST_ALREADY_KEY("레디스 이미 생성된 데이터");

    private String message;

}
