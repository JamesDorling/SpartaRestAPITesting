Feature: Testing the CRUD operations for trainees

    Scenario: Creating a new trainee
      When I enter first name "Testy"
      And I enter last name of "McTesterson"
      And I enter course ID of 1
      And I enter start date of "2022-02-02"
      And I send a POST request

      Then A trainee should be created with the name "Testy" "McTesterson", a course ID of 1, a start date of "2022-02-02", and an end date of "2022-03-30"

      Scenario: Updating an existing trainee
        When I enter first name "Thomas"
        And I enter last name of "Anderson"
        And I enter course ID of 2
        And I enter start date of "2022-03-03"
        And I send a PUT request

        Then The trainee should be updated to have the name {string} {string}, a course ID of {int} a start date of {string}, and an end date of {string}