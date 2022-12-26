package com.ebaykorea.payback.scheduler.support;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
  public static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
  public static final String dateFormat = "yyyy-MM-dd";

  public static final DateTimeFormatter LOCAL_DATE_FORMATTER  = DateTimeFormatter.ofPattern(dateFormat)
      .withZone(ZoneId.of("Asia/Seoul"));

  public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER  = DateTimeFormatter.ofPattern(dateTimeFormat)
      .withZone(ZoneId.of("Asia/Seoul"));

  public static Timestamp from(final Instant instant) {
    return instant == null ? null : Timestamp.from(instant);
  }
}
