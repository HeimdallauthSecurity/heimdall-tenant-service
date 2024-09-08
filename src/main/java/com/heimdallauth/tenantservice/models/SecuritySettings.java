package com.heimdallauth.tenantservice.models;

import java.util.List;

public record SecuritySettings(
        AuditLogSettings auditLogSettings,
        List<String> ipWhitelisting
) {
}
