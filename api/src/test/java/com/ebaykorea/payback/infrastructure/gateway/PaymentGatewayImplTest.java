package com.ebaykorea.payback.infrastructure.gateway;

import com.ebaykorea.payback.core.gateway.PaymentGateway;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class PaymentGatewayImplTest {

    @Autowired
    PaymentGateway paymentGateway;

    @Test
    void findPaymentRecord() {
        paymentGateway.getPaymentRecordAsync(38876501L);
    }
}