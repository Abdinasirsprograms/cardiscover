# CarDiscover
## Your next destination awaits!
![Logo](./src/main/resources/Logo/LogoWhiteBg.png)

A backend web project created with Java JDK 17.

I'm using the Spring Web MVC (model-view-controller) framework with a MYSQL database.

![LogoWithTagline](./src/main/resources/Logo/LogoLargeWithTagline.png)


[`CarDiscover.java`](./src/main/java/com/abdi/cardiscover/CarDiscover.java) class is the entry point, if you're using an IDE that has langauge support for Java & Maven, it's listed in the [`pom.xml`](./pom.xml) file. If the project fails to launch with "no entry point found,' it means that the `start-class` node in the pom file isn't being parsed or read properly. 

# Overview
## Database

Spring's Data `JPA` (Jakarta Persistance APIs) framework uses the Domain-Driven Design (DDD) Repository pattern by generating queries based on method name conventions. Database tables/models are defined in the `repository` folder. 

* You can write MySQL statements by injecting Spring Data's `datasource` into a controller. Then use `preparedstatements()` to prepare the MySQL statements, finally excute the respective operation - i.e `executeUpdate().` 
I chose to stick with Hibernate within Spring's Data JPA because it makes it easy to implement CRUD (create-read-update-delete) biolerplate operations by just using method names instead of testing and validating MySQL statements seperately. 
Spring Data JPA uses the Java ORM library `Hibernate` as it's JPA provider by default. 

`application.properties.sample` file defines the database configuration settings - copy it and rename it to `application.properties` so spring can setup the ORM properly. `spring.jpa.hibernate.ddl-auto` property is set to update. 

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

