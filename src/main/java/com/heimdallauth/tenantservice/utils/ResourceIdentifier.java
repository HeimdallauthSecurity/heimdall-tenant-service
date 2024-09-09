package com.heimdallauth.tenantservice.utils;

import com.heimdallauth.tenantservice.constants.ResourceType;
import lombok.Getter;

import java.util.UUID;

public record ResourceIdentifier(String service, String region, String accountId, String resourceType,
                                 @Getter String resourceId) {
    private static final String IDENTIFIER_PREFIX = "hrn";
    private static final String PARTITION = "heimdallauth";
    private static final String SERVICE = "tenant-service";
    private static final String DELIMITER = "::";
    private static final String RESOURCE_DELIMITER = "/";

    public static ResourceIdentifier buildTenantIdResourceIdentifier(String accountId, String region, String tenantName) {
        return new ResourceIdentifier("tenant-service", region, accountId, ResourceType.TENANT.toString(), tenantName);
    }

    public static ResourceIdentifier buildChildIdentifiersFromTenantIdentifier(String tenantResourceIdentifier, ResourceType childResourceRequested) {
        ResourceIdentifier parentResourceIdentifier = ResourceIdentifier.fromString(tenantResourceIdentifier);
        return new ResourceIdentifier(parentResourceIdentifier.service, parentResourceIdentifier.region, parentResourceIdentifier.accountId, childResourceRequested.toString(), parentResourceIdentifier.resourceId);
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