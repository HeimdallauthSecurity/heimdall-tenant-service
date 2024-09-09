package com.heimdallauth.tenantservice.dm;

import com.heimdallauth.tenantservice.documents.AccountDocument;

import java.util.Optional;

public interface AccountsDataManager {
    AccountDocument createAccount(String accountEmailAddress, String organizationName, String requesterFullName);

    Optional<AccountDocument> findAccountByAccountId(String accountId);

    AccountDocument updateAccount(String accountId, AccountDocument updatedContactInformation);

    Optional<AccountDocument> findAccountByAccountAdminEmailAddress(String accountAdminEmailAddress);

    void deleteAccountByAccountId(String accountId);
}
