package com.ebaykorea.payback.core.repository;

import com.ebaykorea.payback.core.domain.entity.cashback.PayCashback;
import com.ebaykorea.payback.core.domain.entity.order.KeyMap;

public interface PayCashbackRepository {
  void save(PayCashback payCashback);

  boolean isDuplicatedCashback(KeyMap keyMap);
}
