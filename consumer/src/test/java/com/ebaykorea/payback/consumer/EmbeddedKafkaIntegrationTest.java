package com.ebaykorea.payback.consumer;



import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ebaykorea.payback.consumer.domain.OrderCompletedEvent;
import com.ebaykorea.payback.consumer.listener.OrderCompletedEventListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

@SpringBootTest
@EmbeddedKafka(partitions = 1,
    topics = "${kafka.consumer.topic.OrderCompletedEvent}",
    brokerProperties = {
        "listeners=PLAINTEXT://localhost:9092"
    },
    ports = { 9092 })
public class EmbeddedKafkaIntegrationTest {

  @Autowired
  EmbeddedKafkaBroker embeddedKafkaBroker;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  ConsumerFactory<String, String> consumerFactory;

  @Autowired
  KafkaTemplate kafkaTemplate;

  @Autowired
  OrderCompletedEventListener consumer;

  public static final String TOPIC = "bz_carbon_order_completed_gmkt";

  @Test
  void testKafkaTopic() throws Exception {

    OrderCompletedEvent event = OrderCompletedEvent.builder()
        .orderKey("orderKey")
        .txKey("txKey")
        .build();
    final var message = objectMapper.writeValueAsString(event);

    final var consumer = consumerFactory.createConsumer("order-gmkt-group", "test");
    embeddedKafkaBroker.consumeFromEmbeddedTopics(consumer, TOPIC);
    kafkaTemplate.send(new ProducerRecord<>(TOPIC, 0, null, message));

    var record = KafkaTestUtils.getSingleRecord(consumer, TOPIC, 10000);
    var eventMessage = (new StringJsonMessageConverter()).toMessage(
        record, null, null, OrderCompletedEvent.class
    );
    var consumedEvent = (OrderCompletedEvent) eventMessage.getPayload();

    assertEquals(event.getOrderKey() , consumedEvent.getOrderKey());
    assertEquals(event.getTxKey() , consumedEvent.getTxKey());
  }
}
