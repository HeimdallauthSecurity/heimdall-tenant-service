package com.heimdallauth.tenantservice.exceptions;

import com.heimdallauth.tenantservice.dto.TenantInformationDTO;

public class InvalidTenantDataException extends RuntimeException {
  public InvalidTenantDataException(String message) {
    super(message);
  }
}
