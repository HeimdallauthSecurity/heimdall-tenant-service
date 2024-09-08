Feature: Account Creation

  Scenario: Successfully create a new account
    Given an account with valid contact information
    When I create the account
    Then the account should be created successfully

  Scenario: Fail to create an account with invalid contact information
    Given an account with invalid contact information
    When I create the account
    Then the account creation should fail