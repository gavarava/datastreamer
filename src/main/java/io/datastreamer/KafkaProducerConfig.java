package io.datastreamer;

import io.datastreamer.adapters.kafka.OrderTotalsProducer;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerConfig {

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return props;
  }

  @Bean
  public DefaultKafkaProducerFactory<String, String> defaultKafkaProducerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs(),
        new StringSerializer(),
        new StringSerializer());
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(
      DefaultKafkaProducerFactory<String, String> kafkaProducerFactory) {
    return new KafkaTemplate<>(kafkaProducerFactory, true);
  }

  @Bean
  public OrderTotalsProducer orderTotalsProducer() {
    return new OrderTotalsProducer(producerConfigs());
  }
}
