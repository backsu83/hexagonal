package com.ebaykorea.payback.infrastructure.gateway;

import com.ebaykorea.payback.core.domain.entity.cashback.member.Club;
import com.ebaykorea.payback.core.gateway.ClubGateway;
import com.ebaykorea.payback.infrastructure.gateway.client.club.ClubApiClient;
import com.ebaykorea.payback.infrastructure.gateway.mapper.ClubGatewayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubGatewayImpl implements ClubGateway {
  private final ClubApiClient clubApiClient;
  private final ClubGatewayMapper clubGatewayMapper;

  @Override
  public Optional<Club> findMemberSynopsis(String custNo) {
    return Optional.of(clubGatewayMapper
        .map(clubApiClient
            .getMemberSynopsis(custNo)
            .getData()));
  }
}
