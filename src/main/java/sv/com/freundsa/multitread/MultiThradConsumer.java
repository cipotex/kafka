/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.freundsa.multitread;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 *
 * @author ceavalos
 */
public class MultiThradConsumer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //static Logger log = Logger.getLogger(main.class);
        Properties props = new Properties();
        //
        props.setProperty("bootstrap.servers", "localhost:9092"); //broker kadka
        props.setProperty("group.id", "devs4j-group"); //
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //
        ExecutorService executor= Executors.newFixedThreadPool(1);
        
        for (int i = 0; i < 4; i++) {
           DevThreadConsumer worker=  new DevThreadConsumer(new KafkaConsumer<>(props));
           executor.execute(worker);
        }
        executor.shutdown();
        while(!executor.isTerminated());
    }
    
}
