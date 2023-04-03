# Weather Information API with PyTest(Tavern)

This application is designed for Backend Testing of WeWebservices using Python with PyTest and Tavern plugin.

## Technology Stack

- Python
- PyTest
- Tavern
- Docker

## Prerequisites

* [Python](https://www.python.org/downloads/)
* [Tavern](https://pypi.org/project/tavern/)
* [Docker](https://www.docker.com/products/docker-desktop/)

## Choose your favorite IDE

Open it on your favorite IDE, my personal suggestion is Visual Code studio Or PyCharm. You can download it from the following URLs respectively.

https://code.visualstudio.com/download
https://www.jetbrains.com/pycharm/download/

## Project Structure

>test_weather_api.py: It contains all the test cases for weather api backend services using python

>test_weather_api.tavern.yaml: It contains all the test cases for weather api backend services using yaml

>.gitignore: list of gitignore items

>Dockerfile: It contains the docker configuration to build a docker image for pytest application


## Getting Started

We are using Weather Information APIs as Application Under Test.

* URL : http://{baseUrl}/api/weather

Following instructions will help you to run the project.

## Important Pre Requisite

In order to execute backend api tests, we need to make sure weather-information-api application is up and listening on port 8080. If yes, then we also need to make sure to change baseUrl of this project from baseURL=http://weather-info:8080 to baseURL=http://localhost:8080.

## How to execute application using Docker

In order to run the application using docker, go to root folder of weather-information-data-api-test directory. Open a terminal from there and execute following commands,

```bash
docker build -t tavern-test .
```

Once the image is successfully built. Then execute a container using following command,

```bash
docker run -t tavern-test
```

It will automatically start the application on the terminal of the container. You should be able to see Tests getting executed

## How to Stop the application

In order to stop the application, you need to terminate the application by pressing CTRL + C command.

## How to edit the code or Re build the application

If anyone wants to extend or edit the code, after all the changes please make sure to repeat those steps mentioned above from "How to execute application using Docker".
