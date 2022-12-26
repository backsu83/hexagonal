package com.ebaykorea.payback.infrastructure.gateway.client.reward.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Value
@Builder
@JsonDeserialize(builder = RewardBaseReturn.RewardBaseReturnBuilder.class)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class RewardBaseReturn {
    /** 결과 코드 */
    String returnCode;
    /** 결과 값 */
    String returnValue;
    /** 에러 메시지 */
    String errorMessage;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RewardBaseReturnBuilder {

    }
}
