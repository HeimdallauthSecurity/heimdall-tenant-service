package com.heimdallauth.tenantservice.dto;

import com.heimdallauth.tenantservice.models.TenantContactInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountCreationRequestDTO {
    private TenantContactInformation accountContactInformation;

}
