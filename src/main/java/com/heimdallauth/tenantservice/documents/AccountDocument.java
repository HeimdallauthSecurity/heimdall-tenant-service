package com.heimdallauth.tenantservice.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class AccountDocument {
    @Id
    private String accountId;
    private String accountAdminEmailAddress;
    private String organizationName;
    private String requesterFullName;
    private Instant creationTimestamp;
    private Instant updateTimestamp;
    private Instant verificationTimestamp;
}
