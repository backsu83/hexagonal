package com.ebaykorea.payback.infrastructure.gateway.mapper;

import com.ebaykorea.payback.core.domain.entity.payment.*;
import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.PaymentDto;
import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.PaymentMainDto;
import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.PaymentSubDto;
import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.auth.CardDto;
import com.ebaykorea.payback.infrastructure.gateway.client.payment.dto.auth.SmilePayDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PaymentGatewayMapper {

  default Payment map(final PaymentDto source) {
    return Payment.of(
        source.getPaymentSequence(),
        map(source.getMainPaymentMethod()),
        map(source.getSubPaymentMethods()),
        map(source.getAuthentications().getSmilePay()),
        map(source.getAuthentications().getCard())
    );
  }

  PaymentMethod map(PaymentMainDto source);

  List<PaymentMethodSub> map(List<PaymentSubDto> source);

  SmilePay map(SmilePayDto source);

  @Mapping(source = "source", target = "isManualPayment", qualifiedByName = "mapIsManualPayment")
  Card map(CardDto source);

  @Named("mapIsManualPayment")
  default boolean mapIsManualPayment(final CardDto source) {
    return Optional.ofNullable(source)
        .map(CardDto::getManualPayment)
        .isPresent();
  }
}
