# Weather Information API Java Test Framework

This Framework is designed for Backend Testing of WeWebservices using Java with RestAssured and TestNG utilizing Maven as dependency management tool. Data Driven Test strategy is used to design and execute tests utilizing TestNG Data Providers.

## Technology Stack

- Java
- TestNG
- Maven
- RestAssured
- Docker

## Prerequisites

* [Java 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java Dev Kit
* [Maven](https://maven.apache.org/download.cgi) - Dependency Manager
* [Docker](https://www.docker.com/products/docker-desktop/) - Containerize application

## Choose your favorite IDE

Open it on your favorite IDE, my personal suggestion is Visual Code studio Or IntelliJ. You can download it from the following URLs respectively.

https://code.visualstudio.com/download
https://www.jetbrains.com/idea/download

## Project Structure

>businesslayer: This directory contains all the business logics to retrieve pojo and restassured responses

>constant: This package contains all the required constants of services, scenario names etc.

>data: This directory contains TestNG Data Provider Classes

>pojo: This directory contains all the response pojos of each weather api endpoint

>util: This package contains Utility Classes

>config: This package contains configurations, service endpoints and baseUrl

>testcase: This directory contains test cases for each coop api endpoint

>Dockerfile: It contains the docker configuration to build a docker image of java-test which can be also used in docker-compose


## Getting Started

We are using Weather Information APIs as Application Under Test.

* URL : http://{baseUrl}/api/weather

Following instructions will help you to run the project.

## Important Pre Requisite

In order to execute backend api tests, we need to make sure weather-information-api application is up and listening on port 8080. If yes, then we also need to make sure to change baseUrl of this project from baseURL=http://weather-info:8080 to baseURL=http://localhost:8080.

You can find baseUrl config under weather-information-data-api-test-java/src/main/resources/config/config.properties

### Installation

Open the project in any IDE. Run the following command in Terminal and build the project. It will automatically download all the required dependencies.

```sh
$ mvn install -DskipTests
```

If the build is successful. All the required dependencies are installed successfully. But if the build fails, make sure to resolve all the issues in order to execute tests successfully.

### Execute Tests without Docker

Run the following command in Terminal to execute tests.

```sh
$ mvn clean test
```

Or Run the following command in Terminal to execute tests with testng.xml

```sh
$ mvn clean test -DsuiteXmlFile=testng.xml
```

### Test Report

You can find the Surefire HTML reports in the following directory of the Project.

```sh
/target/surefire-reports/index.html
```

Under the surefire-reports directory, open ‘index.html’ file to view reports.

## How to execute application using Docker

In order to run the application using docker, go to root folder of weather-information-data-api-test-java directory. Open a terminal from there and execute following commands,

```bash
docker build -t java-test .
```

Once the image is successfully built. Then execute a container using following command,

```bash
docker run -t java-test
```

It will automatically start the application on the terminal of the container. You should be able to see BUILD SUCCESS message and 27 tests should execute and pass

## How to Stop the application

In order to stop the application, you need to terminate the application by pressing CTRL + C command.

## How to edit the code or Re build the application

If anyone wants to extend or edit the code, after all the changes please make sure to repeat those steps mentioned above from "How to execute application using Docker".
