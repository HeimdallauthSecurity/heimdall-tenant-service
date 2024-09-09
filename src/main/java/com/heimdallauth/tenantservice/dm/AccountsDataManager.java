package com.heimdallauth.tenantservice.dm;

import com.heimdallauth.tenantservice.documents.AccountDocument;
import com.heimdallauth.tenantservice.models.TenantContactInformation;

public interface AccountsDataManager {
    AccountDocument createAccount(String accountEmailAddress, String organizationName, String requesterFullName);
    AccountDocument findAccountByAccountId(String accountId);
    AccountDocument updateAccount(String accountId, AccountDocument updatedContactInformation);
    void deleteAccountByAccountId(String accountId);
}
