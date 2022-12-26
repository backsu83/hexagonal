package com.ebaykorea.payback.scheduler.service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProcessType {
  COMPLETED("완료"),
  FAIL("실패"),
  TARGET("대상"),
  ALREADY("이미완료");

  private String message;
}
