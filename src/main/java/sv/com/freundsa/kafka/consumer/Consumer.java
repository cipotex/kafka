/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.freundsa.kafka.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ceavalos
 */
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
            
    public static void main(String[] args) {
        
        //static Logger log = Logger.getLogger(main.class);
        Properties props = new Properties();
        //
        props.setProperty("bootstrap.servers", "localhost:9092"); //broker kadka
        props.setProperty("group.id", "devs4j-group"); //
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
       
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);) {
            consumer.subscribe(Arrays.asList("devs4j-topic"));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record: records) {
                    log.info (" offset", record.value(),record.offset(), record.partition(), record.key());
                    //
                    System.out.println("offset = {}, key = {}, value ={}" +","+
                            record.offset()+","+ record.key()+","+ record.value());
                }
            }
        }

    }

}
