package com.ebaykorea.payback.core.gateway;

import com.ebaykorea.payback.core.domain.entity.order.KeyMap;

public interface TransactionGateway {
  KeyMap getKeyMap(String txKey, String orderKey);
}
