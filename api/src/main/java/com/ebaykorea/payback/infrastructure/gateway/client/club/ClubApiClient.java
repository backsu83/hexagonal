package com.ebaykorea.payback.infrastructure.gateway.client.club;

import com.ebaykorea.payback.infrastructure.gateway.client.club.dto.ClubBaseResponseDto;
import com.ebaykorea.payback.infrastructure.gateway.client.club.dto.ClubDataDto;
import com.ebaykorea.payback.infrastructure.gateway.client.config.DefaultFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "clubApiClient",
        url = "${apis.club.url}",
        configuration = DefaultFeignConfig.class
)
public interface ClubApiClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/member/gmkt/{cust-no}/synopsis",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ClubBaseResponseDto<ClubDataDto> getMemberSynopsis(@PathVariable(name="cust-no") final String buyerNo);

}
