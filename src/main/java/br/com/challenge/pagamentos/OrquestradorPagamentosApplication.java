package br.com.challenge.pagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class OrquestradorPagamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrquestradorPagamentosApplication.class, args);
	}

}
