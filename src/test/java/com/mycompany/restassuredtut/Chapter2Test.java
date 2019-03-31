package com.mycompany.restassuredtut;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Test;

public class Chapter2Test {
  
      @Test
    public void checkState() {
        RestAssured.given().
                when().
                get("http://zippopotam.us/pl/87-300").
                then().
                assertThat().
                body("places[0].state", equalTo("Kujawsko-Pomorskie"));
    }
    
      @Test
    public void checkHasItem() {
        RestAssured.given().
                when().
                get("http://zippopotam.us/pl/87-300").
                then().
                assertThat().
                body("places.latitude", hasItem("53.25")).
                and().
                body("places.latitude", hasSize(1));
    }

    @Test
    public void checkifResponseCode200() {
        RestAssured.
                given().
                when().
                get("http://zippopotam.us/pl/87-300").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void CheckResponseBodyJson() {
        RestAssured.
                given().
                when().
                get("http://zippopotam.us/pl/87-300").
                then().
                assertThat().
                contentType(ContentType.JSON);
    }

    @Test
    public void logRequestDetails() {

        RestAssured.
                given().
                log().all().
                when().
                get("http://zippopotam.us/pl/87-300");
    }

    @Test
    public void logResponseDetails() {

        RestAssured.
                given().
                when().
                get("http://zippopotam.us/pl/87-300").
                then().
                log().all();
    }
    
}
