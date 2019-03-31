package com.mycompany.restassuredtut;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class Chapter3Test {

    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][]{
            {"us", "90210", "Beverly Hills"},
            {"us", "12345", "Schenectady"},
            {"pl", "87-300", "Brodnica"}
        };
    }

    @Test
    @UseDataProvider("zipCodesAndPlaces")
    public void requestZipCodesFromCollection_checkPlaceNameInResponseBody_expectSpecifiedPlaceName(String countryCode, String zipCode, String expectedPlaceName) {
        RestAssured.given().
                pathParam("countryCode", countryCode).
                pathParam("zipCode", zipCode).
                when().
                get("http://zippopotam.us/{countryCode}/{zipCode}").
                then().
                assertThat().
                body("places[0].'place name'", equalTo(expectedPlaceName));
    }
}
