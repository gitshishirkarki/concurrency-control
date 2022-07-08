# Concurrency Control in REST API - Spring Boot

In modern software system that we are building, it is not common for having thousands and even millions of users independently and concurrently interactingwith our resources. Concurrency is everywhere in modern programming, whether we like it or not. We want to avoid the situations when changes made by oneclient is overridden by another client without even knowing. So, concurrency control comes in picture, in order to prevent these kind of situations violating our data integrity.

## Locking 
### Pessimistic Locking
The pessimistic locking model prevents simultaneous updates to records. As soon as one user starts to update a record, a lock is placed on it. Other users who attempt to update this record are informed that another user has an update in progress.

### Optimistic Locking
Optimistic locking allows multiple users to attempt to update the same record without informing the users that others are also attempting to update the record. The record changes are validated only when the record is committed.

## Getting Started
- Open JDK 11
- MySQL
- Gradle 

## Installation Process
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.shishir.concurrency.MainApplication class from your IDE.
Alternatively you can use the [Spring Boot Gradle Plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins.html#build-tool-plugins.gradle) like so:

```shell
gradle bootRun
```

### MySQL configuration 
MySQL configuration can be found in application.properties as

```shell script
spring.datasource.url=jdbc:mysql://localhost:3306/concurrencyDB?useSSL=false&createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=admin
```

Here, database name used is concurrencyDB with username as 'root' and password as 'admin'. 

Note that the database will be created from the system if not found.
## Problem scenario and brain-storming
The details about the specific problem scenario and brain-storming details are available as PDF under resources folder. Also, you can click [THIS LINK](https://github.com/gitshishirkarki/concurrency-control/blob/main/src/main/resources/spring-boot-optimistic-locking.pdf) to redirect to the document.

## Unit Test
Unit test for ObjectOptimisticLockingFailureException is available in com.shishir.concurrency.service.OptimisticLockingTest class.

## API Details
### GET Available Suites
URL : localhost:8080/api/v1/suites

Method: GET

Response Payload:

```shell script
[
    {
        "id": 1,
        "name": "Presidential Suite",
        "status": "AVAILABLE",
        "bookedBy": null,
        "version": 0
    }
]
```

### Make a booking
URL: localhost:8080/api/v1/suites/{id}/bookings

Method: PUT

Request Payload: 
```shell script
{
    "version":1,
    "user":"shishir"
}
```

Success Response Payload: 

HttpStatus: 200

```shell script
{
    "id": 1,
    "name": "Presidential Suite",
    "status": "BOOKED",
    "bookedBy": "shishir",
    "version": 1
}
```

Failure Response Payload: 

HttpStatus: 406

```shell script
{
    "id": null,
    "name": null,
    "status": null,
    "bookedBy": null,
    "version": null
}
```