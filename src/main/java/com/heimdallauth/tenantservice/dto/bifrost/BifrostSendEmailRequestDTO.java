package com.heimdallauth.tenantservice.dto.bifrost;

import com.heimdallauth.tenantservice.constants.bifrost.MailType;

public record BifrostSendEmailRequestDTO(
        String profileId,
        MailType mailType
) {
}
