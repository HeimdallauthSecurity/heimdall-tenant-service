package com.heimdallauth.tenantservice.dto;

public record AccountCreationRequestDTO(
        String requesterEmailAddress,
        String organizationName,
        String firstName,
        String lastName
) {
}
