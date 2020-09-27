package com.example.demo.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.example.demo.domain.HistoricoPedido;
import com.example.demo.services.HistoricoPedidoService;


public class Consumer {

	public Properties consumerKafka() {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "grupo1");
		return properties;
	}
	
	
	public HistoricoPedido consumerRecords() {
		KafkaConsumer<String, HistoricoPedido> consumer = new KafkaConsumer<String, HistoricoPedido>(consumerKafka());
		HistoricoPedido historicoPedido = null;
		consumer.subscribe(Arrays.asList("historicoPedido"));
		ConsumerRecords<String, HistoricoPedido> records = consumer.poll(Duration.ofSeconds(1));
		for (ConsumerRecord<String, HistoricoPedido> consumerRecord : records) {
			historicoPedido = consumerRecord.value();
			HistoricoPedidoService service = new HistoricoPedidoService();
			service.insert(historicoPedido);
		}
		return historicoPedido;
	}
	
}
