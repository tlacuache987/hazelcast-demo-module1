package com.pluralsight.hazelcast.shared;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableJpaRepositories(basePackages = "com.pluralsight.hazelcast.storage")
@EntityScan
public class SharedConfiguration {
}
