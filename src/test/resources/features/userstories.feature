Feature: Just getting all the user stories down off the Jira so stepdefs can be written at some point
  Background: Connected
    Given I am connected to the database
    And I have an API key

#  Scenario: Checking total number of trainees
#    Given I want to know what the total number of trainees are
#    When I query the total number of employees
#    Then I should receive the sum total of trainees
#
#  Scenario: Storing data in NoSQL database
#    Given I am connected to the NoSQL database
#    When I want to save an employee
#    Then They are stored in the database
#
#  Scenario: Checking all available courses
#    Given I am connected to the NoSQL database
#    When I search by course endpoint
#    Then I should be shown all the courses that are on offer

  Scenario: Assigning trainees to a course
    Given I am creating a new course
    When I enter a name and length
    Then I want to be able to assign trainees to it

  Scenario: Functions restricted to API key

    When I send a put request to a course endpoint
    Then I should be able to update the course length

    When I create a new course with a trainee on it
    And I send a put request to a course endpoint that updates the course endpoint
    Then All trainees on that course should have their end date adjusted

#    When I send a put request
#    Then I should be able to update a trainee's end date
#
#    When I send a DELETE request with an employee ID
#    Then The trainee should be deleted from the database


  Scenario: Searching the database

    When I send a GET request with course name
    Then I should receive all trainees on that course

    When I send a GET request with a start date
    Then I should receive all trainees who started ON that date

    When I send a GET request with first name "firstName" and last name "lastName"
    Then I should receive all trainees called "firstName" "lastName"

    When I send a GET request with an end date
    Then I should receive all trainees that finish ON that date

    When I send a GET request with an ID endpoint
    Then I should receive back the trainee with that ID

