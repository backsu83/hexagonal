package com.ebaykorea.payback.core.domain.entity.order;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Buyer {
  String buyerNo;
  String buyerId;
  boolean member;
  boolean smileClubMember;
}
