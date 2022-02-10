# Testing Framework for Sparta Database API

### A project for the Sparta Global java engineering course.

SpartaRestAPITesting is a testing framework for the Sparta Rest API.

## Table of contents
* [Setup](#Using-the-Framework) <br> <br>
* [DTOs](#Data-Transfer-Objects)
* - [Trainee](#TraineeDTO)
* - [Course](#CourseDTO) 
* - [Lists](#DTO-Lists) <br> <br>
* [Crud Forms](#CRUD-Forms) <br> <br>  
* [Connecting](#Connecting)
* - [Building a Url](#Url-Builder)
* - [Getting a Response](#Connection-Manager)
<br> <br>
* [Injecting Data](#Injector)
<br> <br>
* [Extending The Framework](#Extending-The-Framework)
* -
<br> <br>
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
These classes are used to construct Jsons for use in performing CRUD operations

##Connecting

###Url Builder
The url builder is used by the Connection Manager to build urls. It allows the tester to navigate to Spartans and Courses pages 
and add the parameters that the API utilizes. 

###Connection Manager
The connection manager is used for constructing the url, receiving HttpResponses, and sending Http requests.
The send requests functions take in a Json, created by a CRUD Form, and a string url in order to perform a CRUD operation.

##Injector
This class contains functions for Object Mapping through a url or a stub.
The function InjectDTO does not give functionality for mapping through a stub, to map through a stub use the InjectDTOFromFile function.

##Extending The Framework
If a tester/engineer wishes to extend the framework, for example: adding training complexes, they will need to do the following.
- A newly created DTO needs to implement the DTO interface
- A new field needs to be added to the DTOEnum to include the newly created object
- The injectDTO and InjectDTOFromFile functions within the Injector class need to have a new case statement within the switch statements for the new object.




## Contributors
###Sparta Rest API Scrum Master
Harry Jones
###Testing Framework lead
James Dorling<br>
###Additional Engineers
Louis Clement-Harris<br>
Callum Davis-Keogh<br>
Michael Makam <br>
Tony Parsons <br>
Reshman Siva<br>
Melvin Thomas 
