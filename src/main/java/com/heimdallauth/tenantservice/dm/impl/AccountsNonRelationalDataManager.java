package com.heimdallauth.tenantservice.dm.impl;

import com.heimdallauth.tenantservice.dm.AccountsDataManager;
import com.heimdallauth.tenantservice.documents.AccountDocument;
import com.heimdallauth.tenantservice.models.TenantContactInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class AccountsNonRelationalDataManager implements AccountsDataManager {
    private final MongoTemplate mongoTemplate;
    private static final String ACCOUNTS_COLLECTION = "accounts-collection";

    @Autowired
    public AccountsNonRelationalDataManager(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public AccountDocument createAccount(String accountEmailAddress, String organizationName, String requesterFullName) {
        return null;
    }

    @Override
    public AccountDocument findAccountByAccountId(String accountId) {
        Query selectionQuery = Query.query(Criteria.where("accountId").is(accountId));
        return this.mongoTemplate.find(selectionQuery, AccountDocument.class, ACCOUNTS_COLLECTION).stream().findFirst().orElseThrow();
    }

    @Override
    public AccountDocument updateAccount(String accountId, AccountDocument updatedAccountDocument) {
        updatedAccountDocument.setUpdateTimestamp(Instant.now());
        return this.mongoTemplate.save(updatedAccountDocument);
    }

    @Override
    public void deleteAccountByAccountId(String accountId) {

    }
}
