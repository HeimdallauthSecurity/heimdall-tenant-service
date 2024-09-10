package com.heimdallauth.tenantservice.exceptions;

public record ErrorDTO(
        String logId,
        String message,
        String details
) {
}
