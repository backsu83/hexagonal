package com.ebaykorea.payback.infrastructure.gateway;

import com.ebaykorea.payback.core.gateway.UserGateway;
import com.ebaykorea.payback.infrastructure.gateway.client.member.QuiltApiClient;
import com.ebaykorea.payback.infrastructure.gateway.client.member.dto.QuiltBaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

  private final QuiltApiClient quiltApiClient;

  @Override
  public Optional<String> findUserKey(String buyerNo) {
    return quiltApiClient.findUserKey(buyerNo)
        .map(QuiltBaseResponse::findSuccessData)
        .filter(Optional::isPresent)
        .map(Optional::get);
  }
}
