package com.ebaykorea.payback.infrastructure.gateway.client.club.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public final class ClubBaseResponseDto<T> {
  private int resultCode;
  private String message;
  private T data;

  public Optional<T> findData() {
    return Optional.ofNullable(data);
  }
  public boolean isSuccess() {
    return resultCode == 0;
  }
}
