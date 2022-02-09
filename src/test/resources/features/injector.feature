@injector
Feature: As a tester, I want to make sure the injector is working

#  Scenario: Injecting from a file
#    Given Data has been injected from a file
#    When I check trainee first name
#    Then First name should not be null
#
#    Given Data has been injected from a file
#    When I check trainee first name
#    Then First name should be "Keri"
    
    Scenario: Testing against the database
      Given I am connected to the database
      When I check the first trainee's name
      Then First name should be "Hendrix"