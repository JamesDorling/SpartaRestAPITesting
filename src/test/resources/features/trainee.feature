Feature: Testing the CRUD operations for trainees

  Background: Connected to the database
    Given I am connected to the database

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

        Then The trainee should be updated to have the name "Thomas" "Anderson", a course ID of 2, a start date of "2022-03-03", and an end date of "2022-04-28"

        Scenario: Searching for a trainee by first name
          When I search for first name "Testy"
          Then I should receive all trainees whose first name contains that

          Scenario: Searching for a trainee by last name
            When I search for last name "McTesterson"
            Then I should receive all trainees whose last name contains that

            Scenario: Searching for trainees that started before a certain date
              When I search before date "2020-01-01"
              Then I should receive all trainees that started before that date

              Scenario: Searching for trainees that started after a certain date