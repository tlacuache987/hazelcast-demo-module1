package com.pluralsight.hazelcast.storage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
@Configuration
public class StorageNodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageNodeApplication.class, args);
	}

	@Bean(destroyMethod = "shutdown")
	public HazelcastInstance createStorageNode(@Qualifier("storageNodeConfig") Config config) throws Exception {
		return Hazelcast.newHazelcastInstance(config);
	}

	@Bean(name = "storageNodeConfig")
	public Config config(CustomersMapStore customersMapStore) throws Exception {
		Config config = new Config();

		// Create a new map configuration for the customers map
		MapConfig customerMapConfig = new MapConfig();

		// Create a map store config for the customer information
		MapStoreConfig customerMapStoreConfig = new MapStoreConfig();
		customerMapStoreConfig.setImplementation(customersMapStore);
		customerMapStoreConfig.setWriteDelaySeconds(3);

		// Update the customers map configuration to use the
		// customers map store config we just created
		customerMapConfig.setMapStoreConfig(customerMapStoreConfig);
		customerMapConfig.setName("customers");

		MapIndexConfig dobFieldIndex = new MapIndexConfig("dob", true);
		customerMapConfig.addMapIndexConfig(dobFieldIndex);

		// Add the customers map config to our storage node config
		config.addMapConfig(customerMapConfig);

		return config;
	}

}
