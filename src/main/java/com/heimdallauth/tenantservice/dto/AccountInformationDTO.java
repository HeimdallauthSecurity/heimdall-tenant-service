package com.heimdallauth.tenantservice.dto;

import java.time.Instant;

public record AccountInformationDTO(
        String accountId,
        String accountAdminEmailAddress,
        String organizationName,
        Instant creationTimestamp,
        Instant updateTimestamp
) {
}
