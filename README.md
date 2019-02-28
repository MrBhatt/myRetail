# myRetail RESTful service

## Description

myRetail RESTful service provides product and pricing information for items in the Target inventory.

myRetail service fetches data from (and posts to):
 * RedSky service for product information (product id's and product description)
 * Pricing API (mock API powered by mocky.io) for a product's pricing information
 
## Tech stack (language and frameworks)
* Java 8.0
* Spring Boot
* Gradle
* lombok

## Build and Test instructions

myRetail service uses Gradle as a build tool. 
It provides a Gradle Wrapper to avoid Gradle as a pre-req for building the application

### Build
To build the application and create self-contained executable Jar

``
./gradlew build
``

The gradle wrapper installs gradle (if not present) and compiles and creates an executable jar

### Run Tests
``
./gradlew test
``

Executes the tests and generates a report

### Run application

To bring up the myRetail service, do one of the following:

* using gradle:  ``./gradlew bootRun``
* using executable jar: locate the build/libs directory after building the application. Execute the jar: ``java -jar myRetail-1.0.jar``

## Accessing the service

myRetail is a RESTful service and can be accessed using any REST client (ex: POSTMAN)

### Available endpoints
*  health check (is the service up?)
```
HTTP method: GET
endpoint: https://<host>/actuator/health

Example: 
GET http://localhost:8080/actuator/health
```
* get product information based on a product id
```
HTTP method: GET
endpoint: https://<host>/products/{id}

Example: 
GET http://localhost:8080/products/13860428

```
* post product information (create a new product entry)
```
HTTP method: POST 
endpoint: https://<host>/products
request body:
    {
      "name": <product name>,
      "current_price": {
         "value": <product price with 2 decimal places>,
         "currency_code": <currency code>
      }
    }

Example: 
POST http://localhost:8080/products
request body: 
    {
      "name":"this is a new product name",
      "current_price": {
         "value":21.48,
         "currency_code":"USD"
      }
    }
```

