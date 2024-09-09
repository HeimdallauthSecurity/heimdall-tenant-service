package com.heimdallauth.tenantservice.documents;

import com.heimdallauth.tenantservice.constants.TenantStatus;
import com.heimdallauth.tenantservice.models.TenantContactInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TenantDocument {
    @Id
    private String id;
    private String tenantName;
    private String tenantDescription;
    private TenantContactInformation contactInformation;
    private TenantStatus tenantStatus;
    private Instant createdOn;
    private Instant updatedOn;
}
