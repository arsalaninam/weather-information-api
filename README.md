# Weather Information API

Weather Information API is built using Java with Spring boot Framework. 

There are two backend test frameworks developed in order to test the APIs.

1. PyTest with Tavern Plugin using Python
2. RestAssured with TestNG using Java and Maven

## Technology Stack

- Spring Boot
- Java
- Redis Cache
- Python
- PyTest
- Tavern
- RestAssured
- TestNG
- Maven
- Docker

## Pre requisite

In order to execute the application, please install Docker Desktop on your host machine.
You can easily download it from https://www.docker.com/products/docker-desktop/

Once it is downloaded and installed, kindly run Docker server on your host machine. You need to make sure Docker desktop
is running on background.

## Choose your favorite IDE

Open it on your favorite IDE, some famous IDEs for Python and Java based applications are Visual Code studio, PyCharm and IntelliJ. You can download it from the following URLs respectively.

https://code.visualstudio.com/download

https://www.jetbrains.com/pycharm/download/

https://www.jetbrains.com/idea/download/

## Directories
>Directory (weather-information-api)

>>config: This contains all the Redis Cache related configurations

>>controller: It contains all the GET and POST web controllers

>>model: It contains Weather model object

>>service: It contains the service layer of weather api

>>application.properties: It contains application properties

>>test: It contains unit tests of weather api

>>Dockerfile: It contains the docker configuration to build a docker image for pytest application

>Directory (weather-information-data-api-test)

>>test_weather_api.py: It contains all the test cases for weather api backend services using python

>>test_weather_api.tavern.yaml: It contains all the test cases for weather api backend services using yaml

>>Dockerfile: It contains the docker configuration to build a docker image for pytest application

>Directory (weather-information-data-api-test-java)

>>businesslayer: This directory contains all the business logics to retrieve pojo and restassured responses

>>constant: This package contains all the required constants of services, scenario names etc.
 
>>data: This directory contains TestNG Data Provider Classes
 
>>pojo: This directory contains all the response pojos of each weather api endpoint
 
>>util: This package contains Utility Classes
 
>>config: This package contains configurations, service endpoints and baseUrl
 
>>testcase: This directory contains test cases for each coop api endpoint

>>Dockerfile: It contains the docker configuration to build a docker image of java-test which can be also used in docker-compose

>docker-compose: This is the docker-compose yaml configuration which helps us to build all the docker images and helps us to execute all the containers with single command and the api and tests will also execute automatically


## How to execute application

In order to run the application, go to root folder of the directory. Open a terminal from there and execute following commands,

```bash
docker compose build
```

Once the images are successfully built. Then execute,

```bash
docker compose up
```

It will automatically start the weather api on port 8080. It will create a default network and all the containers will be executed within same docker network to communicate with each other. Once the weather api Spring Boot application is up and ready to listen on port 8080. It will automatiaclly execute the java and python backend test frameworks and execute the test cases.


## How to Stop the application

In order to stop the application, you need to terminate the application by pressing CTRL + C command. Once the application is terminated, you can execute the following command to clean up the containers created by docker-compose file,

```bash
docker compose down
```

## How to edit the code or Re build the application

If anyone wants to extend or edit the code, after all the changes please make sure to repeat those steps mentioned above from "How to execute application".
