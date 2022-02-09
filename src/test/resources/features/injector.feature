Feature: As a tester, I want to make sure the injector is working

  Scenario: Injecting from a file
    Given Data has been injected from a file
    When I check trainee first name
    Then First name should not be null

    Given Data has been injected from a file
    When I check trainee first name
    Then First name should be "Keri"