package com.ebaykorea.payback.api.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonExceptionResponseDto {

  String exceptionMessage;
  String exceptionCode;
  Object exceptionInfo;

}
