package com.heimdallauth.tenantservice.documents;

import com.heimdallauth.tenantservice.models.TenantContactInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class AccountDocument {
    @Id
    private String accountId;
    private TenantContactInformation contactInformation;
}
