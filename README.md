# CarDiscover
## Your next destination awaits!
![Logo](/backend/cardiscover-backend/src/main/resources/Logo/LogoWhiteBg.png)

This is a project created with a springboot backend using a MYSQL JDBC with the spring hibernate ORM.

I'm using Angular for the frontend with Material-UI.

![LogoWithTagline](/backend/cardiscover-backend/src/main/resources/Logo/LogoLargeWithTagline.png)


The frontend is Single Page App without any routing. I use a few global flags to maintain the overall application, otherwise each component maintains its own state.

I chose to go this route because I have limited application states. This is just a fancy form =)

Global flags:
editView - makes a GET to retrieve all cars, shows the car results and shows all action buttons component
addCarView - 


On navigating to the homepage, these flags get set:




At the end of the day, this is just a fancy form =)

The car object represents a collection of relationships with all other objects:
Supplier
Agency
Location
Reservation
Rate
Brand
SpecialtyClass - "Luxury, wheel-chair accessible, electric, etc."
Size
Model
Color

Each of the above objects can have a list of Car objects associated. 

This makes collecting relationship-specific data, (i.e. get all suppliers that have cars with the rate of $13.65 per day), much easier. 

API Routes:

`/get-all-cars`

`/delete-all-cars`

`/create-car` - requires the following body params:
    
    *location: String
    
    *brand: String
    
    *size: String
    
    *model: String
    
    *color: String
    
    *supplier: String
    
    *rate: Float
    
    *doLocation: String 
    
    *puLocation: String
    
*Note that all the params are setup as a get-or-create. No routes are setup to check before creating.
You can always delete and recreate a car object

`/get-car` - requires a request param car ID: integer

`/book-car` - requires a request param car ID: integer
