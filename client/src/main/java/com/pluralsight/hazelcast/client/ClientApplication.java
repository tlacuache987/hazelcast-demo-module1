package com.pluralsight.hazelcast.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
@Configuration
public class ClientApplication {

	public static void main(String... args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean(destroyMethod = "shutdown")
	public HazelcastInstance clientInstance() throws Exception {
		return HazelcastClient.newHazelcastClient();
	}

}
