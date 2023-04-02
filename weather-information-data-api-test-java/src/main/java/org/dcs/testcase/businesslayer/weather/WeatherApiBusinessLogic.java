package org.dcs.testcase.businesslayer.weather;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;
import org.dcs.testcase.pogo.weather.ListWeatherResponse;
import org.dcs.testcase.pogo.weather.UnauthorizedErrorResponse;
import org.dcs.testcase.pogo.weather.WeatherApiResponse;
import org.dcs.testcase.util.PropertyReader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

import static org.dcs.testcase.constant.ServiceConstant.*;

public class WeatherApiBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(WeatherApiBusinessLogic.class);
    private static String baseUrl = prop.getProperty(BASE_URL);

    /**
     * Create a weather Information Request Body and POST it
     * <p>
     * e.g. https://{baseUrl}/api/weather
     */
    public static String postWeatherInformation(JSONObject requestBody, String username, String password) {
        RestAssured.defaultParser = Parser.JSON;
        String postWeatherInfoEndpoint = prop.getProperty(WEATHER_POST_URL);
        String url = baseUrl + postWeatherInfoEndpoint;
        log.info("URL to be hit : " + url);
        Response response = RestAssured.given().auth().basic(username, password)
                .contentType(ContentType.JSON).body(requestBody.toString())
                .when().post(url);
        Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not 200");
        return response.getBody().asString();
    }

    /**
     * Extract data as list of Weather Api Response object from the POJO
     * <p>
     * e.g. https://{baseUrl}/api/weather?date={provided date}
     */
    public static ListWeatherResponse getWeatherResponseByDate(String date, String username, String password) {
        RestAssured.defaultParser = Parser.JSON;
        String getWeatherEndpointByDate = prop.getProperty(WEATHER_GET_URL_BY_DATE_ENDPOINT);
        String url = baseUrl + getWeatherEndpointByDate + date;
        log.info("URL to be hit : " + url);
        Response response = RestAssured.given().auth().basic(username, password).when()
                .get(url);
        Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not 200");
        return getWeatherResponseList(response);
    }

    /**
     * Extract data as list of Weather Api Response object from the POJO
     * <p>
     * e.g. https://{baseUrl}/api/weather?city={provided city}
     */
    public static ListWeatherResponse getWeatherResponseByCity(String city, String username, String password) {
        RestAssured.defaultParser = Parser.JSON;
        String getWeatherEndpointByCity = prop.getProperty(WEATHER_GET_URL_BY_CITY_ENDPOINT);
        String url = baseUrl + getWeatherEndpointByCity + city;
        log.info("URL to be hit : " + url);
        Response response = RestAssured.given().auth().basic(username, password).when()
                .get(url);
        Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not 200");
        return getWeatherResponseList(response);
    }

    /**
     * Extract data as Unauthorized POJO from response
     * <p>
     * e.g. https://{baseUrl}/api/weather?city={provided city}
     */
    public static UnauthorizedErrorResponse errorResponse(String city) {
        RestAssured.defaultParser = Parser.JSON;
        String getWeatherEndpointByCity = prop.getProperty(WEATHER_GET_URL_BY_CITY_ENDPOINT);
        String url = baseUrl + getWeatherEndpointByCity + city;
        log.info("URL to be hit : " + url);
        Response response = RestAssured.given().when()
                .get(url);
        Assert.assertEquals(response.getStatusCode(), 401, "Response status code is not 401");
        return getUnauthorizedErrorResponse(response);
    }

    /**
     * Extract rest assured response
     * <p>
     * e.g. https://{baseUrl}/api/weather?city={provided city}
     */
    public static Response apiResponse(String city, String username, String password) {
        RestAssured.defaultParser = Parser.JSON;
        String getWeatherEndpointByCity = prop.getProperty(WEATHER_GET_URL_BY_CITY_ENDPOINT);
        String url = baseUrl + getWeatherEndpointByCity + city;
        log.info("URL to be hit : " + url);
        return RestAssured.given().auth().basic(username, password).when().get(url);
    }

    /**
     * Sending a GET request without query parameter
     * <p>
     * e.g. https://{baseUrl}/api/weather
     */
    public static Response getResponseWithoutQueryParameter(String username, String password) {
        RestAssured.defaultParser = Parser.JSON;
        String postWeatherInfoEndpoint = prop.getProperty(WEATHER_POST_URL);
        String url = baseUrl + postWeatherInfoEndpoint;
        log.info("URL to be hit : " + url);
        return RestAssured.given().auth().basic(username, password).when().get(url);
    }

    /**
     * Sending a GET request to error endpoint
     * <p>
     * e.g. https://{baseUrl}/api/weather/error
     */
    public static Response errorEndpoint(String username, String password) {
        RestAssured.defaultParser = Parser.JSON;
        String getErrorEndpoint = prop.getProperty(WEATHER_ERROR_URL);
        String url = baseUrl + getErrorEndpoint;
        log.info("URL to be hit : " + url);
        return RestAssured.given().auth().basic(username, password).when().get(url);
    }

    /**
     * Extract data as list of Weather Api Response object from the POJO
     * and set a particular timeout of 5000 milliseconds
     * <p>
     * e.g. https://{baseUrl}/api/weather?city={provided city}
     */
    public static ListWeatherResponse setTimeoutOfWeatherResponseByCity(String city, String username, String password) {
        RestAssured.defaultParser = Parser.JSON;
        String getWeatherEndpointByCity = prop.getProperty(WEATHER_GET_URL_BY_CITY_ENDPOINT);
        String url = baseUrl + getWeatherEndpointByCity + city;
        log.info("URL to be hit : " + url);
        Response response = RestAssured.given().config(RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 5000)))
                .auth().basic(username, password).when()
                .get(url);
        Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not 200");
        return getWeatherResponseList(response);
    }

    private static ListWeatherResponse getWeatherResponseList(Response response) {
        List<WeatherApiResponse> weatherApiResponse = Arrays.asList(response.getBody().as(WeatherApiResponse[].class));
        ListWeatherResponse listWeatherResponse = new ListWeatherResponse();
        listWeatherResponse.setWeatherApiResponseList(weatherApiResponse);
        return listWeatherResponse;
    }

    private static UnauthorizedErrorResponse getUnauthorizedErrorResponse(Response response) {
        UnauthorizedErrorResponse unauthorizedErrorResponse = response.getBody().as(UnauthorizedErrorResponse.class);
        return unauthorizedErrorResponse;
    }
}
