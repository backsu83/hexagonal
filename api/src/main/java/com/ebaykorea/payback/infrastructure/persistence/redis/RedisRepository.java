package com.ebaykorea.payback.infrastructure.persistence.redis;

import com.ebaykorea.payback.infrastructure.persistence.redis.entity.RedisCustomEntity;

public interface RedisRepository {

    RedisCustomEntity findKey(String key);
    void save(RedisCustomEntity redisCmd);
    void remove(String key);
    RedisCustomEntity update(RedisCustomEntity redisCmd);
}
