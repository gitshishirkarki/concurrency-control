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
## Unit Test
Unit test for ObjectOptimisticLockingFailureException is available 
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
URL: localhost:8080/api/v1/suites/{id}/{user}
Method: PUT
Response Payload: 
```shell script
{
    "id": 1,
    "name": "Presidential Suite",
    "status": "BOOKED",
    "bookedBy": "shishir",
    "version": 1
}
```