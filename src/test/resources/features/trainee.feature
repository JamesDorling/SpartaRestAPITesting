Feature: Testing the CRUD operations for trainees

  Background: Connected to the database
#    Given I am connected to the database
#    And I have an API key

    Scenario: Creating a new trainee
      When I enter first name "Testy"
      And I enter last name of "McTesterson"
      And I enter course ID of 1
      And I enter start date of "2022-01-01"
      And I send a POST request

#      Then A trainee should be created with the name "Testy" "McTesterson", a course ID of 1, a start date of "2022-01-01", and an end date of "2022-02-26"