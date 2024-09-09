package com.heimdallauth.tenantservice.services;

import com.heimdallauth.tenantservice.dm.AccountsDataManager;
import com.heimdallauth.tenantservice.documents.AccountDocument;
import com.heimdallauth.tenantservice.dto.AccountCreationRequestDTO;
import com.heimdallauth.tenantservice.dto.AccountInformationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class AccountService {
    private final AccountsDataManager accountsDataManager;


    @Autowired
    public AccountService(AccountsDataManager accountsDataManager) {
        this.accountsDataManager = accountsDataManager;
    }
    public String createNewAccount(AccountCreationRequestDTO accountCreatePayload){
        AccountDocument accountDocument = this.accountsDataManager.createAccount(
                accountCreatePayload.requesterEmailAddress(),
                accountCreatePayload.organizationName(),
                accountCreatePayload.requesterFullName()
        );
        return accountDocument.getAccountId();
    }

    public AccountInformationDTO getAccountInformation(String accountId){
        AccountDocument accountDocument = this.accountsDataManager.findAccountByAccountId(accountId);
        return new AccountInformationDTO(
                accountDocument.getAccountId(),
                accountDocument.getAccountAdminEmailAddress(),
                accountDocument.getOrganizationName(),
                accountDocument.getCreationTimestamp(),
                accountDocument.getUpdateTimestamp()
        );
    }
    public Boolean validateAccountId(String accountId){
        return this.accountsDataManager.findAccountByAccountId(accountId) != null;
    }

    public AccountInformationDTO validateAccountEmailAddress(String accountId){
        AccountDocument accountDocument = this.accountsDataManager.findAccountByAccountId(accountId);
        accountDocument.setVerificationTimestamp(Instant.now());
        this.accountsDataManager.updateAccount(accountId, accountDocument);
        return new AccountInformationDTO(
                accountDocument.getAccountId(),
                accountDocument.getAccountAdminEmailAddress(),
                accountDocument.getOrganizationName(),
                accountDocument.getCreationTimestamp(),
                accountDocument.getUpdateTimestamp()
        );
    }
}
