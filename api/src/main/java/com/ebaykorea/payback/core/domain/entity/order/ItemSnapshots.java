package com.ebaykorea.payback.core.domain.entity.order;

import static com.ebaykorea.payback.core.exception.PaybackExceptionCode.DOMAIN_ENTITY_001;
import static com.ebaykorea.payback.util.PaybackCollections.toMapBy;

import com.ebaykorea.payback.core.exception.PaybackException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.Value;

@Value
public class ItemSnapshots {

  Map<String, ItemSnapshot> itemSnapshotMap;

  public static ItemSnapshots of(final List<ItemSnapshot> itemSnapshots) {
    return new ItemSnapshots(itemSnapshots.stream().collect(toMapBy(ItemSnapshot::getSnapshotKey)));
  }

  private ItemSnapshots(final Map<String, ItemSnapshot> itemSnapshotMap) {
    this.itemSnapshotMap = itemSnapshotMap;

    validate();
  }

  // 불변식
  private void validate() {
    if (itemSnapshotMap == null || itemSnapshotMap.isEmpty()) {
      throw new PaybackException(DOMAIN_ENTITY_001, "itemSnapshots 정보가 없습니다");
    }
  }

  public Optional<ItemSnapshot> findBy(final String snapshotKey) {
    return Optional.ofNullable(itemSnapshotMap.get(snapshotKey));
  }
}
