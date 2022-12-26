package com.ebaykorea.payback.infrastructure.gateway.client.reward.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class RewardBaseResponse<T> {

  private final static String ResponseOK = "0000";

  RewardBaseReturn returnBase;

  T result;

  public Optional<T> findSuccessData() {
    if (!isSuccess()) {
      return Optional.empty();
    }
    return Optional.ofNullable(result);
  }

  private boolean isSuccess() {
    return returnBase != null && ResponseOK.equals(returnBase.getReturnCode());
  }

}
