package com.sb.hazelcast;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sb.hazelcast.model.UserAccount;

@SpringBootApplication
public class SbHazelcastExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbHazelcastExampleApplication.class, args);
	}

	@Bean
	public Map<String, UserAccount> accountMap() {
		return new HashMap<>();
	}
	
}
