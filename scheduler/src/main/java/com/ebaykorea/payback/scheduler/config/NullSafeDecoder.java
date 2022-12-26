package com.ebaykorea.payback.scheduler.config;

import static java.util.Collections.emptyList;

import feign.Response;
import feign.Types;
import feign.codec.Decoder;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NullSafeDecoder implements Decoder {

  final Decoder delegate;

  public NullSafeDecoder(Decoder delegate) {
    Objects.requireNonNull(delegate, "Decoder must not be null. ");
    this.delegate = delegate;
  }

  @Override
  public Object decode(Response response, Type type) throws IOException {
    final var decoded = delegate.decode(response, type);
    final Class<?> rawType = Types.getRawType(type);

    if (Optional.class.equals(rawType) && decoded == null) {
      return Optional.empty();
    }

    // Return Type List 인 경우, Null-Safe
    if (List.class.equals(rawType)) {
      return decoded == null ? emptyList() : decoded;
    }
    return decoded;
  }
}
