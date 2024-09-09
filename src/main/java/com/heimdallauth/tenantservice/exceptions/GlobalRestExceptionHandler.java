package com.heimdallauth.tenantservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String KEY_LOG_ID = "logId";

    @ExceptionHandler(TenantNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleTenantNotFoundException(TenantNotFoundException e, WebRequest request) {
        String logId = UUID.randomUUID().toString();
        MDC.put(KEY_LOG_ID, logId);
        log.error("Requested tenant not found, requestData {}, endpoint: {}", e.tenantResourceName, request.getContextPath());
        MDC.clear();
        return ResponseEntity.status(404).body(new ErrorDTO(logId, "Requested tenant not found", e.getMessage()));
    }

    @ExceptionHandler(InvalidTenantDataException.class)
    public ResponseEntity<ErrorDTO> handleInvalidTenantDataException(InvalidTenantDataException ex, WebRequest request) {
        String logId = UUID.randomUUID().toString();
        MDC.put(KEY_LOG_ID, logId);
        log.error("Invalid tenant data endpoint: {}", request.getContextPath());
        return ResponseEntity.status(400).body(new ErrorDTO(logId, "Invalid tenant data", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGlobalException(Exception ex, WebRequest request) {
        String logId = UUID.randomUUID().toString();
        StackTraceElement[] stackTrace = ex.getStackTrace();
        String logMessageStackTrace = Arrays.stream(stackTrace).map(StackTraceElement::toString).collect(Collectors.joining(" | "));
        MDC.put(KEY_LOG_ID, logId);
        MDC.put("StackTrace", logMessageStackTrace);
        log.error("Internal server error, endpoint: {}", request.getContextPath());
        MDC.clear();
        return ResponseEntity.status(500).body(new ErrorDTO(logId, "Internal server error", ex.getMessage()));
    }
}
