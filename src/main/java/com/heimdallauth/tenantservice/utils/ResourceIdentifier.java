package com.heimdallauth.tenantservice.utils;

import com.heimdallauth.tenantservice.constants.ResourceType;
import lombok.Getter;

import java.net.ResponseCache;
import java.util.UUID;

@Getter
public class ResourceIdentifier {
    private static final String IDENTIFIER_PREFIX = "hrn";
    private static final String PARTITION = "heimdallauth";
    private static final String SERVICE = "tenant-service";
    private static final String DELIMITER = "::";
    private static final String RESOURCE_DELIMITER = "/";

    private final String service;
    private final String region;
    private final String accountId;
    private final String resourceType;
    private final String resourceId;

    public ResourceIdentifier(String service, String region, String accountId, String resourceType, String resourceId) {
        this.service = service;
        this.region = region;
        this.accountId = accountId;
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    public static ResourceIdentifier buildTenantIdResourceIdentifier(String accountId, String region) {
        return new ResourceIdentifier("tenant-service", region, accountId, ResourceType.TENANT.toString(), UUID.randomUUID().toString());
    }
    public static ResourceIdentifier buildChildIdentifiersFromTenantIdentifier(String tenantResourceIdentifier, ResourceType childResourceRequested) {
        ResourceIdentifier parentResourceIdentifier = ResourceIdentifier.fromString(tenantResourceIdentifier);
        return new ResourceIdentifier(parentResourceIdentifier.service, parentResourceIdentifier.region, parentResourceIdentifier.resourceId, childResourceRequested.toString(), UUID.randomUUID().toString());
    }

    public static ResourceIdentifier fromString(String identifier) {
        if (!identifier.startsWith(IDENTIFIER_PREFIX + DELIMITER)) {
            throw new IllegalArgumentException("Invalid identifier format");
        }

        String[] parts = identifier.split(DELIMITER);
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid identifier format");
        }

        String[] resourceParts = parts[4].split(RESOURCE_DELIMITER);
        if (resourceParts.length != 3) {
            throw new IllegalArgumentException("Invalid resource format");
        }

        return new ResourceIdentifier(
                parts[2], // service
                parts[3], // region
                resourceParts[0], // accountId
                resourceParts[1], // resourceType
                resourceParts[2]  // resourceId
        );
    }

    @Override
    public String toString() {
        return IDENTIFIER_PREFIX + DELIMITER +
                PARTITION + DELIMITER +
                SERVICE + DELIMITER +
                region + DELIMITER +
                accountId + RESOURCE_DELIMITER +
                resourceType + RESOURCE_DELIMITER +
                resourceId;
    }
    // Getters and setters for each field can be added here
}