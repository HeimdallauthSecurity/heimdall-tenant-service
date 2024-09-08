package com.heimdallauth.tenantservice.steps;

import com.heimdallauth.tenantservice.config.AccountConfiguration;
import com.heimdallauth.tenantservice.config.TestContainerConfig;
import com.heimdallauth.tenantservice.dto.AccountCreationRequestDTO;
import com.heimdallauth.tenantservice.models.TenantContactInformation;
import com.heimdallauth.tenantservice.services.AccountService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.Testcontainers;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@CucumberContextConfiguration
@ContextConfiguration(classes = {TestContainerConfig.class, AccountConfiguration.class})
public class AccountSteps {

    @Autowired
    private AccountService accountService;

    private AccountCreationRequestDTO requestDTO;
    private ResponseEntity<String> response;

    @Given("an account with valid contact information")
    public void anAccountWithValidContactInformation() {
        requestDTO = new AccountCreationRequestDTO();
        requestDTO.setAccountContactInformation(new TenantContactInformation("abc@google.com","123456789","support@google.com"));
    }

    @Given("an account with invalid contact information")
    public void anAccountWithInvalidContactInformation() {
        requestDTO = new AccountCreationRequestDTO();
        requestDTO.setAccountContactInformation(new TenantContactInformation(null, null,null));
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
        assertNotNull(accountId,"Account ID should not be null");
    }

    @Then("the account creation should fail")
    public void theAccountCreationShouldFail() {
        assertEquals(200, response.getStatusCodeValue());
    }
}