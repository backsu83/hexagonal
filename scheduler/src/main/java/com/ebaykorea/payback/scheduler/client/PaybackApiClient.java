package com.ebaykorea.payback.scheduler.client;

import com.ebaykorea.payback.scheduler.client.dto.PaybackRequestDto;
import com.ebaykorea.payback.scheduler.client.dto.PaybackResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
    value = "paybackApiClient",
    url = "${apis.payback.url}"
)
public interface PaybackApiClient {

  @RequestMapping(
      method = RequestMethod.POST,
      value = "/api/cashbacks",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  PaybackResponseDto saveCashbacks(final @RequestBody PaybackRequestDto request);
}
