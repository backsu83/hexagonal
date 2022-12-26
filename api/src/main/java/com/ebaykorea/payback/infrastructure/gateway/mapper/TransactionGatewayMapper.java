package com.ebaykorea.payback.infrastructure.gateway.mapper;

import com.ebaykorea.payback.core.domain.entity.order.OrderUnitKey;
import com.ebaykorea.payback.infrastructure.gateway.client.transaction.dto.KeyMapDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface TransactionGatewayMapper {

  @Mapping(source = "orderNo", target = "buyOrderNo")
  OrderUnitKey mapToOrderUnitKey(KeyMapDto source);
}
