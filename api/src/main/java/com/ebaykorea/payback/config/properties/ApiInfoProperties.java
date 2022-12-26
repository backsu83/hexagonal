package com.ebaykorea.payback.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;

@Configuration
@ConfigurationProperties("saturn.application.api-info")
@Getter
@Setter
public class ApiInfoProperties {
  private String name;
  private String apiPackage;
  @Valid
  private Version version = new Version();
  @Getter
  @Setter
  public static class Version {
    private String major;
    private String full;
  }
}
