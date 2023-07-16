# CarDiscover
## Your next destination awaits!
![Logo](./src/main/resources/Logo/LogoWhiteBg.png)

This is a project created with a springboot backend using a MYSQL JDBC with the spring hibernate ORM.

I built this project after 2 weeks of learning Java and used the starter template.
![LogoWithTagline](./src/main/resources/Logo/LogoLargeWithTagline.png)

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

Routes:

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
