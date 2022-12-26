package com.ebaykorea.payback.infrastructure.gateway;

import static com.ebaykorea.payback.core.exception.PaybackExceptionCode.API_GATEWAY_002;

import com.ebaykorea.payback.core.domain.entity.order.KeyMap;
import com.ebaykorea.payback.core.exception.PaybackException;
import com.ebaykorea.payback.core.gateway.TransactionGateway;
import com.ebaykorea.payback.infrastructure.gateway.client.transaction.TransactionApiClient;
import com.ebaykorea.payback.infrastructure.gateway.client.transaction.dto.KeyMapResponseDto;
import com.ebaykorea.payback.infrastructure.gateway.mapper.TransactionGatewayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class TransactionGatewayImpl implements TransactionGateway {

  private final TransactionApiClient transactionApiClient;
  private final TransactionGatewayMapper transactionGatewayMapper;

  private static final String QUERY_FIELDS = "orders";

  @Override
  public KeyMap getKeyMap(final String txKey, final String orderKey) {
    final var keyMaps = transactionApiClient.findKeyMaps(txKey, orderKey, QUERY_FIELDS)
        .map(KeyMapResponseDto::getOrders)
        .orElse(Collections.emptyList());
    final var firstKeyMap = keyMaps.stream().findFirst()
        .orElseThrow(() -> new PaybackException(API_GATEWAY_002, "값 없음"));

    final var orderUnitKeys = keyMaps.stream()
        .map(transactionGatewayMapper::mapToOrderUnitKey)
        .collect(Collectors.toUnmodifiableList());

    return KeyMap.of(txKey, orderKey, firstKeyMap.getPackNo(), orderUnitKeys);
  }
}
