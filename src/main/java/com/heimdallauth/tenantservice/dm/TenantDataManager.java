package com.heimdallauth.tenantservice.dm;

import com.heimdallauth.tenantservice.dto.TenantInformationDTO;
import com.heimdallauth.tenantservice.models.TenantContactInformation;
import com.heimdallauth.tenantservice.utils.ResourceIdentifier;

import java.util.Optional;

public interface TenantDataManager {
    TenantInformationDTO onboardNewTenant(ResourceIdentifier tenantId, String tenantName, TenantContactInformation contactInformation);
    Optional<TenantInformationDTO> fetchTenantInformation(ResourceIdentifier tenantId);
    Optional<TenantInformationDTO> updateTenantInformation(ResourceIdentifier tenantId, TenantInformationDTO updatedTenantInformation);
    void deleteTenantIfPresent(ResourceIdentifier tenantId);
}
