package com.ebaykorea.payback.config.persistence.redis;

import com.ebaykorea.payback.config.properties.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.time.Duration;

@EnableCaching
@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {

    private final RedisProperties properties;
    private final RedisConnectConfig redisConfig;

    public RedisCacheConfig(RedisProperties properties, RedisConnectConfig redisConfig) {
        this.properties = properties;
        this.redisConfig = redisConfig;
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(redisConfig.lettuceConnectionFactory());
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(properties.getTimeout()));
        builder.cacheDefaults(configuration);
        return builder.build();
    }
}
