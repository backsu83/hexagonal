
package com.ebaykorea.payback.consumer.listener;


import com.ebaykorea.payback.consumer.domain.OrderCompletedEvent;
import com.ebaykorea.payback.consumer.domain.PaybackService;
import java.util.concurrent.CountDownLatch;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCompletedEventListener {

  @KafkaListener(
      topics = {"${kafka.consumer.topic.OrderCompletedEvent}"}
  )
  public void consume(@Payload @Valid final OrderCompletedEvent orderCompletedEvent) {
    log.info("listener payload : '{}' '{}'",
        orderCompletedEvent.getOrderKey() ,
        orderCompletedEvent.getTxKey());
    //TODO 주문완료 이벤트를 구독하여 paybackService 실행
  }
}
