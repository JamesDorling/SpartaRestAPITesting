# Testing Framework for Sparta Database API

### A project for the Sparta Global java engineering course.

SpartaRestAPITesting is a testing framework for the Sparta Rest API.

<!--                                                                                     branch  -->
[Sparta-Global](https://raw.githubusercontent.com/JamesDorling/SpartaRestAPITesting/main/src/test/resources/Sparta-Global.png)
<!--![Sparta-Global](src/test/resources/images/Sparta-Global.png) --->

## Contents
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


## Data Transfer Objects
All DTOs implement the DTO interface, which is empty, for the purpose of dependency inversion.

### TraineeDTO

Extending the basic POJO for the Trainee, the trainee DTO has a host of functionality for the tester. <br>
- Simple boolean methods for returning whether or not a value is null, there is also a function that checks for any data values being null.
- Returning the full name of a trainee 
- Returning each start and end dates as a LocalDate
- Functionality for checking that start and end dates are before or after a given date
- Functionality for checking that the start date is before the end date and the end date is after the start date <br>
- Functionality for getting the courseId and returning the name of the corresponding course
- Functionality for returning the trainee as a Json



    public String getFullName();

    public LocalDate getStartDateAsDate();
    public LocalDate getEndDateAsDate();

    public boolean startIsBefore(LocalDate date);
    public boolean startIsAfter(LocalDate date);
    public boolean endIsBefore(LocalDate date);
    public boolean endIsAfter(LocalDate date);

    public boolean firstNameIsNotNull();
    public boolean lastNameIsNotNull();
    public boolean startDateIsNotNull();
    public boolean endDateIsNotNull();
    public boolean idIsNotNull();
    public boolean courseIdIsNotNull();

    public boolean noDataIsNull();

    public boolean startIsBeforeEnd();
    public boolean endIsAfterStart();
    public String getCourseName();
    public String getTraineeAsJson();






### CourseDTO
This DTO also contains simple boolean methods for returning whether or not a value is null, as well as a method checking if any value is null 

    public boolean idIsNotNull();
    public boolean courseIdIsNotNull();
    public boolean courseNameIsNotNull();
    public boolean lengthIsNotNull();
    public boolean descriptionIsNotNull();
    public boolean isActiveIsNotNull();
    public boolean linksIsNotNull();

### DTO Lists

Both DTOs have respective list objects as well. These lists are used to handle all objects of that type. <br>
The list DTOs themselves are empty. The classes are only there to covert their POJO parent classes into DTOs. 

## CRUD Forms
These classes are used to construct Jsons for use in performing CRUD operations. <br>

    //Constructs the Json required to peform the CRUD operation of the classes name sake
    public AddCourseForm(String courseName, int length, String description);
    public AddTraineeForm(String firstName, String lastName, Integer courseId, String startDate);
    public UpdateCourseForm(String id, Integer courseId, String courseName, Integer length, String description, boolean active)
    public UpdateTraineeForm(String id, String firstName, String lastName, Integer courseId, String startDate)

## Connecting

### Url Builder
The url builder is used by the Connection Manager to build urls. It allows the tester to navigate to the Spartans and Courses pages 
using the following functions:
    
    //These methods append the baseUrl to navigate to their respective pages
    public UrlBuilder spartan(); 
    public UrlBuilder course();

There are functions that append the baseUrl link to allow for navigation to specific entries within the page:

    public String getSpecificSpartan(String id);
    public String getSpecificCourse(Integer id);

Testers are also able to navigate to pages with their API Key:

    public String getSpartanWithKey();
    public String getCourseWithKey();

The UrlBuilder is also capable of appending the link with the parameters that the API provides

#### Trainee

    public UrlBuilder firstName(String name);
    public UrlBuilder lastName(String name);
    public UrlBuilder courseId(Integer id);
    public UrlBuilder date(String date);
    public UrlBuilder courseName(String course);
    public UrlBuilder length(String length);

    //                              BEFORE, AFTER, SAME
    public UrlBuilder BeforeAfter(TimeParameters parameter)
    //                                 START, END
    public UrlBuilder StartEnd(TimeParameters parameter);

    public UrlBuilder searchByDate(TimeParameters BeforeOrAfter, TimeParameters StartOrEnd, String date);

The UrlBuilder is also capable of deleting trainees.

    public String deleteSpartan(String id);

#### Course

    public UrlBuilder active(boolean active);
    public String getAllActiveCourses();
    public String getAllInactiveCourses();

### Connection Manager
The connection manager is used for fetching the url, receiving HttpResponses, and sending Http requests.
The send requests functions take in a String(Json), created by a CRUD Form, and a string url in order to perform a CRUD operation.

#### Responses

    public static String getURL();
    public static String getConnection();
    public static UrlBuilder makeUrl();
    public static int getStatusCode();
    public static int getStatusCode(String url);
    private static HttpResponse<String> getResponse(String url);
    //Gets response from /spartans
    private static HttpResponse<String> getResponse();
    private static HttpResponse<String> receiveResponse(String url);

#### CRUD Requests

    public static HttpResponse<String> sendPostRequest(String newJson, String url);
    public static HttpResponse<String> sendPutRequest(String newJson, String url);
    public static HttpResponse<String> sendDeleteRequest(String url);

## Injector
This class contains functions for Object Mapping through a url or a stub.
The function InjectDTO does not give functionality for mapping through a stub, to map through a stub use the InjectDTOFromFile function.

    public static DTO injectDTO(String URL, DTOEnum dtoType);
    public static DTO injectDTOFromFile(String file, DTOEnum dtoType);

## Extending The Framework
### Requirements
If a tester/engineer wishes to extend the framework, for example: adding training complexes, they will need to do the following.
- A newly created DTO needs to implement the DTO interface
- A new field needs to be added to the DTOEnum to include the newly created object
- The injectDTO and InjectDTOFromFile functions within the Injector class need to have a new case statement within the switch statements for the new object.

### Recommendations
- Update the UrlBuilder with functions that append the base link with the desired page as well as any parameters
- Create CRUD form classes within the crud_forms package


## Contributors
### Sparta Rest API Scrum Master
Harry Jones
### Testing Framework lead
James Dorling<br>
### Additional Engineers
Louis Clement-Harris<br>
Callum Davis-Keogh<br>
Michael Makam <br>
Tony Parsons <br>
Reshman Siva<br>
Melvin Thomas 
