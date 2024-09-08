package com.heimdallauth.tenantservice.models;

public record PasswordPolicy(
        int minLength,
        int maxLength,
        boolean requireUppercase,
        boolean requireLowercase,
        boolean requireNumber,
        boolean requireSpecialCharacter,
        int passwordReuseLimit,
        int expirePasswordAfterDays
) {
}
