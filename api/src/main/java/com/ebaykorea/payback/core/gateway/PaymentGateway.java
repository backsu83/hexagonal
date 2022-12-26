package com.ebaykorea.payback.core.gateway;

import com.ebaykorea.payback.core.domain.entity.payment.Payment;

import java.util.concurrent.CompletableFuture;

public interface PaymentGateway {

    CompletableFuture<Payment> getPaymentRecordAsync(Long paySeq);
}
