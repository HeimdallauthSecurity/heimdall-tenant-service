package com.heimdallauth.tenantservice.utils;

public class ResourceIdentifier {
    private static final String IDENTIFIER_PREFIX = "hrn";
    private static final String DELIMITER = "::";
    private static final String RESOURCE_DELIMITER = "/";

    private final String partition;
    private final String service;
    private final String region;
    private final String accountId;
    private final String resourceType;
    private final String resourceId;

    public ResourceIdentifier(String partition, String service, String region, String accountId, String resourceType, String resourceId) {
        this.partition = partition;
        this.service = service;
        this.region = region;
        this.accountId = accountId;
        this.resourceType = resourceType;
        this.resourceId = resourceId;
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
        if (resourceParts.length != 2) {
            throw new IllegalArgumentException("Invalid resource format");
        }

        return new ResourceIdentifier(parts[0], parts[1], parts[2],parts[3], resourceParts[0], resourceParts[1]);
    }

    @Override
    public String toString() {
        return IDENTIFIER_PREFIX + DELIMITER +
                partition + DELIMITER +
                service + DELIMITER +
                region + DELIMITER +
                accountId + RESOURCE_DELIMITER +
                resourceType + RESOURCE_DELIMITER +
                resourceId;
    }

    // Getters and setters for each field can be added here
}