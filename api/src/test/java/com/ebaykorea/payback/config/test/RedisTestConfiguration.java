package com.ebaykorea.payback.config.test;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.util.SocketUtils;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@TestConfiguration
public class RedisTestConfiguration {
  private RedisServer redisServer;

  @Bean
  public int redisPort() {
    return SocketUtils.findAvailableTcpPort();
  }

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory("localhost", redisPort());
  }

  @PostConstruct
  public void redisServer() {
    redisServer = new RedisServer(redisPort());
    redisServer.start();
  }

  @PreDestroy
  public void stopRedis() {
    if (redisServer != null) {
      redisServer.stop();
    }
  }
}
