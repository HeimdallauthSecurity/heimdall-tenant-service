package com.heimdallauth.tenantservice.documents;

import com.heimdallauth.tenantservice.models.NotificationSettings;
import com.heimdallauth.tenantservice.utils.ResourceIdentifier;
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
    private ResourceIdentifier id;
    @Indexed(unique = true)
    private ResourceIdentifier tenantId;
    private NotificationSettings notificationSettings;
}
