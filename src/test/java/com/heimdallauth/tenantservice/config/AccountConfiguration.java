package com.heimdallauth.tenantservice.config;

import com.heimdallauth.tenantservice.dm.AccountsDataManager;
import com.heimdallauth.tenantservice.dm.impl.AccountsNonRelationalDataManager;
import com.heimdallauth.tenantservice.services.AccountService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.testcontainers.containers.MongoDBContainer;

@Configuration
public class AccountConfiguration {
    private final MongoDBContainer mongoDBContainer;

    public AccountConfiguration(MongoDBContainer mongoDBContainer) {
        this.mongoDBContainer = mongoDBContainer;
    }

   @Bean
    public MongoTemplate mongoTemplate() {
       String mongoUri = mongoDBContainer.getReplicaSetUrl();
       return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoUri));
   }
   @Bean
    public AccountsNonRelationalDataManager accountsNonRelationalDataManager(MongoTemplate mongoTemplate) {
        return new AccountsNonRelationalDataManager(mongoTemplate);
    }
    @Bean
    public AccountsDataManager accountsDataManager(AccountsNonRelationalDataManager accountsNonRelationalDataManager) {
        return accountsNonRelationalDataManager;
    }
    @Bean
    public AccountService accountService(AccountsDataManager accountsDataManager) {
        return new AccountService(accountsDataManager);
    }
}
