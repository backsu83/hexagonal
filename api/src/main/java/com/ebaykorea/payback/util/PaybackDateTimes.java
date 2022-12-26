package com.ebaykorea.payback.util;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PaybackDateTimes {

  public static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
  public static final String dateFormat = "yyyy-MM-dd";

  public static final DateTimeFormatter LOCAL_DATE_FORMATTER  = DateTimeFormatter.ofPattern(dateFormat)
      .withZone(ZoneId.of("Asia/Seoul"));

  public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER  = DateTimeFormatter.ofPattern(dateTimeFormat)
      .withZone(ZoneId.of("Asia/Seoul"));
}
