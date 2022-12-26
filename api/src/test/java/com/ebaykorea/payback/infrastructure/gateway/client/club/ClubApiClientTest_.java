package com.ebaykorea.payback.infrastructure.gateway.client.club;

import com.ebaykorea.payback.infrastructure.gateway.client.club.dto.ClubBaseResponseDto;
import com.ebaykorea.payback.infrastructure.gateway.client.club.dto.ClubDataDto;
import com.ebaykorea.payback.infrastructure.persistence.redis.support.GsonUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class ClubApiClientTest_ {

    @Autowired
    ClubApiClient client;

    @Test
    void getMemberSynopsis() {
        ClubBaseResponseDto<ClubDataDto> memberSynopsis = client.getMemberSynopsis("103574394");
        System.out.println(GsonUtils.toJsonPretty(memberSynopsis));

    }
}