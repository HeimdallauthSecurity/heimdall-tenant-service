package com.heimdallauth.tenantservice.services;

import com.heimdallauth.tenantservice.dm.AccountsDataManager;
import com.heimdallauth.tenantservice.documents.AccountDocument;
import com.heimdallauth.tenantservice.dto.AccountCreationRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {
    private final AccountsDataManager accountsDataManager;


    @Autowired
    public AccountService(AccountsDataManager accountsDataManager) {
        this.accountsDataManager = accountsDataManager;
    }
    public String createNewAccount(AccountCreationRequestDTO accountCreatePayload){
        AccountDocument accountDocument = this.accountsDataManager.createAccount(accountCreatePayload.getAccountContactInformation());
        return accountDocument.getAccountId();
    }
}
