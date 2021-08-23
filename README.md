# Project: VehicleAd

Test description: https://docs.google.com/document/d/1cKccRTy-EWxCfOK7vit62Nw-cwjf7sVzVP1Elt9KqgI

Technologies:
* Java 11
* Spring Boot 2.5
* Spring Boot Validation
* Spring Boot Data
* Project Lombok
* FlywayDB
* MySQL
* H2 Database
* Orika
* OpenAPI v3.0
* QueryDSL
* Mockito Framework
* JUnit 5 for Unit Tests
* MockMvc for Integration Tests

# ToDo
* Business Rule when changing TierLimit > VehicleDealerService.java Lines: 69 and 93
* Integration Tests > VehicleDealerResourceIntegrationTests.java
* Fix Warning: ObjectMapper JavaTimeModule > when running mvn install

# Recommendations
* Add cache on Redis: when changing publications invalidate cache with vehicle dealer id
* Package the app in docker: to make it easy to configure the database along with the app

# Postman Configuration

	Import the file: "./postman/vehicle-ad-api.postman_collection.json"

# Procedure for turning on the database server

	Run the file on the terminal: "./docker/up-local-environment.sh".
	
# Procedure for turning off the database server

	Run the file on the terminal: "./docker/down-local-environment.sh".

# SwaggerUI and API docs

* http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
* http://localhost:8080/v3/api-docs

# Build source code

	Run the file on the terminal: "./java/mvn clean install"
