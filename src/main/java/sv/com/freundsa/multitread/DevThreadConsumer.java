/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.freundsa.multitread;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ceavalos
 */
public class DevThreadConsumer extends Thread {

       
   private  KafkaConsumer<String,String>consumer;

       
    public static final Logger log  = LoggerFactory.getLogger(DevThreadConsumer.class);

    private final AtomicBoolean closed = new AtomicBoolean(false);

    

    public DevThreadConsumer(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        consumer.subscribe(Arrays.asList("devs4j-topic"));        
        try {
            while (!closed.get()) {
                ConsumerRecords<String, String> records= consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record  : records) {
                      log.info (" offset value = {}  ,  ofsset={}  , partition={}  key={}", record.value(),record.offset(), record.partition(), record.key());

                }
            }
        } catch (WakeupException e) {
            if (!closed.get()) {
                throw e;
            }
        } finally {
            consumer.close();
        }
    }

    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }
}
