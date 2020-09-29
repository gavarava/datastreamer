package io.datastreamer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import io.datastreamer.adapters.kafka.OrderAnalyticsProducer;
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
  public OrderAnalyticsProducer orderTotalsProducer() {
    return new OrderAnalyticsProducer(producerConfigs());
  }

  @Bean
  public static ObjectMapper objectMapper() {
    return new ObjectMapper()
        .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
        .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true))
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        .setSerializationInclusion(Include.NON_NULL);
  }
}
