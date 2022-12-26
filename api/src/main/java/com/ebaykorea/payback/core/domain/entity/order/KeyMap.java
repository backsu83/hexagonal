package com.ebaykorea.payback.core.domain.entity.order;

import com.ebaykorea.payback.core.exception.PaybackException;
import lombok.Value;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.ebaykorea.payback.core.exception.PaybackExceptionCode.DOMAIN_ENTITY_001;
import static com.ebaykorea.payback.util.PaybackCollections.orEmpty;
import static com.ebaykorea.payback.util.PaybackStrings.isBlank;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toUnmodifiableMap;

@Value
public class KeyMap {
  String txKey;
  String orderKey;
  long packNo;
  List<OrderUnitKey> orderUnitKeys;

  public static KeyMap of(
      final String txKey,
      final String orderKey,
      final long packNo,
      final List<OrderUnitKey> orderUnitKeys) {
    return new KeyMap(txKey, orderKey, packNo, orderUnitKeys);
  }

  private KeyMap(
      final String txKey,
      final String orderKey,
      final long packNo,
      final List<OrderUnitKey> orderUnitKeys) {
    this.txKey = txKey;
    this.orderKey = orderKey;
    this.packNo = packNo;
    this.orderUnitKeys = orderUnitKeys;

    validate();
  }

  public void validate() {
    if (isBlank(txKey)) {
      throw new PaybackException(DOMAIN_ENTITY_001, "txKey가 없습니다");
    }
    if (isBlank(orderKey)) {
      throw new PaybackException(DOMAIN_ENTITY_001, "orderKey가 없습니다");
    }
    if (packNo <= 0L) {
      throw new PaybackException(DOMAIN_ENTITY_001, "packNo는 0보다 커야 합니다");
    }
    if (orEmpty(orderUnitKeys).isEmpty()) {
      throw new PaybackException(DOMAIN_ENTITY_001, "orderUnitKeys 정보가 없습니다");
    }
  }

  public Map<String, OrderUnitKey> orderUnitKeyMap() {
    return orderUnitKeys.stream()
        .collect(toUnmodifiableMap(OrderUnitKey::getOrderUnitKey, identity()));
  }

  public Optional<OrderUnitKey> findBy(final String orderUnitKey) {
    return Optional.ofNullable(orderUnitKeyMap().get(orderUnitKey));
  }

  private Map<Long, OrderUnitKey> orderUnitKeyMapByBuyOrderNo() {
    return orderUnitKeys.stream()
        .collect(toUnmodifiableMap(OrderUnitKey::getBuyOrderNo, identity()));
  }

  public Optional<OrderUnitKey> findByOrderNo(final long buyOrderNo) {
    return Optional.ofNullable(orderUnitKeyMapByBuyOrderNo().get(buyOrderNo));
  }
}
