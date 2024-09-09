package com.heimdallauth.tenantservice.exceptions;

public class TenantNotFoundException extends RuntimeException {
    public final String tenantResourceName;
    public TenantNotFoundException(String tenantResourceName, String message) {
        super(message);
        this.tenantResourceName = tenantResourceName;
    }
}
