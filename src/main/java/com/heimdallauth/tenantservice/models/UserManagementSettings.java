package com.heimdallauth.tenantservice.models;

import com.heimdallauth.tenantservice.constants.UserCreationMode;

import java.util.List;

public record UserManagementSettings(
        List<UserCreationMode> userCreationModes,
        List<String> defaultRoles,
        int userLimit
) {
}
