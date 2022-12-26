package com.ebaykorea.payback.util;

public class PaybackObjects {
  public static <T> T orElse(T nullable, T defaultValue) {
    return isNull(nullable) ? defaultValue : nullable;
  }

  public static <T> boolean isNull(T nullable) {
    return nullable == null;
  }
}
