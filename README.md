# Hotelmanagementsystem-Backend with gRPC in Java using Quarkus

This repository contains the backend project of a Hotelmanagementsystem implementing the hexagonal architecture in Java using the Quarkus framework. It includes an API with gRPC, a core domain component, and a persistence component using JPA and Hibernate. It uses the H2 database.

Please download and watch hotelmanagement-demo.mp4 to get a quick introduction into this project.


## Unit Testing

* We implemented UnitTests for all components
* All UnitTests get automatically executed during the Build-Process
## Integration Testing

* The integration test of our project is: `HotelmanagementTest.java` or interactive with the CLI.

## Docker - How to start the Application:

##### Step 1: Make sure you have Docker running on your device. Additionally, please ensure that you are not using a Java version newer than Java 22.
##### Step 2: Create a Docker image and run the tests (all UnitTests and the integration test):

```shell script
mvn verify
```

##### Step 3: Start the container as a CLI:

```shell script
docker run -it hotelmanagementsystem/cli:latest
```




