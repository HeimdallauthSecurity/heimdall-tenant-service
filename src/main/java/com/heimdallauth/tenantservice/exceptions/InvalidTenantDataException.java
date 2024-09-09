package com.heimdallauth.tenantservice.exceptions;

public class InvalidTenantDataException extends RuntimeException {
    public InvalidTenantDataException(String message) {
        super(message);
    }
}
