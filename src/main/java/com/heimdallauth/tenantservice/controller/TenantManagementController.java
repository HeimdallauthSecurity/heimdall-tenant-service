package com.heimdallauth.tenantservice.controller;

import com.heimdallauth.tenantservice.dto.TenantCreateRequestDTO;
import com.heimdallauth.tenantservice.dto.TenantInformationDTO;
import com.heimdallauth.tenantservice.services.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage/tenants")
public class TenantManagementController {
    private final TenantService tenantService;

    @Autowired
    public TenantManagementController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @Operation(summary = "Create a new tenant", description = "Creates a new tenant with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tenant created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public ResponseEntity<TenantInformationDTO> createTenant(@RequestBody TenantCreateRequestDTO tenantDTO) {
        return ResponseEntity.ok(tenantService.onboardTenantInformation(tenantDTO));
    }
}