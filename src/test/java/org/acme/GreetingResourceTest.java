package org.acme;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testSuccessEndpoint() {
        given()
                .when().get("/imageio/success")
                .then().statusCode(200)
                .body(is("Quarkus png logo width: 289"));
    }

    @Test
    public void testFailEndpoint() {
        given()
                .when().get("/imageio/fail")
                .then()
                .statusCode(200)
                .body(is("Quarkus png logo width: 289. Rewritten jpeg width: 289"));
    }

}