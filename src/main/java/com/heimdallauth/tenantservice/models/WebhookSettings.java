package com.heimdallauth.tenantservice.models;

public record WebhookSettings(
        String userWebhook,
        String passwordResetWebhook
) {
}
