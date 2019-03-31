package com.mycompany.restassuredtut;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Test;


public class Chapter5Test {

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills_usingXMLPath() {

        RestAssured.
                given().
                when().
                get("http://localhost:9876/us/90210").
                then().
                assertThat().
                body("response.places.place.placeName", equalTo("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode24848_checkPlaceNameInResponseBody_expectKleinRheide() {

        RestAssured.
                given().
                when().
                get("http://localhost:9876/de/24848").
                then().
                assertThat().
                body("response.places.place[1].placeName", equalTo("Klein Rheide"));
    }

    @Test
    public void requestUsZipCode24848_checkPlaceNameInResponseBody_expectKleinBennebek() {

        RestAssured.
                given().
                when().
                get("http://localhost:9876/de/24848").
                then().
                assertThat().
                // If i want to refer to last element of collection i use index -1
                body("response.places.place[-1].placeName", equalTo("Klein Bennebek"));
    }

//Xml support also attributes associated with element in this example <place longitude="9.4833" latitude="54.45">
// To refer to an attribute use @
    @Test
    public void requestDeZipCode24848_checkLatitudeForLastPlaceInResponseBody_expect5444() {

        RestAssured.
                given().
                when().
                get("http://localhost:9876/de/24848").
                then().
                assertThat().
                body("response.places.place[-1].@latitude", equalTo("54.4"));
    }

    // This filter works not only with XMLPath but also with JSONPath
    @Test
    public void requestDeZipCode24848_checkSizeOfElementsFromStateSchleswig_Holstein_expect4() {

        RestAssured.
                given().
                when().
                get("http://localhost:9876/de/24848").
                then().
                assertThat().
                body("response.places.place.findAll{it.stateAbbreviation='SH'}", hasSize(4));
    }
}
