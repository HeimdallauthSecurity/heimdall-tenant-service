package com.heimdallauth.tenantservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MongoDBContainer;

@Configuration
public class TestContainerConfig {


    @Bean
    public MongoDBContainer mongoDBContainer() {
        MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7");
        mongoDBContainer.start();
        return mongoDBContainer;
    }
}
