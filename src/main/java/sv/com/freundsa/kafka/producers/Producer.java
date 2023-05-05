/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.freundsa.kafka.producers;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ceavalos
 */
public class Producer {
    
    private static final Logger log = LoggerFactory.getLogger(Producer.class); 

    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Properties props = new Properties();

        long startime = System.currentTimeMillis();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "1");
        props.put("linger.ms", 3);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        try (org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<>(props);) {
            producer.send(new ProducerRecord<>("devs4j-topic", "key", "message"));

            for (int i = 0; i < 1000; i++) {
                //producer.send(new ProducerRecord<String, String>("devs4j-topic", String.valueOf(i), "message : " + i)).get();
                producer.send(new ProducerRecord<String, String>("devs4j-topic", String.valueOf(i%2), "message : " + i));
            }
            producer.flush();
            //long duration = System.currentTimeMillis() - startime;
            log.info(" duracion fue de {}", System.currentTimeMillis() - startime);
        }
    }
}
