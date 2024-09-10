package com.heimdallauth.tenantservice.models;

import com.heimdallauth.tenantservice.documents.TenantNotificationSettingDocument;

public record NotificationSettings(
        boolean emailEnabled,
        boolean smsEnabled
) {
    public static NotificationSettings fromEntity(TenantNotificationSettingDocument notificationSettingsDocument) {
        return new NotificationSettings(
                notificationSettingsDocument.getNotificationSettings().emailEnabled,
                notificationSettingsDocument.getNotificationSettings().smsEnabled()
        );
    }
}
