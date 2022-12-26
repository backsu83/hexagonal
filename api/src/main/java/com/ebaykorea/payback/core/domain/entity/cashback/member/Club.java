package com.ebaykorea.payback.core.domain.entity.cashback.member;

import lombok.Value;

@Value
public class Club {
  String partnerId;
  String payCycleType;
  String membershipGrade;
}
