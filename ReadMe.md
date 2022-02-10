# Testing Framework for Sparta Database API

### A Sparta Global java engineer course project.

SpartaRestAPITesting is a testing framework for the Sparta Rest API.

## Table of contents
* [Setup](#Using-the-Framework) <br> <br>
* [DTOs](#Data-Transfer-Objects)
* - [Trainee](#TraineeDTO)
* - [Course](#CourseDTO) 
* - [Lists](#DTO-Lists) <br> <br>
* [Crud Forms](#CRUD-Forms) 
* [Contributors](#Contributors)


## Using the Framework

In order to use the framework the tester must be running the partner project: https://github.com/GeoGregory/SpartaRESTAPI <br>
and
not be running an application that utilises localhost8080 url

### Config Requirements

In order to use the framework with full functionality, a config.properties file must be created containing an API Key for
the Sparta Rest API  <br>
This property is retrieved programmatically via the Config class and is used in construction of the full url of the API.

#### Properties:
- api_key=[Your API Key]


##Data Transfer Objects
All DTOs implement the DTO interface, which is empty, for the purpose of dependency inversion.

###TraineeDTO

Extending the basic POJO for the Trainee, the trainee DTO has a host of functionality for the tester. <br>
- Simple boolean methods for returning whether or not a value is null, there is also a function that checks for any data values being null.
- Returning the full name of a trainee
- Returning each start and end dates as a LocalDate
- Functionality for checking that the start date is before the end date and the end date is after the start date
- Functionality for getting the courseId and returning the name of the corresponding course
- Functionality for returning the trainee as a Json

###CourseDTO
This DTO also contains simple boolean methods for returning whether or not a value is null, as well as a method checking if any value is null 

###DTO Lists

Both DTOs have respective list objects as well. These lists are used to transfer all objects of that type.

##CRUD Forms
These forms are used to construct Jsons for use in performing CRUD operations

## Contributors
###Sparta Rest API Scrum Master
Harry Jones
###Testing Framework lead
James Dorling<br>
###Mooks
Louis Clement-Harris<br>
Callum Davis-Keogh<br>
Reshman Siva<br>
Tony Parsons <br>
Melvin Thomas
