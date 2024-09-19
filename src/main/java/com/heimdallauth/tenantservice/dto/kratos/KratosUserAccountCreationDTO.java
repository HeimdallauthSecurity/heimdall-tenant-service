package com.heimdallauth.tenantservice.dto.kratos;

public record KratosUserAccountCreationDTO(
        String username,
        String email,
        String firstName,
        String lastName,
        boolean isActive
) {
}
