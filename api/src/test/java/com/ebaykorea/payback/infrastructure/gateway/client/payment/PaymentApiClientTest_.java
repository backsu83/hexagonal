package com.ebaykorea.payback.infrastructure.gateway.client.payment;

import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.PaymentDto;
import com.ebaykorea.payback.infrastructure.persistence.redis.support.GsonUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
class PaymentApiClientTest_ {

    @Autowired
    PaymentApiClient client;

    @Test
    void findPaymentRecord() {
        PaymentDto paymentRecord = client.findPaymentRecord(38876501L);
        System.out.println(GsonUtils.toJsonPretty(paymentRecord));
    }
}