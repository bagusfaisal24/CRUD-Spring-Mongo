package com.example.springmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SpringmongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmongoApplication.class, args);
	}

}
