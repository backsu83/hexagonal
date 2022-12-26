package com.ebaykorea.payback.infrastructure.persistence.redis;


import com.ebaykorea.payback.infrastructure.persistence.redis.support.ExceptionType;
import com.ebaykorea.payback.infrastructure.persistence.redis.support.RedisCrudException;
import com.ebaykorea.payback.infrastructure.persistence.redis.entity.RedisCustomEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisRepositoryImpl implements RedisRepository {

    private final RedisJpaRepository repository;

    @Override
    public RedisCustomEntity findKey(String key) {
        Optional<RedisCustomEntity> redisCmd
                = Optional.ofNullable(repository
                .findById(key)
                .orElseThrow(() -> new RedisCrudException(ExceptionType.REDIS_NOT_MATCHED_KEY.name())));
        return redisCmd.get();
    }

    @Override
    public void save(RedisCustomEntity entity) {
        Optional<RedisCustomEntity> object = repository.findById(entity.getId());
        if(object.isPresent()) {
            throw new RedisCrudException(ExceptionType.REDIS_EXIST_ALREADY_KEY.name());
        }
        repository.save(entity);
    }

    @Override
    public void remove(String key) {
        RedisCustomEntity object = findKey(key);
        repository.delete(object);
    }

    @Override
    public RedisCustomEntity update(RedisCustomEntity entity) {
        Optional<RedisCustomEntity> object = repository.findById(entity.getId());
        if(object.isPresent()) {
            entity = repository.save(object.get());
        }
        return entity;
    }
}
