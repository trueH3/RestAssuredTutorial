package com.mycompany.restassuredtut;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import static org.junit.Assert.*;

public class Chapter6Test {

    //Using deserialization
    @Test
    public void requestUsZipCode90210_checkPlaceNameInDeserializedObject_expectBeverlyHills() {

        Location location
                = RestAssured.
                        given().
                        when().
                        get("http://api.zippopotam.us/us/90210").
                        as(Location.class);
        assertEquals(
                "Beverly Hills",
                location.getPlaces().get(0).getPlaceName()
        );
    }

    //Using Serialization
    @Test
    public void shouldSerializeRequestBody() {
        Location l = new Location();
        l.setCountry("Netherlands");

        RestAssured.
                given().
                contentType(ContentType.JSON).
                body(l).
                log().all().
                when().
                post("http://zippopotam.us/pl/87-300");
    }
}
