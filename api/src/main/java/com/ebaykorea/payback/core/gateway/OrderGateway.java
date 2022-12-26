package com.ebaykorea.payback.core.gateway;

import com.ebaykorea.payback.core.domain.entity.order.ItemSnapshots;
import com.ebaykorea.payback.core.domain.entity.order.Order;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface OrderGateway {
  Order getOrder(String orderKey);
  CompletableFuture<ItemSnapshots> getItemSnapshotAsync(List<String> itemSnapshotKey);
}
