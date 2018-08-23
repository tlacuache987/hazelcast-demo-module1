package com.pluralsight.hazelcast.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
@Configuration
public class ClientApplication {

	public static void main(String... args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean(name = "clientInstance")
	public ClientConfig clientConfig() {
		ClientConfig clientConfig = new ClientConfig();
		ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
		networkConfig.setConnectionAttemptLimit(0);
		return clientConfig;
	}

	@Bean(name = "clientInstance", destroyMethod = "shutdown")
	public HazelcastInstance clientInstance(ClientConfig clientConfig) throws Exception {
		return HazelcastClient.newHazelcastClient(clientConfig);
	}
}
