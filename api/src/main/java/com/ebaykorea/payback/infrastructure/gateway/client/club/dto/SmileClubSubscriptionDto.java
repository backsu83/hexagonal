package com.ebaykorea.payback.infrastructure.gateway.client.club.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public final class SmileClubSubscriptionDto {
  private Long unifyMasterId;
  private String membershipGrade;
  private String payCycleType;
  private String partnerId;
}
