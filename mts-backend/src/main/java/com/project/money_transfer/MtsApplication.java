package com.project.money_transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class MtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtsApplication.class, args);
	}

}
