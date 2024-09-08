package com.heimdallauth.tenantservice.models;

import com.heimdallauth.tenantservice.constants.AuthenticationMethods;

import java.util.List;

public record TenantAuthenticationSettings(
        List<AuthenticationMethods> authMethods,
        Boolean mfaEnabled,
        PasswordPolicy passwordPolicy
) {
}
