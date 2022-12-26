package com.ebaykorea.payback.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toUnmodifiableMap;

public class PaybackCollections {
  public static <T> List<T> orEmpty(final List<T> list) {
    if (list == null) {
      return Collections.emptyList();
    }
    return list;
  }

  public static <T> Stream<T> orEmptyStream(final List<T> list) {
    if (list == null) {
      return Stream.empty();
    }
    return list.stream();
  }

  public static <T> boolean isEmpty(final Collection<T> collection) {
    return collection == null || collection.isEmpty();
  }

  public static <K, V> Collector<V, ?, Map<K, V>> toMapBy(final Function<V, K> keyGetter) {
    return toUnmodifiableMap(keyGetter, identity(), (l, r) -> l);
  }
}
