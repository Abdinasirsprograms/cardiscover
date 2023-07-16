# CarDiscover
## Your next destination awaits!
![Logo](./src/main/resources/Logo/LogoWhiteBg.png)

A backend web project created with Java SE 17.

I'm using the Spring Web MVC (model-view-controller) framework with a MYSQL database.

I built this project after 2 weeks of learning Java and used the starter template.
![LogoWithTagline](./src/main/resources/Logo/LogoLargeWithTagline.png)


This project is laid out to have only just have [`CarDiscover.java`](./src/main/java/com/abdi/cardiscover/CarDiscover.java) class as the entry point, if you're using an IDE that has langauge support for Java & Maven, the class entry point is listed in the [`pom.xml`](./pom.xml) file. If the project fails to launch advising there's no entry point found, it means that the `start-class` node within the project -> protperties isn't being parsed or read properly. You can relaunch it by making sure your launching the afromentioned main function. 

# Overview
## Database

Spring's Data `JPA` (Jakarta Persistance APIs) framework abstracts the JPA provider and makes it easy to implement the Domain-Driven Design (DDD) Repository pattern by generating queries based on method name conventions. Database tables/models are defined in the `repository` folder. 

Spring Data JPA uses the Java ORM library `Hibernate` as it's JPA provider by default. 

You can write MySQL statements by injecting Spring Data's `datasource` into a controller. Then use `preparedstatements()` to prepare the MySQL statements, finally excute the respective operation - i.e `executeUpdate().` 

Instead, I chose to stick with Hibernate within Spring's Data JPA because it makes it easy to implement CRUD (create-read-update-delete) biolerplate operations by just using method names instead of testing and validating MySQL statements seperately. 

The application.properties.sample define the database configuration settings - copy it and rename it to application.properties so spring can setup the ORM properly. The `spring.jpa.hibernate.ddl-auto` database schema proeprty is needs to be setup so properly so that if the application crashes unexpectldy and relaunches, you'll be prapared for what spring will do to the database - i.e. `create-drop` delete all the database and re-create it, which is very useful for local dev environment, but not so for prod. This setting can also be set to `none` to ensure spring doesn't do anything to the database scheme.

## Docker
At this time, the Dockerfile does image compose but due to how docker is containerizing and communicating with my host server, I'm unable to get past CORS (Cross Origin Resource Sharing) security policy errors when communicating with my host server and connecting to my host server's MySQL database via Docker's IP `172.17.0.1.` At this time I will need to re-think my entire website's infrastructure to accomodate Docker containers. I'm researching how I can use docker-compose with a MySQL DB service embedded - right now that's my biggest hurdle. The frontend can always be served by apache but I also want to incorporate the frontend. More to come.

## Layout
* Business logic is within `controller` folder with axuillary shared functionality stored within `utility` folder
* API request parameters and request body data received by controllers are defined within `requestbody` folder
* Database tables/models are defined in `repository` folder
* The `entities` folder defines the link to the database tables in Java object form. I'm not using it to store any business logic beyond CRUD operations to minimize sideffects.

## Objects
The `Car` object represents a collection of relationships with all other objects:
* Supplier
* Agency
* Location
* Reservation
* Price
* Make
* SpecialtyClass (Also was debating to call it "AuxillaryClass" - think cars/agencies/suppliers that may offer extra perks or ammenties such Luxury experinces, wheel-chair accessiblites, or a car is all electric, etc.)
* Size
* Model
* Color

Each of the above objects can have a list of Car objects associated. 

This makes the car object nothing more then a thin veneer over the raw data so you can collect relationship-specific data without too much effort (i.e. get all cars with the rate of $13.65 per day that have supplier primary key of 4). 

