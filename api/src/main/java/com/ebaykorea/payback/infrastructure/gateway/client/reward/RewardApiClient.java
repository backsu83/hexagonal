package com.ebaykorea.payback.infrastructure.gateway.client.reward;

import com.ebaykorea.payback.infrastructure.gateway.client.reward.dto.*;
import com.ebaykorea.payback.infrastructure.gateway.client.config.DefaultFeignConfig;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(
    value = "rewardApiClient",
    url = "${apis.reward.url}",
    configuration = DefaultFeignConfig.class
)
public interface RewardApiClient {

  @Retry(name = "retryApi")
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/api/Read/V2/CashbackReward"
  )
  Optional<RewardBaseResponse<CashbackRewardResponseDto>> getCashbackReward(@RequestBody final CashbackRewardRequestDto request);

  @RequestMapping(
      method = RequestMethod.POST,
      value = "api/Read/V2/CashbackRewardBackend",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  Optional<RewardBaseResponse<List<CashbackRewardBackendResponseDto>>> getCashbackRewardBackend(@RequestBody final CashbackRewardRequestDto request);

}
