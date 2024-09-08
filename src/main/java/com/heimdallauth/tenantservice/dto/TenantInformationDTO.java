package com.heimdallauth.tenantservice.dto;

import com.heimdallauth.tenantservice.models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TenantInformationDTO {
    private String tenantId;
    private String tenantName;
    private TenantContactInformation contactInformation;
    private Boolean status;
    private TenantAuthenticationSettings authenticationSettings;
    private SessionManagement sessionManagement;
    private UserManagementSettings userManagementSettings;
    private List<String> customDomains;
    private TenantUsageLimits usageLimits;
    private WebhookSettings webhookSettings;
    private SecuritySettings securitySettings;
    private NotificationSettings notificationSettings;
}
