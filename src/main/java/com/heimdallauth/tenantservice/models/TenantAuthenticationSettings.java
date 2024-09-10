package com.heimdallauth.tenantservice.models;

import com.heimdallauth.tenantservice.constants.AuthenticationMethods;
import com.heimdallauth.tenantservice.documents.TenantAuthenticationSettingsDocument;

import java.util.List;

public record TenantAuthenticationSettings(
        List<AuthenticationMethods> authMethods,
        Boolean mfaEnabled,
        PasswordPolicy passwordPolicy
) {
    public static TenantAuthenticationSettings fromEntity(TenantAuthenticationSettingsDocument authenticationSettingsDocument) {
        return new TenantAuthenticationSettings(
                authenticationSettingsDocument.getAuthenticationMethods(),
                authenticationSettingsDocument.getMfaEnabled(),
                new PasswordPolicy(
                        authenticationSettingsDocument.getPasswordPolicy().minLength(),
                        authenticationSettingsDocument.getPasswordPolicy().maxLength(),
                        authenticationSettingsDocument.getPasswordPolicy().requireUppercase(),
                        authenticationSettingsDocument.getPasswordPolicy().requireLowercase(),
                        authenticationSettingsDocument.getPasswordPolicy().requireNumber(),
                        authenticationSettingsDocument.getPasswordPolicy().requireSpecialCharacter(),
                        authenticationSettingsDocument.getPasswordPolicy().passwordReuseLimit(),
                        authenticationSettingsDocument.getPasswordPolicy().expirePasswordAfterDays()
                )
        );
    }
}
