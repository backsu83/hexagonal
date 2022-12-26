package com.ebaykorea.payback.infrastructure.persistence.redis.support;

public class RedisCrudException extends RuntimeException {
    public RedisCrudException(String message) {
        super(message);
    }
}
