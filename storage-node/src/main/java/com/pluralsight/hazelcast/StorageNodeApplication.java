package com.pluralsight.hazelcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
@Configuration
public class StorageNodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageNodeApplication.class, args);
	}

	@Bean(destroyMethod = "shutdown")
	public HazelcastInstance createStorageNode() throws Exception {
		return Hazelcast.newHazelcastInstance();
	}

}
