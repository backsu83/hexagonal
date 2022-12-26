package com.ebaykorea.payback.infrastructure.gateway.client.transaction;

import com.ebaykorea.payback.infrastructure.gateway.client.config.DefaultFeignConfig;
import com.ebaykorea.payback.infrastructure.gateway.client.transaction.dto.KeyMapDto;
import com.ebaykorea.payback.infrastructure.gateway.client.transaction.dto.KeyMapResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(
    value = "transactionApiClient",
    url = "${apis.transaction.url}",
    configuration = DefaultFeignConfig.class
)
public interface TransactionApiClient {
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/key/maps/{tx-key}",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  Optional<KeyMapResponseDto> findKeyMaps(
      @PathVariable(value = "tx-key") final String txKey,
      @RequestParam("order-key")final String orderKey,
      @RequestParam("fields")final String fields);
}
