package com.heimdallauth.tenantservice.models;

public record AuditLogSettings(
        boolean enabled,
        int retentionDays
) {
}
