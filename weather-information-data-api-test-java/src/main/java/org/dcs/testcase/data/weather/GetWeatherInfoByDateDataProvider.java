package org.dcs.testcase.data.weather;

import org.testng.annotations.DataProvider;

/**
 * A data provider class to provide test data for posting weather information into database
 *
 * @author Arsalan Inam
 */
public class GetWeatherInfoByDateDataProvider {

    @DataProvider
    public static Object[][] getWeatherInfoByDate() {
        return new Object[][]{
                {"2022-04-03", "Karachi", 26 , 76}
        };
    }
}
