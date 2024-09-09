package com.heimdallauth.tenantservice.dm.impl;

import com.heimdallauth.tenantservice.constants.AuthenticationMethods;
import com.heimdallauth.tenantservice.constants.ResourceType;
import com.heimdallauth.tenantservice.constants.UserCreationMode;
import com.heimdallauth.tenantservice.dm.TenantDataManager;
import com.heimdallauth.tenantservice.documents.TenantAuthenticationSettingsDocument;
import com.heimdallauth.tenantservice.documents.TenantDocument;
import com.heimdallauth.tenantservice.documents.TenantNotificationSettingDocument;
import com.heimdallauth.tenantservice.documents.UserManagementSettingsDocument;
import com.heimdallauth.tenantservice.dto.TenantInformationDTO;
import com.heimdallauth.tenantservice.models.*;
import com.heimdallauth.tenantservice.utils.ResourceIdentifier;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class TenantNonRelationalDataManager implements TenantDataManager {
    @Value("${heimdall.deployment.region}")
    private String deploymentRegion;
    private final MongoTemplate mongoTemplate;
    private static final String TENANT_COLLECTION = "tenants-collection";
    private static final String AUTHENTICATION_SETTINGS_COLLECTION = "authentication-settings-collection";
    private static final String USER_MANAGEMENT_SETTINGS_COLLECTION = "user-management-settings-collection";
    private static final String SECURITY_SETTINGS_COLLECTION = "security-settings-collection";
    private static final String NOTIFICATION_SETTINGS_COLLECTION = "notification-settings-collection";
    private static final String CUSTOM_SETTINGS_COLLECTION = "custom-settings-collection";


    @Autowired
    public TenantNonRelationalDataManager(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TenantInformationDTO onboardNewTenant(ResourceIdentifier tenantId,String accountId, String tenantName, TenantContactInformation contactInformation, String tenantDescription, List<UserCreationMode> userCreationModes) {
        TenantDocument tenantDocument = TenantDocument.builder()
                .id(tenantId.toString())
                .tenantName(tenantName)
                .tenantDescription(tenantDescription)
                .contactInformation(contactInformation)
                .build();
        TenantAuthenticationSettingsDocument tenantAuthenticationSettingsDocument = TenantAuthenticationSettingsDocument.builder()
                .tenantId(tenantId.toString())
                .id(ResourceIdentifier.buildChildIdentifiersFromTenantIdentifier(tenantId.toString(), ResourceType.AUTHENTICATION_SETTINGS).toString())
                .authenticationMethods(List.of(AuthenticationMethods.OAUTH_2, AuthenticationMethods.OPEN_ID_CONNECT, AuthenticationMethods.PASSKEY))
                .mfaEnabled(false)
                .passwordPolicy(new PasswordPolicy(8, 128, true, true, true, true, 5,90))
                .build();
        TenantNotificationSettingDocument tenantNotificationSettingDocument = TenantNotificationSettingDocument.builder()
                .tenantId(tenantId.toString())
                .id(ResourceIdentifier.buildChildIdentifiersFromTenantIdentifier(tenantId.toString(), ResourceType.NOTIFICATION_SETTINGS).toString())
                .notificationSettings(new NotificationSettings(true, false))
                .build();
        UserManagementSettingsDocument userManagementSettingsDocument = UserManagementSettingsDocument.builder()
                .tenantId(tenantId.toString())
                .id(ResourceIdentifier.buildChildIdentifiersFromTenantIdentifier(tenantId.toString(),ResourceType.USER_MANAGEMENT_SETTINGS).toString())
                .userLimit(1000)
                .userCreationModes(userCreationModes)
                .build();
        TenantDocument savedTenantDocument = this.mongoTemplate.save(tenantDocument, TENANT_COLLECTION);
        TenantAuthenticationSettingsDocument savedTenantAuthenticationSettingsDocument = this.mongoTemplate.save(tenantAuthenticationSettingsDocument, AUTHENTICATION_SETTINGS_COLLECTION);
        TenantNotificationSettingDocument savedTenantNotificationSettings = this.mongoTemplate.save(tenantNotificationSettingDocument, NOTIFICATION_SETTINGS_COLLECTION);
        UserManagementSettingsDocument savedUserManagementSettings = this.mongoTemplate.save(userManagementSettingsDocument, USER_MANAGEMENT_SETTINGS_COLLECTION);
        return TenantInformationDTO.builder()
                .tenantId(savedTenantDocument.getId())
                .tenantName(savedTenantDocument.getTenantName())
                .contactInformation(savedTenantDocument.getContactInformation())
                .authenticationSettings(TenantAuthenticationSettings.fromEntity(savedTenantAuthenticationSettingsDocument))
                .notificationSettings(NotificationSettings.fromEntity(savedTenantNotificationSettings))
                .userManagementSettings(UserManagementSettings.fromEntity(savedUserManagementSettings))
                .build();
    }

    @Override
    public Optional<TenantInformationDTO> fetchTenantInformation(ResourceIdentifier tenantId) {
        String resourceDBIdentifier = tenantId.toString();
        Optional<TenantDocument> tenantDocument = Optional.ofNullable(this.mongoTemplate.findById(resourceDBIdentifier, TenantDocument.class, TENANT_COLLECTION));
        Optional<TenantAuthenticationSettingsDocument> tenantAuthenticationSettingsDocument = Optional.ofNullable(this.mongoTemplate.findById(ResourceIdentifier.buildChildIdentifiersFromTenantIdentifier(resourceDBIdentifier, ResourceType.AUTHENTICATION_SETTINGS).toString(), TenantAuthenticationSettingsDocument.class, AUTHENTICATION_SETTINGS_COLLECTION));
        Optional<TenantNotificationSettingDocument> tenantNotificationSettingDocument = Optional.ofNullable(this.mongoTemplate.findById(ResourceIdentifier.buildChildIdentifiersFromTenantIdentifier(resourceDBIdentifier, ResourceType.NOTIFICATION_SETTINGS).toString(), TenantNotificationSettingDocument.class, NOTIFICATION_SETTINGS_COLLECTION));
        Optional<UserManagementSettingsDocument> userManagementSettingsDocument = Optional.ofNullable(this.mongoTemplate.findById(ResourceIdentifier.buildChildIdentifiersFromTenantIdentifier(resourceDBIdentifier, ResourceType.USER_MANAGEMENT_SETTINGS).toString(), UserManagementSettingsDocument.class, USER_MANAGEMENT_SETTINGS_COLLECTION));
        if(tenantDocument.isPresent() && tenantAuthenticationSettingsDocument.isPresent() && tenantNotificationSettingDocument.isPresent() && userManagementSettingsDocument.isPresent()){
            return Optional.of(TenantInformationDTO.builder()
                    .tenantId(tenantDocument.get().getId())
                    .tenantName(tenantDocument.get().getTenantName())
                    .contactInformation(tenantDocument.get().getContactInformation())
                    .authenticationSettings(TenantAuthenticationSettings.fromEntity(tenantAuthenticationSettingsDocument.get()))
                    .notificationSettings(NotificationSettings.fromEntity(tenantNotificationSettingDocument.get()))
                    .userManagementSettings(UserManagementSettings.fromEntity(userManagementSettingsDocument.get()))
                    .build());
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<TenantInformationDTO> updateTenantInformation(ResourceIdentifier tenantId, TenantInformationDTO updatedTenantInformation) {
        return Optional.empty();
    }

    Optional<TenantDocument> fetchTenantDocument(String tenantIdentifier){
        return Optional.ofNullable(this.mongoTemplate.findById(tenantIdentifier, TenantDocument.class, TENANT_COLLECTION));
    }
    Optional<TenantAuthenticationSettingsDocument> fetchTenantAuthenticationSettings(String tenantIdentifier){
        return Optional.ofNullable(this.mongoTemplate.findById(ResourceIdentifier.buildChildIdentifiersFromTenantIdentifier(tenantIdentifier, ResourceType.AUTHENTICATION_SETTINGS).toString(), TenantAuthenticationSettingsDocument.class, AUTHENTICATION_SETTINGS_COLLECTION));
    }
    Optional<TenantNotificationSettingDocument> fetchTenantNotificationSettings(String tenantIdentifier){
        return Optional.ofNullable(this.mongoTemplate.findById(ResourceIdentifier.buildChildIdentifiersFromTenantIdentifier(tenantIdentifier, ResourceType.NOTIFICATION_SETTINGS).toString(), TenantNotificationSettingDocument.class, NOTIFICATION_SETTINGS_COLLECTION));
    }
    @Override
    public void deleteTenantIfPresent(ResourceIdentifier tenantId) {

    }
}
