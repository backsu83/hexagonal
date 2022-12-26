package com.ebaykorea.payback.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("spring.redis")
public class RedisProperties {
    private String host;
    private Integer port;
    private Integer timeout;
}
