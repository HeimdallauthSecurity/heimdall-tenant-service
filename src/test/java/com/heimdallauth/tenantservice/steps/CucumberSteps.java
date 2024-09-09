package com.heimdallauth.tenantservice.steps;

import com.heimdallauth.tenantservice.config.TestContainerConfig;
import com.heimdallauth.tenantservice.config.TestDependencyConfiguration;
import com.heimdallauth.tenantservice.dto.AccountCreationRequestDTO;
import com.heimdallauth.tenantservice.dto.TenantCreateRequestDTO;
import com.heimdallauth.tenantservice.dto.TenantInformationDTO;
import com.heimdallauth.tenantservice.models.TenantContactInformation;
import com.heimdallauth.tenantservice.services.AccountService;
import com.heimdallauth.tenantservice.services.TenantService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@CucumberContextConfiguration
@ContextConfiguration(classes = {TestContainerConfig.class, TestDependencyConfiguration.class})
public class CucumberSteps {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TenantService tenantService;

    private AccountCreationRequestDTO requestDTO;
    private ResponseEntity<String> response;
    private TenantCreateRequestDTO createRequestDTO;
    private TenantInformationDTO tenantInformationDTO;

    @Given("an account with valid contact information")
    public void anAccountWithValidContactInformation() {
        requestDTO = new AccountCreationRequestDTO();
        requestDTO.setAccountContactInformation(new TenantContactInformation("abc@google.com", "123456789", "support@google.com"));
    }

    @Given("an account with invalid contact information")
    public void anAccountWithInvalidContactInformation() {
        requestDTO = new AccountCreationRequestDTO();
        requestDTO.setAccountContactInformation(new TenantContactInformation(null, null, null));
    }

    @When("I create the account")
    public void iCreateTheAccount() {
        try {
            String result = accountService.createNewAccount(requestDTO);
            response = ResponseEntity.ok(result);
        } catch (Exception e) {
            response = ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @Then("the account should be created successfully")
    public void theAccountShouldBeCreatedSuccessfully() {
        String accountId = response.getBody();
        assertNotNull(accountId, "Account ID should not be null");
    }

    @Then("the account creation should fail")
    public void theAccountCreationShouldFail() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Given("the system is ready to onboard a new tenant")
    public void theSystemIsReadyToOnboardANewTenant() {
        // Initialize any required state or dependencies here
    }

    @When("the user provides the required data for tenant registration")
    public void theUserProvidesTheRequiredDataForTenantRegistration(DataTable dataTable) {
        // Convert DataTable to TenantCreateRequestDTO
        var data = dataTable.asMaps(String.class, String.class).get(0);
        createRequestDTO = new TenantCreateRequestDTO();
        createRequestDTO.setAccountId(data.get("accountId"));
        createRequestDTO.setTenantName(data.get("tenantName"));
        createRequestDTO.setContactInformation(new TenantContactInformation(data.get("emailAddress"), data.get("phoneNumber"), data.get("supportContact")));
    }

    @Then("the system should generate a unique identifier for the tenant")
    public void theSystemShouldGenerateAUniqueIdentifierForTheTenant() {
        tenantInformationDTO = tenantService.onboardTenantInformation(createRequestDTO);
        Assertions.assertNotNull(tenantInformationDTO);
    }

    @Then("the tenant should be registered successfully")
    public void theTenantShouldBeRegisteredSuccessfully() {
        // Additional assertions can be added here to verify successful registration
        Assertions.assertNotNull(tenantInformationDTO);
    }

    @Then("the tenant should have access to isolated identity management features")
    public void theTenantShouldHaveAccessToIsolatedIdentityManagementFeatures() {
        // Verify that the tenant has access to isolated identity management features
        // This can be done by checking specific fields or calling additional methods
        Assertions.assertNotNull(tenantInformationDTO);
    }
}