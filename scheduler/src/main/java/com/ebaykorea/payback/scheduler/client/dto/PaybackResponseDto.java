package com.ebaykorea.payback.scheduler.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class PaybackResponseDto {

  private String code;
  private Body data;
  private String message;

  @Data
  @Builder
  @JsonInclude(Include.NON_NULL)
  public static class Body {
    private String orderKey;
    private String txKey;
  }
}
