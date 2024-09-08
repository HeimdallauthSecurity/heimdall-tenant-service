package com.heimdallauth.tenantservice.services;

import com.heimdallauth.tenantservice.dm.TenantDataManager;
import com.heimdallauth.tenantservice.dto.TenantCreateRequestDTO;
import com.heimdallauth.tenantservice.dto.TenantInformationDTO;
import com.heimdallauth.tenantservice.utils.ResourceIdentifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class TenantService {
    @Value("${heimdall.deployment.region}")
    private String deploymentRegion;
    private final TenantDataManager tenantDataManager;

    @Autowired
    public TenantService(TenantDataManager tenantDataManager) {
        this.tenantDataManager = tenantDataManager;
    }
    public TenantInformationDTO onboardTenantInformation(TenantCreateRequestDTO createRequestDTO){
        log.info("Onboarding tenant information for tenant: {}", createRequestDTO.getTenantName());
        ResourceIdentifier identifier = ResourceIdentifier.buildResourceIdentifier(createRequestDTO.getAccountId(), deploymentRegion, UUID.randomUUID());
        return tenantDataManager.onboardNewTenant(identifier, createRequestDTO.getAccountId(), createRequestDTO.getTenantName(), createRequestDTO.getContactInformation());
    }
}
