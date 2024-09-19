package com.heimdallauth.tenantservice.services;

import com.heimdallauth.tenantservice.clients.BifrostClient;
import com.heimdallauth.tenantservice.clients.KratosClient;
import com.heimdallauth.tenantservice.constants.bifrost.MailType;
import com.heimdallauth.tenantservice.dm.AccountsDataManager;
import com.heimdallauth.tenantservice.documents.AccountDocument;
import com.heimdallauth.tenantservice.dto.AccountCreationRequestDTO;
import com.heimdallauth.tenantservice.dto.AccountInformationDTO;
import com.heimdallauth.tenantservice.dto.bifrost.BifrostSendEmailRequestDTO;
import com.heimdallauth.tenantservice.dto.kratos.KratosAccountInformation;
import com.heimdallauth.tenantservice.dto.kratos.KratosUserAccountCreationDTO;
import com.heimdallauth.tenantservice.exceptions.AccountNotFoundException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class AccountService {
    private static final Boolean ACCOUNT_ACTIVE = false;
    private final AccountsDataManager accountsDataManager;
    private final KratosClient kratosClient;
    private final BifrostClient bifrostClient;


    @Autowired
    public AccountService(AccountsDataManager accountsDataManager, KratosClient kratosClient, BifrostClient bifrostClient) {
        this.accountsDataManager = accountsDataManager;
        this.kratosClient = kratosClient;
        this.bifrostClient = bifrostClient;
    }

    public String createNewAccount(AccountCreationRequestDTO accountCreatePayload) {
        AccountDocument accountDocument = this.accountsDataManager.createAccount(
                accountCreatePayload.requesterEmailAddress(),
                accountCreatePayload.organizationName(),
                accountCreatePayload.firstName(),
                accountCreatePayload.lastName()
        );
        this.provisionTenantAdministratorAccountAndInitiateEmailVerificationProcess(accountDocument, accountCreatePayload);
        return accountDocument.getAccountId();
    }

    public AccountInformationDTO getAccountInformation(String accountId) {
        AccountDocument accountDocument = this.accountsDataManager.findAccountByAccountId(accountId).orElseThrow(() -> new AccountNotFoundException("Account could not be located", accountId));
        return new AccountInformationDTO(
                accountDocument.getAccountId(),
                accountDocument.getAccountAdminEmailAddress(),
                accountDocument.getOrganizationName(),
                accountDocument.getCreationTimestamp(),
                accountDocument.getUpdateTimestamp()
        );
    }

    public Boolean validateAccountId(String accountId) {
        return this.accountsDataManager.findAccountByAccountId(accountId).isPresent();
    }

    public AccountInformationDTO validateAccountEmailAddress(String accountId) {
        AccountDocument accountDocument = this.accountsDataManager.findAccountByAccountId(accountId).orElseThrow(() -> new AccountNotFoundException("Account could not be located", accountId));
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
    private void provisionTenantAdministratorAccountAndInitiateEmailVerificationProcess(AccountDocument createdAccount, AccountCreationRequestDTO accountCreationRequestDTO){
        try{
            KratosUserAccountCreationDTO kratosUserAccountCreationDTO = new KratosUserAccountCreationDTO(
                    createdAccount.getAccountAdminEmailAddress(),
                    createdAccount.getAccountAdminEmailAddress(),
                    accountCreationRequestDTO.firstName(),
                    accountCreationRequestDTO.lastName(),
                    ACCOUNT_ACTIVE
            );
            KratosAccountInformation kratosAccountInformation = this.kratosClient.createAdministratorUserAccount(kratosUserAccountCreationDTO);
            this.bifrostClient.sendEmail(new BifrostSendEmailRequestDTO(kratosAccountInformation.email(), MailType.PROFILE_VERIFICATION));
        }catch(FeignException e){
            log.error("Error occurred while creating account in Kratos or sending email verification", e);
            this.accountsDataManager.deleteAccountByAccountId(createdAccount.getAccountId());
            throw new AccountNotFoundException("Account could not be created", createdAccount.getAccountId());
        }
    }
}
