package com.ebaykorea.payback.infrastructure.gateway.client.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * feign client 로그 레벨 변경 ( debug -> info )
 * objectMapper 적용
 */
@Configuration
public class DefaultFeignConfig {

  @Autowired
  private ObjectMapper objectMapper;

  @Bean
  public FeignRequestLogging customFeignRequestLogging() {
    return new FeignRequestLogging();
  }

  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.BASIC;
  }

  @Bean
  public ObjectMapper insensitiveMapper() {
    ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //  Sender 에만 있고, Receiver 에는 없는 컬럼 무시 옵션

    return mapper;
  }

  @Bean
  public Decoder feignDecoder(
      @Qualifier("insensitiveMapper") ObjectMapper mapper
  ) {
    return new NullSafeDecoder(new JacksonDecoder(mapper));
  }
}
