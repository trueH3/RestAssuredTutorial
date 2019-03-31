package com.mycompany.restassuredtut;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;

public class Chapter1Test {
    
      @Test
    public void requestUsZipCode87_300_checkPlaceNameInResponseBody_expectBrodnica() {
        RestAssured.given().
                when().
                get("http://zippopotam.us/pl/87-300").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Brodnica"));
    }
    
}
