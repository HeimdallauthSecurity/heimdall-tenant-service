package com.heimdallauth.tenantservice.dm;

import com.heimdallauth.tenantservice.documents.AccountDocument;
import com.heimdallauth.tenantservice.models.TenantContactInformation;

public interface AccountsDataManager {
    AccountDocument createAccount(TenantContactInformation contactInformation);
    AccountDocument findAccountByAccountId(String accountId);
    AccountDocument updateAccount(String accountId, TenantContactInformation updatedContactInformation);
    void deleteAccountByAccountId(String accountId);
}
