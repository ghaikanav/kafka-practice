package io.conduktor.demos.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithKeys {
    private static final Logger log = LoggerFactory.getLogger(ProducerDemoWithKeys.class);

    public static void main(String[] args) {
        log.info("I am a producer!");
        // create Producer Properties
        Properties properties = new Properties();

        // connect to localhost
        properties.setProperty("bootstrap.servers", "localhost:29092");

        // set Producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());


        // create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);


        for (int i = 0; i < 30; i++) {
            String topic = "demo_java";
            String key = "id_" + i;
            String value = "hello world " + i;

            // create a Producer record
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);


            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        log.info("Key: " + key + " | Partition: " + recordMetadata.partition());
                    } else {
                        log.error("Error while producing", e);
                    }
                }
            });
        }


        // send data


        // tell the producer to send all data and block until done -- synchronous
        producer.flush();

        // flush and close the producer
        producer.close();


    }
}