package org.dcs.testcase.weatherApiEndpoint;

import org.dcs.testcase.TestBase;
import org.dcs.testcase.businesslayer.weather.WeatherApiBusinessLogic;
import org.dcs.testcase.data.weather.GetWeatherInfoByCityDataProvider;
import org.dcs.testcase.data.weather.GetWeatherInfoByDateDataProvider;
import org.dcs.testcase.data.weather.PostWeatherInfoDataProvider;
import org.dcs.testcase.pogo.weather.ListWeatherResponse;
import org.dcs.testcase.pogo.weather.UnauthorizedErrorResponse;
import org.dcs.testcase.pogo.weather.WeatherApiResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.dcs.testcase.constant.ScenarioNameConstant.CRETE_WEATHER_INFO_BY_POST_REQUEST;
import static org.dcs.testcase.constant.ScenarioNameConstant.VALIDATE_SUCCESS_RESPONSE_BODY;
import static org.dcs.testcase.constant.ServiceConstant.*;
import static org.testng.AssertJUnit.assertEquals;

public class WeatherInfoTescase extends TestBase {
    private final String baseUrl = prop.getProperty(BASE_URL);
    private final String username = prop.getProperty(USERNAME);
    private final String password = prop.getProperty(PASSWORD);
    private final String getGetWeatherByDateEndpoint = prop.getProperty(WEATHER_GET_URL_BY_DATE_ENDPOINT);
    private final String getWeatherByDateUrl = baseUrl + getGetWeatherByDateEndpoint;
    private static final Logger log = LoggerFactory.getLogger(WeatherInfoTescase.class);

    /*****************************************************************************
     * Send a Post request to /api/weather and Validate that response
     * returns the expected status code 200 and successful response
     *****************************************************************************/
    @Test(dataProvider = "postWeatherInfo",
            dataProviderClass = PostWeatherInfoDataProvider.class)
    public void testPostResponseToCreateWeatherInfoByDate(String date, String city,
                                                          String temperature, String humidity) {
        log.info(CRETE_WEATHER_INFO_BY_POST_REQUEST + getWeatherByDateUrl);
        JSONObject weatherInfoRequestBody = new JSONObject();
        weatherInfoRequestBody.put("date", date);
        weatherInfoRequestBody.put("city", city);
        weatherInfoRequestBody.put("temperature", temperature);
        weatherInfoRequestBody.put("humidity", humidity);

        String weatherApiResponse = WeatherApiBusinessLogic.postWeatherInformation(weatherInfoRequestBody, username, password);
        Assert.assertEquals(weatherApiResponse, "Weather data added successfully.");
    }

    /*****************************************************************************
     * Send a Get request to /api/weather?date= and Validate that response
     * returns the expected date, city, temperature and humidity
     *****************************************************************************/
    @Test(dependsOnMethods = {"testPostResponseToCreateWeatherInfoByDate"}, dataProvider = "getWeatherInfoByDate",
            dataProviderClass = GetWeatherInfoByDateDataProvider.class)
    public void testGetResponseByDateFetchesCorrectResponse(String date, String city,
                                                            int temperature, int humidity) {
        log.info(VALIDATE_SUCCESS_RESPONSE_BODY + getWeatherByDateUrl);
        ListWeatherResponse weatherApiResponseList = WeatherApiBusinessLogic.getWeatherResponseByDate(date, username, password);
        List<WeatherApiResponse> allWeatherInfoList = weatherApiResponseList.getWeatherApiResponseList();
        System.out.println(weatherApiResponseList.getWeatherApiResponseList());

        for (WeatherApiResponse weatherApiResponse : allWeatherInfoList) {
            softAssert.assertEquals(weatherApiResponse.getDate(), date);
            softAssert.assertEquals(weatherApiResponse.getCity(), city);
            softAssert.assertEquals(weatherApiResponse.getTemperature(), temperature);
            softAssert.assertEquals(weatherApiResponse.getHumidity(), humidity);
            softAssert.assertAll();
        }
    }

    /*****************************************************************************
     * Send a Get request to /api/weather?city= and Validate that response
     * returns the expected date, city, temperature and humidity
     *****************************************************************************/
    @Test(dependsOnMethods = {"testPostResponseToCreateWeatherInfoByDate"}, dataProvider = "getWeatherInfoByCity",
            dataProviderClass = GetWeatherInfoByCityDataProvider.class)
    public void testGetResponseByCityFetchesCorrectResponse(String date, String city,
                                                            int temperature, int humidity) {
        log.info(VALIDATE_SUCCESS_RESPONSE_BODY + getWeatherByDateUrl);
        ListWeatherResponse weatherApiResponseList = WeatherApiBusinessLogic.getWeatherResponseByCity(city, username, password);
        List<WeatherApiResponse> allWeatherInfoList = weatherApiResponseList.getWeatherApiResponseList();
        System.out.println(weatherApiResponseList.getWeatherApiResponseList());

        for (WeatherApiResponse weatherApiResponse : allWeatherInfoList) {
            softAssert.assertEquals(weatherApiResponse.getDate(), date);
            softAssert.assertEquals(weatherApiResponse.getCity(), city);
            softAssert.assertEquals(weatherApiResponse.getTemperature(), temperature);
            softAssert.assertEquals(weatherApiResponse.getHumidity(), humidity);
            softAssert.assertAll();
        }
    }

