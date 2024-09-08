package com.heimdallauth.tenantservice.models;

import com.heimdallauth.tenantservice.constants.UserCreationMode;
import com.heimdallauth.tenantservice.documents.UserManagementSettingsDocument;
import org.apache.catalina.User;

import java.util.List;

public record UserManagementSettings(
        List<UserCreationMode> userCreationModes,
        List<String> defaultRoles,
        int userLimit
) {
    public static UserManagementSettings fromEntity(UserManagementSettingsDocument managementSettingsDocument){
        return new UserManagementSettings(
                managementSettingsDocument.getUserCreationModes(),
                managementSettingsDocument.getDefaultRoles(),
                managementSettingsDocument.getUserLimit()
        );
    }
}
