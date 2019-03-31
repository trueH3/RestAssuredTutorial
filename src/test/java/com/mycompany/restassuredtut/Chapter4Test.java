package com.mycompany.restassuredtut;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class Chapter4Test {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createRequestAndResponseSpecification() {

        requestSpec = new RequestSpecBuilder().setBaseUri("http://api.zippopotam.us").build();
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();

    }

    @Test
    public void shouldRespondWith200CodeJsonBodyAndBeverlyHills() {
        RestAssured.given().
                spec(requestSpec).
                when().
                get("us/90210").
                then().
                spec(responseSpec).
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));

    }
    
// I can extract different things from response body using extract()
    @Test
    public void theSameAsAboveUsingStringExtract() {
        String name
                = RestAssured.
                        given().
                        spec(requestSpec).
                        when().
                        get("us/90210").
                        then().
                        spec(responseSpec).
                        extract().path("places[0].'place name'");

        assertEquals("Beverly Hills", name);

    }
}