package com.ebaykorea.payback.core.gateway;

import java.util.Optional;

public interface UserGateway {
  Optional<String> findUserKey(String buyerNo);
}
