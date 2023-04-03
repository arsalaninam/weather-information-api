# Weather Information API

Weather Information API is built using Java with Spring boot Framework.

## Technology Stack

- Spring Boot
- Java
- Redis Cache
- Maven
- Docker

## Pre requisite

In order to execute the application, please install Docker Desktop on your host machine.
You can easily download it from https://www.docker.com/products/docker-desktop/

Once it is downloaded and installed, kindly run Docker server on your host machine. You need to make sure Docker desktop
is running on background.

## Choose your favorite IDE

Open it on your favorite IDE, some famous IDEs for Java based applications are Visual Code studio and IntelliJ. You can download it from the following URLs respectively.

https://code.visualstudio.com/download

https://www.jetbrains.com/idea/download/

## Directories

>config: This contains all the Redis Cache related configurations

>controller: It contains all the GET and POST web controllers

>model: It contains Weather model object

>service: It contains the service layer of weather api

>application.properties: It contains application properties

>test: It contains unit tests of weather api

>Dockerfile: It contains the docker configuration to build a docker image for pytest application


## How to execute application

In order to run the application, go to root folder of the directory weather-information-api. Open a terminal from there and execute following commands,

```bash
docker build -t weather-api .
```

Once the images is successfully built. Then execute,

```bash
docker run -p 8080:8080 -t weather-api
```

It will automatically start the weather api using embedded tomcat server on port 8080 with redis server on port 6379.

## How to Execute APIs on POSTMAN

Simply import Weather-Api.postman_collection.json file and you can test the application via POSTMAN manually. However there are backend automated tests also written in python and java in other directories.

### POST Endpoint to create weather information

```
POST http://localhost:8080/api/weather
Content-Type: application/json
Basic auth : Username and Password 
(you can find it in application.properties)

{
"city" : "karachi",
"date" : "2023-04-03",
"temperature" : "26",
"humidity" : "75"
}

RESPONSE: HTTP 200 OK
RESPONSE MESSAGE : Weather data added successfully.
```

### GET Endpoint to retrieve weather information by date

```
GET http://localhost:8080/api/weather?date=2023-04-03
Content-Type: application/json
Basic auth : Username and Password 
(you can find it in application.properties)

RESPONSE: HTTP 200 OK
RESPONSE BODY : 
[
    {
        "date": "2023-04-03",
        "city": "Karachi",
        "temperature": "26",
        "humidity": "75"
    }
]
```

### GET Endpoint to retrieve weather information by city

```
GET http://localhost:8080/api/weather?city=karachi
Content-Type: application/json
Basic auth : Username and Password 
(you can find it in application.properties)

RESPONSE: HTTP 200 OK
RESPONSE BODY : 
[
    {
        "date": "2023-04-03",
        "city": "Karachi",
        "temperature": "26",
        "humidity": "75"
    }
]
```

### Error Endpoint

```
GET http://localhost:8080/api/weather/error
Content-Type: application/json
Basic auth : Username and Password 
(you can find it in application.properties)

RESPONSE: HTTP 500 INTERNAL SERVER ERROR
RESPONSE BODY : 500 Internal Server Error
```

## How to Stop the application

In order to stop the application, you need to terminate the application by pressing CTRL + C command.

## How to edit the code or Re build the application

If anyone wants to extend or edit the code, after all the changes please make sure to repeat those steps mentioned above from "How to execute application".
