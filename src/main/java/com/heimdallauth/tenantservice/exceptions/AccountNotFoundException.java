package com.heimdallauth.tenantservice.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public final String accountId;

    public AccountNotFoundException(String message, String accountId) {
        super(message);
        this.accountId = accountId;
    }
}