    /*****************************************************************************
     * Send a Get request to /api/weather?date= and Validate that response
     * returns the expected Data type validations
     *****************************************************************************/
    @Test(dependsOnMethods = {"testPostResponseToCreateWeatherInfoByDate"}, dataProvider = "getWeatherInfoByDate",
            dataProviderClass = GetWeatherInfoByDateDataProvider.class)
    public void testGetResponseByDateFetchesCorrectDataTypeValidation(String date, String city,
                                                                      int temperature, int humidity) {
        log.info(VALIDATE_SUCCESS_RESPONSE_BODY + getWeatherByDateUrl);
        ListWeatherResponse weatherApiResponseList = WeatherApiBusinessLogic.getWeatherResponseByDate(date, username, password);
        List<WeatherApiResponse> allWeatherInfoList = weatherApiResponseList.getWeatherApiResponseList();
        System.out.println(weatherApiResponseList.getWeatherApiResponseList());

        for (WeatherApiResponse weatherApiResponse : allWeatherInfoList) {
            softAssert.assertEquals(weatherApiResponse.getDate(), date);
            softAssert.assertEquals(weatherApiResponse.getDate().getClass(), String.class);
            softAssert.assertEquals(weatherApiResponse.getCity().getClass(), String.class);
            softAssert.assertEquals(((Object) weatherApiResponse.getTemperature()).getClass(), Integer.class);
            softAssert.assertEquals(((Object) weatherApiResponse.getTemperature()).getClass(), Integer.class);
            softAssert.assertAll();
        }
    }

    /*****************************************************************************
     * Send a Get request to /api/weather?city= and Validate that response
     * returns the expected Data type validations
     *****************************************************************************/
    @Test(dependsOnMethods = {"testPostResponseToCreateWeatherInfoByDate"}, dataProvider = "getWeatherInfoByCity",
            dataProviderClass = GetWeatherInfoByCityDataProvider.class)
    public void testGetResponseByCityFetchesCorrectDataTypeValidation(String date, String city,
                                                                      int temperature, int humidity) {
        log.info(VALIDATE_SUCCESS_RESPONSE_BODY + getWeatherByDateUrl);
        ListWeatherResponse weatherApiResponseList = WeatherApiBusinessLogic.getWeatherResponseByCity(city, username, password);
        List<WeatherApiResponse> allWeatherInfoList = weatherApiResponseList.getWeatherApiResponseList();
        System.out.println(weatherApiResponseList.getWeatherApiResponseList());

        for (WeatherApiResponse weatherApiResponse : allWeatherInfoList) {
            softAssert.assertEquals(weatherApiResponse.getCity(), city);
            softAssert.assertEquals(weatherApiResponse.getDate().getClass(), String.class);
            softAssert.assertEquals(weatherApiResponse.getCity().getClass(), String.class);
            softAssert.assertEquals(((Object) weatherApiResponse.getTemperature()).getClass(), Integer.class);
            softAssert.assertEquals(((Object) weatherApiResponse.getTemperature()).getClass(), Integer.class);
            softAssert.assertAll();
        }
    }

    /*****************************************************************************
     * Send a Get request to /api/weather?city= and Validate unauthorized error
     *****************************************************************************/
    @Test(dependsOnMethods = {"testPostResponseToCreateWeatherInfoByDate"}, dataProvider = "getWeatherInfoByCity",
            dataProviderClass = GetWeatherInfoByCityDataProvider.class)
    public void testUnauthorizedErrorResponse(String date, String city,
                                              int temperature, int humidity) {
        log.info(VALIDATE_SUCCESS_RESPONSE_BODY + getWeatherByDateUrl);
        UnauthorizedErrorResponse unauthorizedErrorResponse = WeatherApiBusinessLogic
                .errorResponse(city);

        assertEquals(unauthorizedErrorResponse.getError(), "Unauthorized");
    }

    /*****************************************************************************
     * Send a Get request to /api/weather?city= and Validate timeout response
     *****************************************************************************/
    @Test(dependsOnMethods = {"testPostResponseToCreateWeatherInfoByDate"}, dataProvider = "getWeatherInfoByCity",
            dataProviderClass = GetWeatherInfoByCityDataProvider.class)
    public void testGetResponseByCityResponseTimeout(String date, String city,
                                                     int temperature, int humidity) {
        log.info(VALIDATE_SUCCESS_RESPONSE_BODY + getWeatherByDateUrl);
        ListWeatherResponse weatherApiResponseList = WeatherApiBusinessLogic.setTimeoutOfWeatherResponseByCity(city, username, password);
        List<WeatherApiResponse> allWeatherInfoList = weatherApiResponseList.getWeatherApiResponseList();
        System.out.println(weatherApiResponseList.getWeatherApiResponseList());

        for (WeatherApiResponse weatherApiResponse : allWeatherInfoList) {
            assertEquals(weatherApiResponse.getCity(), city);
        }
    }


}
