package com.heimdallauth.tenantservice.documents;

import com.heimdallauth.tenantservice.constants.UserCreationMode;
import com.heimdallauth.tenantservice.utils.ResourceIdentifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserManagementSettingsDocument {
    @Id
    private ResourceIdentifier id;
    @Indexed(unique = true)
    private ResourceIdentifier tenantId;
    private List<UserCreationMode> userCreationModes;
    private List<String> defaultRoles;
    private int userLimit;
}
