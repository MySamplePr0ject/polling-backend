package com.example.pollResaultsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PollResaultsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollResaultsApiApplication.class, args);
	}

}
