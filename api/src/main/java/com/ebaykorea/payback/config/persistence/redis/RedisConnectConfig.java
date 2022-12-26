package com.ebaykorea.payback.config.persistence.redis;

import com.ebaykorea.payback.config.properties.RedisProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.time.Duration;

@Configuration
@EnableRedisRepositories(value = "com.ebaykorea.payback.core.persistence.redis")
public class RedisConnectConfig {

    private final RedisProperties properties;
    private final ObjectMapper objectMapper;

    public RedisConnectConfig(@Qualifier("redisProperties") RedisProperties properties, ObjectMapper objectMapper) {
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(3L))
                .build();
        RedisStandaloneConfiguration redisStandaloneConfiguration
                = new RedisStandaloneConfiguration(properties.getHost(), properties.getPort());
        return new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory());
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        return template;
    }
}