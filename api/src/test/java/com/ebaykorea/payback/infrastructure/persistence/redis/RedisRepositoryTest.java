package com.ebaykorea.payback.infrastructure.persistence.redis;

import com.ebaykorea.payback.infrastructure.persistence.redis.RedisRepositoryImpl;
import com.ebaykorea.payback.infrastructure.persistence.redis.support.GsonUtils;
import com.ebaykorea.payback.infrastructure.persistence.redis.support.RedisCrudException;
import com.ebaykorea.payback.config.test.RedisTestConfiguration;
import com.ebaykorea.payback.infrastructure.persistence.redis.entity.RedisCustomEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import spock.lang.Ignore;

import java.time.LocalDateTime;

//TODO: integration test로 이동 및 명확한 테스트 코드로 변경 필요 (+local 세팅 의존 없이)
@DataRedisTest
@Import(RedisTestConfiguration.class)
@Disabled
public class RedisRepositoryTest {

    @Autowired
    RedisRepositoryImpl repository;

    @Test
    void findByKey() {
        RedisCustomEntity byKey = repository.findKey("10005");
        System.out.println(byKey.getRefreshTime());
        System.out.println(GsonUtils.toJson(byKey));
    }

    @Test
    void save() {
        RedisCustomEntity entity = RedisCustomEntity
                .builder()
                .id("10006")
                .amount(1000L)
                .refreshTime(LocalDateTime.now())
                .build();
        repository.save(entity);
    }

    @Test()
    void remove() {
        repository.remove("10002");
        Assertions.assertThrows(RedisCrudException.class , () ->{
            repository.findKey("10002");
        });
    }

    @Test
    void update() {
        RedisCustomEntity entity = RedisCustomEntity
                .builder()
                .id("10000")
                .amount(11111L)
                .refreshTime(LocalDateTime.now())
                .build();
        repository.update(entity);
    }
}