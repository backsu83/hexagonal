package com.ebaykorea.payback.core.domain.entity.cashback.member;

import static com.ebaykorea.payback.core.exception.PaybackExceptionCode.DOMAIN_ENTITY_004;
import static com.ebaykorea.payback.core.exception.PaybackExceptionCode.DOMAIN_ENTITY_013;

import com.ebaykorea.payback.core.exception.PaybackException;
import com.ebaykorea.payback.util.PaybackStrings;
import java.util.Optional;
import lombok.Value;

@Value
public class Member {
  String buyerNo;
  String buyerId;
  String userKey;
  boolean member;
  boolean smileClubMember;
  Club club;

  public static Member of(
      final String buyerNo,
      final String buyerId,
      final String userKey,
      final boolean member,
      final boolean smileClubMember,
      final Club club
  ) {
    return new Member(buyerNo, buyerId, userKey, member, smileClubMember, club);
  }

  private Member(
      final String buyerNo,
      final String buyerId,
      final String userKey,
      final boolean member,
      final boolean smileClubMember,
      final Club club
  ) {
    this.buyerNo = buyerNo;
    this.buyerId = buyerId;
    this.userKey = userKey;
    this.member = member;
    this.smileClubMember = smileClubMember;
    this.club = club;

    validate();
  }

  private void validate() {
    if (PaybackStrings.isBlank(buyerNo)) {
      throw new PaybackException(DOMAIN_ENTITY_004 , "buyerNo");
    }
    if (PaybackStrings.isBlank(buyerId)) {
      throw new PaybackException(DOMAIN_ENTITY_004 , "buyerId");
    }
    if (!member) {
      throw new PaybackException(DOMAIN_ENTITY_013);
    }
  }

  public Optional<Club> findClub() {
    return Optional.ofNullable(club);
  }

  public boolean hasClub() {
    return findClub().isPresent();
  }
}
