package com.heimdallauth.tenantservice.dm;

import com.heimdallauth.tenantservice.constants.UserCreationMode;
import com.heimdallauth.tenantservice.dto.TenantInformationDTO;
import com.heimdallauth.tenantservice.models.TenantContactInformation;
import com.heimdallauth.tenantservice.utils.ResourceIdentifier;

import java.util.List;
import java.util.Optional;

public interface TenantDataManager {
    TenantInformationDTO onboardNewTenant(ResourceIdentifier tenantId, String accountId, String tenantName, TenantContactInformation contactInformation, String tenantDescription, List<UserCreationMode> userCreationModes);
    Optional<TenantInformationDTO> fetchTenantInformation(ResourceIdentifier tenantId);
    Optional<TenantInformationDTO> updateTenantInformation(ResourceIdentifier tenantId, TenantInformationDTO updatedTenantInformation);
    void deleteTenantIfPresent(ResourceIdentifier tenantId);
}
