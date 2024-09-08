package com.heimdallauth.tenantservice.controller;

import com.heimdallauth.tenantservice.dto.AccountCreationRequestDTO;
import com.heimdallauth.tenantservice.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
    private final AccountService accountService;

    @Autowired
    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Create a new account", description = "Creates a new account with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public ResponseEntity<String> createNewAccount(@RequestBody AccountCreationRequestDTO accountCreationRequestDTO){
        return ResponseEntity.ok(this.accountService.createNewAccount(accountCreationRequestDTO));
    }
}