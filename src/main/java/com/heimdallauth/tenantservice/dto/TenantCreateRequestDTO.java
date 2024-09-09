package com.heimdallauth.tenantservice.dto;

import com.heimdallauth.tenantservice.constants.UserCreationMode;
import com.heimdallauth.tenantservice.models.TenantContactInformation;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TenantCreateRequestDTO {
    private String accountId;
    private String tenantName;
    private String tenantDescription;
    private TenantContactInformation contactInformation;
    private List<UserCreationMode> userRegistrationModes;
}
