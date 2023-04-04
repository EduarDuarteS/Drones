# README

### Project Description

This project is a REST API service developed using Java Spring Framework and Java 17. The goal of the project is to allow clients to communicate with a fleet of drones, which are capable of delivering small loads of medications. The service provides the ability to register a drone, load a drone with medication items, check loaded medication items for a given drone, check available drones for loading, and check the battery level of a given drone.

### Functional Requirements

- The service should prevent the drone from being loaded with more weight than it can carry.
- The service should prevent the drone from being in LOADING state if the battery level is below 25%.
- The service should introduce a periodic task to check drones' battery levels and create history/audit event logs for this.

### Non-Functional Requirements

- Input/output data must be in JSON format.
- The project must be buildable and runnable.
- The project must have a README file with build/run/test instructions (use a database that can be run locally, e.g., in-memory, via container).
- Any data required by the application to run (e.g., reference tables, dummy data) must be preloaded in the database.
- JUnit tests are optional but advisable (if you have time).
- Commit history should be clear and well-documented.


### Build Instructions

1. Clone the repository.
2. Ensure you have JDK 17 and Gradle 7.6.1 installed on your machine.
3. Open a terminal in the project root directory.
4. Run the command **gradle build** to build the project.


### Run Instructions

1. Ensure you have JDK 17 and Gradle 7.6.1 installed on your machine.
2. Open a terminal in the project root directory.
3. Run the command `gradle bootRun` to start the application.


### Test Instructions

1. Ensure you have JDK 17 and Gradle 7.6.1 installed on your machine.
2. Open a terminal in the project root directory.
3. Run the command `gradle test` to run the unit tests.


### Database

The application uses an H2 in-memory database for development and a PostgreSQL database for production. To change the database, modify the `application-prod.properties` file with the appropriate database details.

To switch between development and production environments, change the `spring.profiles.active=dev` parameter in the `application.properties` file.

### Dependencies

    - jakarta.persistence:jakarta.persistence-api:3.1.0
    - org.postgresql:postgresql:42.3.1
    - commons-codec:commons-codec:1.15
    - javax.validation:validation-api:2.0.1.Final
    - org.springframework.boot:spring-boot-starter-data-jpa
    - org.springframework.boot:spring-boot-starter-web
    - org.projectlombok:lombok
    - com.h2database:h2
    - org.projectlombok:lombok
    - org.springframework.boot:spring-boot-starter-test







### Services

1. DroneService: This service would be responsible for managing the drones. It would have methods for registering a new drone, loading medication items onto a drone, checking loaded medication items for a given drone, and checking available drones for loading. You could name the DTOs for this service as follows:

    - DroneRequestDto: DTO for registering a new drone
    - LoadMedicationRequestDto: DTO for loading medication items onto a drone
    - LoadedMedicationResponseDto: DTO for checking loaded medication items for a given drone
    - AvailableDronesResponseDto: DTO for checking available drones for loading

2. BatteryService: This service would be responsible for managing the drone battery levels. It would have a method for checking the drone battery level for a given drone:
     - DroneBatteryResponseDto: DTO for checking the drone battery level for a given drone


### REST API endpoints implemented:

1. POST /api/drones - Registers a new drone. Payload should include drone serial number, model, weight limit, and battery capacity.

2. PUT /api/drones/{drone_id}/load PUT /api/drones/{drone_id}/load - Loads medication items onto the drone with the specified ID. Payload should include medication name, weight, and code.

3. GET /api/drones/{drone_id}/medicationItems - Retrieves a list of medication items loaded onto the drone with the specified ID.

4. GET /api/drones/availableForLoading - Retrieves a list of available drones that can be loaded with medication items.

5. GET /api/drones/{drone_id}/battery - Retrieves the battery level of the drone with the specified ID.