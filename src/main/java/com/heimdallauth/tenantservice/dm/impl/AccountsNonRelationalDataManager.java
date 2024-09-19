package com.heimdallauth.tenantservice.dm.impl;

import com.heimdallauth.tenantservice.dm.AccountsDataManager;
import com.heimdallauth.tenantservice.documents.AccountDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class AccountsNonRelationalDataManager implements AccountsDataManager {
    private static final String ACCOUNTS_COLLECTION = "accounts-collection";
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AccountsNonRelationalDataManager(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public AccountDocument createAccount(String accountEmailAddress, String organizationName, String firstName, String lastName) {
        AccountDocument accountDocument = AccountDocument.builder()
                .accountAdminEmailAddress(accountEmailAddress)
                .organizationName(organizationName)
                .requesterFirstName(firstName)
                .requesterLastName(lastName)
                .creationTimestamp(Instant.now())
                .updateTimestamp(Instant.now())
                .build();
        return this.mongoTemplate.save(accountDocument, ACCOUNTS_COLLECTION);
    }

    @Override
    public Optional<AccountDocument> findAccountByAccountId(String accountId) {
        Query selectionQuery = Query.query(Criteria.where("accountId").is(accountId));
        return executeDatabaseQuery(selectionQuery);
    }

    @Override
    public AccountDocument updateAccount(String accountId, AccountDocument updatedAccountDocument) {
        updatedAccountDocument.setUpdateTimestamp(Instant.now());
        return this.mongoTemplate.save(updatedAccountDocument);
    }

    @Override
    public Optional<AccountDocument> findAccountByAccountAdminEmailAddress(String accountAdminEmailAddress) {
        Query selectionQuery = Query.query(Criteria.where("accountAdminEmailAddress").is(accountAdminEmailAddress));
        return executeDatabaseQuery(selectionQuery);
    }

    @Override
    public void deleteAccountByAccountId(String accountId) {
        Query selectionQuery = Query.query(Criteria.where("accountId").is(accountId));
        executeSelectAndDelete(selectionQuery);
    }

    private Optional<AccountDocument> executeDatabaseQuery(Query selectionQuery) {
        return this.mongoTemplate.find(selectionQuery, AccountDocument.class, ACCOUNTS_COLLECTION).stream().findFirst();
    }

    private void executeSelectAndDelete(Query selectionQuery) {
        this.mongoTemplate.findAndRemove(selectionQuery, AccountDocument.class, ACCOUNTS_COLLECTION);
    }
}
