package com.heimdallauth.tenantservice.models;

public record TenantUsageLimits(
        int apiCalls,
        int userCount
) {
}
