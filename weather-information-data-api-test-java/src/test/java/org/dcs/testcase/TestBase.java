package org.dcs.testcase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.dcs.testcase.util.ObjectFactory;
import org.dcs.testcase.util.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class TestBase extends PropertyReader {


    private static final Logger log = LoggerFactory.getLogger(TestBase.class);

    protected static RequestSpecification requestSpecification;
    protected static ResponseSpecification responseSpecification;

    // Singleton instance of softAssert
    protected SoftAssert softAssert = ObjectFactory.getSoftAssert();

    @BeforeClass
    public static void setUp() {
        requestSpecification = new RequestSpecBuilder().
                build();

        responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_OK).
                build();
    }
}
