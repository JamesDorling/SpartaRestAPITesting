Feature: Testing the CRUD and search operations for trainees

  Background: Connected to the database
    Given I am connected to the database

    Scenario: Creating a new trainee
      When I enter first name of "Testy"
      And I enter last name of "McTesterson"
      And I enter course ID of 1
      And I enter start date of "2022-02-02"
      And I send a POST request

      Then A trainee should be created with the name "Testy" "McTesterson", a course ID of 1, a start date of "2022-02-02", and an end date of "2022-03-30"

    Scenario: Updating an existing trainee
      When I enter ID of "620533cf0578f2530e926194"
      And I enter first name of "Thomas"
      And I enter last name of "Anderson"
      And I enter course ID of 2
      And I enter start date of "2022-03-03"
      And I send a PUT request

      Then The trainee should be updated to have the name "Thomas" "Anderson", a course ID of 2, a start date of "2022-03-03", and an end date of "2022-04-28"

    Scenario: Deleting a trainee
      When I create a new trainee with dummy values
      And I send a DELETE request

      Then The dummy trainee should be removed from the database

    Scenario: Searching for a trainee by first name
      When I search for first name "Testy"
      Then I should receive all trainees whose first name contains that

    Scenario: Searching for a trainee by last name
      When I search for last name "McTesterson"
      Then I should receive all trainees whose last name contains that

    Scenario: Searching for trainee by full name
      When I search for first name "Testy"
      And I search for last name "McTesterson"
      Then I should receive all trainees whose first and last names contain those

    Scenario: Searching for trainees that started before a certain date
      When I search: started before "2025-01-01"
      Then I should receive all trainees that started before that date

    Scenario: Searching for trainees that started after a certain date
      When I search: started after "2023-01-01"
      Then I should receive all trainees that started after that date

    Scenario: Searching for trainees that started on a certain date
      When I search: started on "2022-02-02"
      Then I should receive all trainees that started on that date

    Scenario: Searching for trainees that finished before a certain date
      When I search: finished before "2022-04-15"
      Then I should receive all trainees that finished before that date

    Scenario: Searching for trainees that finished after a certain date
      When I search: finished after "2023-01-01"
      Then I should receive all trainees that finished after that date

    Scenario: Searching for trainees that finished on a certain date
      When I search: finished on "2023-04-02"
      Then I should receive all trainees that finished on that date

    Scenario: Searching for trainee by ID
      When I search for id "6203d4425a7a6c0b58931ba5"
      Then I should receive the trainee that matches that id