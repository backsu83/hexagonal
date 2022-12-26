package com.ebaykorea.payback.infrastructure.gateway.client.order;

import com.ebaykorea.payback.infrastructure.gateway.client.config.DefaultFeignConfig;
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.ItemSnapshotDto;
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.OrderQueryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(
    value = "orderApiClient",
    url = "${apis.order.url}",
    configuration = DefaultFeignConfig.class
)
public interface OrderApiClient {
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/orders/{order-key}",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  Optional<OrderQueryResponseDto> findOrder(@PathVariable(name="order-key") final String orderKey, @RequestParam("fields") final String fields);

  // 상품 스냅샷 (추후 주문이 아닌 상품쪽에서 제공 될 수 있음)
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/snapshot/item/items",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  List<ItemSnapshotDto> findItemSnapshots(@RequestParam("snapshotKeys")final List<String> snapshotKeys);
}
