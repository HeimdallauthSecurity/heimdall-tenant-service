package com.heimdallauth.tenantservice.dto.kratos;

import java.util.List;

public record KratosAccountInformation(
        String id,
        String username,
        String email,
        String firstName,
        String lastName,
        List<String> roles
) {
}
