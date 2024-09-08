package com.heimdallauth.tenantservice.models;

public record TenantContactInformation(
        String emailAddress,
        String phoneNumber,
        String supportContact
) {
}
