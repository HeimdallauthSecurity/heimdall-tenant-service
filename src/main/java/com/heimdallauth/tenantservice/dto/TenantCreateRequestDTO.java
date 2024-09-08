package com.heimdallauth.tenantservice.dto;

import com.heimdallauth.tenantservice.constants.TenantStatus;
import com.heimdallauth.tenantservice.models.TenantContactInformation;
import com.heimdallauth.tenantservice.models.UserManagementSettings;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TenantCreateRequestDTO {
    private String accountId;
    private String tenantName;
    private TenantContactInformation contactInformation;
    private TenantStatus status;
    private UserManagementSettings userManagementSettings;
}
