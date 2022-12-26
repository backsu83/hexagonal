package com.ebaykorea.payback.scheduler.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.Logger;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class DefaultFeignConfig {

  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  public ObjectMapper defualtInsensitiveMapper(Jackson2ObjectMapperBuilder builder) {
    return builder
        .featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
        .featuresToEnable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
        .featuresToEnable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
        .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .build();
  }

  @Bean
  public Decoder defaultDecoder(@Qualifier("defualtInsensitiveMapper") ObjectMapper mapper) {
    return new NullSafeDecoder(new JacksonDecoder(mapper));
  }
}
