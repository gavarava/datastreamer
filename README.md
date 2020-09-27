# Data Streamer
Stream data to a Kafka Queue from a Postgres database which contains over 10M rows.

#### Run Kafka 
You can find the documentation and instructions to Run Kafka at [https://docs.confluent.io/current/tutorials/build-your-own-demos.html](https://docs.confluent.io/current/tutorials/build-your-own-demos.html?utm_source=github&utm_medium=demo&utm_campaign=ch.examples_type.community_content.cp-all-in-one)

#### Run Database Locally
```
docker-compose up -d
```

#### Create a Kafka Topic called order-totals
```
kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order-totals
```

#### List Kafka Topics
```
kafka-topics --list --zookeeper localhost:2181
```

#### Tail Contents of Kafka Topic
```
kafka-console-consumer --bootstrap-server localhost:9092 --topic order-totals --from-beginning
```