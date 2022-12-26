package com.ebaykorea.payback.infrastructure.gateway;

import com.ebaykorea.payback.core.domain.entity.cashback.member.Club;
import com.ebaykorea.payback.core.gateway.ClubGateway;
import com.ebaykorea.payback.infrastructure.persistence.redis.support.GsonUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class ClubGatewayImplTest {

    @Autowired
    ClubGateway gateway;

    @Test
    public void test() {
        Club memberSynopsis = gateway.findMemberSynopsis("103574394").get();
        System.out.println(GsonUtils.toJsonPretty(memberSynopsis));
    }
}