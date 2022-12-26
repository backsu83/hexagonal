package com.ebaykorea.payback.infrastructure.gateway.client.payment;

import com.ebaykorea.payback.infrastructure.gateway.client.config.DefaultFeignConfig;
import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        value = "paymentApiClient",
        url = "${apis.payment.url}",
        configuration = DefaultFeignConfig.class
)
public interface PaymentApiClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/payment-records/{paySeq}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    PaymentDto findPaymentRecord(@PathVariable(name="paySeq") Long paySeq);

}
