package com.ebaykorea.payback.core.service;

import com.ebaykorea.payback.core.domain.entity.cashback.member.Member;
import com.ebaykorea.payback.core.domain.entity.cashback.member.Club;
import com.ebaykorea.payback.core.domain.entity.order.Buyer;
import com.ebaykorea.payback.core.gateway.ClubGateway;
import com.ebaykorea.payback.core.gateway.UserGateway;
import com.ebaykorea.payback.util.PaybackStrings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final ClubGateway clubGateway;
  private final UserGateway userGateway;

  public CompletableFuture<Member> getMemberAsync(final Buyer buyer) {
    return CompletableFuture.supplyAsync(() -> getMember(buyer));
  }

  Member getMember(final Buyer buyer) {
    final var clubAsync = getClubAsync(buyer.getBuyerNo());
    final var userKeyAsync = getUserKeyAsync(buyer.getBuyerNo());

    final var club = clubAsync.join();
    final var userKey = userKeyAsync.join();

    return Member.of(
        buyer.getBuyerNo(),
        buyer.getBuyerId(),
        userKey,
        buyer.isMember(),
        buyer.isSmileClubMember(),
        club);
  }

  private CompletableFuture<Club> getClubAsync(final String buyerNo) {
    return CompletableFuture.supplyAsync(() -> clubGateway.findMemberSynopsis(buyerNo))
        .thenApply(club -> club.orElse(null));
  }

  private CompletableFuture<String> getUserKeyAsync(final String buyerNo) {
    return CompletableFuture.supplyAsync(() -> userGateway.findUserKey(buyerNo))
        .thenApply(userKey -> userKey.orElse(PaybackStrings.EMPTY));
  }
}
