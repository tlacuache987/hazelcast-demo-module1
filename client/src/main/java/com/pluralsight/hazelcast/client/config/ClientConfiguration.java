package com.pluralsight.hazelcast.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.client.config.ClientConfig;

@Configuration
public class ClientConfiguration {

	@Bean(name = "hazelcastClientConfig")
	public ClientConfig clientConfig() {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.getNetworkConfig().setConnectionAttemptLimit(0);
		return clientConfig;
	}

}
