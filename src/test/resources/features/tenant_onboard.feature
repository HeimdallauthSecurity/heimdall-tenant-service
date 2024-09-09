Feature: Tenant Onboarding

  Scenario: Register a new tenant with required data
    Given the system is ready to onboard a new tenant
    When the user provides the required data for tenant registration
      | accountId    | tenantName | contactInformation    |
      | "account123" | "Tenant A" | "contact@tenantA.com" |
    Then the system should generate a unique identifier for the tenant
    And the tenant should be registered successfully
    And the tenant should have access to isolated identity management features