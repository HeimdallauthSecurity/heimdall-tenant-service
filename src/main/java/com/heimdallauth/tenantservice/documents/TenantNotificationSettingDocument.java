package com.heimdallauth.tenantservice.documents;

import com.heimdallauth.tenantservice.models.NotificationSettings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class TenantNotificationSettingDocument {
    @Id
    private String id;
    @Indexed(unique = true)
    private String tenantId;
    private NotificationSettings notificationSettings;
}
