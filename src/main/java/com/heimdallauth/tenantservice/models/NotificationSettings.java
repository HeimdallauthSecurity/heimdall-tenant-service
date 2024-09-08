package com.heimdallauth.tenantservice.models;

public record NotificationSettings(
        boolean emailEnabled,
        boolean smsEnabled
) {
}
