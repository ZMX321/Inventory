REI.com Interview
====================
This project contains the core functionality, although rudimentary for a basic e-commerce website. We have filled in hard-coded values to reduce the complexity of the application so that the context is much smaller. 

This application is built on top of [Spring Boot 2.1.4](https://spring.io/projects/spring-boot) and uses built in functionality in Spring to implement REST services, repositories and services.



Prerequisites
-----------------

There are a few things you'll need to have installed in order to get this up and running.

**Java**

- [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/index.html)


**Maven**

- [version 3.6.1](https://maven.apache.org/download.cgi)


### Running this application ###

To run this application from the command line with Maven:

`mvn spring-boot:run`

Or in an IDE, just run the `Application` class

Verify that the application is functioning as expected by checking: http://localhost:8080/


Overview
-----------

We have provided a few basic services for use in our e-commerce application: 
- ProductService - get products by their id & verify if a product is valid
- CartService - add a product to a cart
- LocationService - get nearby stores of a given location
- InventoryService - checks if inventory exists for a product in a nearby store or in the distribution center
- DistributionCenterService - checks if inventory exists in the distribution center

#### REST Services ####

We currently only have one web service for clients to interact with, our CartWebService. This service allows clients to add a product to a cart.

Cool, but what do I need to do...
-----------
What we're asking from you, the engineer assigned to this project, is to go to the "new-features" directory and work through those requirements. We have tried to mimic typical tasks that our engineering staff would work through on a given day. Our intention is to try to make this as real-world as possible, so we're more interested in seeing you reuse libraries, take advantage of built-in Spring functionality and have good design and code maintenance.  

*There are bugs in the code, if you find one, go ahead and fix it! If you see something you think could be implemented better, refactor it.* 

Things to keep in mind
-----------

*  Commit directly to this repository
*  Make/document any assumptions needed to complete the exercise
*  You have three days to contribute your solution to this repository
*  Please don't spend the entire three days working on this exercise.  Try to limit to 3-4 hours.
*  Please do not share this exercise with anyone but us.
*  Have fun!


