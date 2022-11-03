package com.rest.order;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
public class OrderApplication {

	public static void main(String[] args) {
		log.info("Start Applicaction");
		SpringApplication.run(OrderApplication.class, args);
	}

}
