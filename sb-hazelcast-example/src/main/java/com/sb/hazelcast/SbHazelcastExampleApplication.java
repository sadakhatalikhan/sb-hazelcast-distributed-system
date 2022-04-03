package com.sb.hazelcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.sb.hazelcast.cache.HazelcastMapManager;
import com.sb.hazelcast.cache.MapEntryListener;

@SpringBootApplication
@EnableCaching
public class SbHazelcastExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbHazelcastExampleApplication.class, args);
	}

	/*
	@Bean
	public Map<String, UserAccount> accountMap() {
		return new HashMap<>();
	}
	*/

	@Bean
	public MapEntryListener mapEntryListener(){
		return new MapEntryListener();
	}
	

}
