package com.ebaykorea.payback.consumer.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaConsumerFactoryCustomizer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig implements KafkaListenerConfigurer {

  private final LocalValidatorFactoryBean validator;

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
      ConsumerFactory<String, String> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    factory.setMessageConverter(new StringJsonMessageConverter());
    return factory;
  }

  @Bean
  public ConsumerFactory<String, String> consumerFactory(KafkaProperties kafkaProperties
      , ObjectProvider<DefaultKafkaConsumerFactoryCustomizer> customizers ) {
    var factory = new DefaultKafkaConsumerFactory<>(
        kafkaProperties.buildConsumerProperties(),
        new ErrorHandlingDeserializer<>(new StringDeserializer()),
        new ErrorHandlingDeserializer<>(new StringDeserializer()));
    customizers.orderedStream().forEach((customizer) -> customizer.customize(factory));
    return factory;
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(
      ProducerFactory<String, String> producerFactory) {
    var kafkaTemplate = new KafkaTemplate<>(producerFactory);
    kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());
    return kafkaTemplate;
  }

  @Override
  public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
    registrar.setValidator(this.validator);
  }

  //TODO 에러 핸들링을 통한 deadletter 발행
}

