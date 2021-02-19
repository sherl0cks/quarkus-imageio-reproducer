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
                .when().get("/imageio/success/png")
                .then().statusCode(200)
                .body(is("Quarkus png logo width: 289"));
    }

    @Test
    public void testFailJpgEndpoint() {
        given()
                .when().get("/imageio/fail/jpg")
                .then()
                .statusCode(200)
                .body(is("Quarkus png logo width: 289. Rewritten jpeg width: 289"));
    }

    /**
     * This is the reproducer for https://github.com/quarkusio/quarkus/blob/master/core/runtime/src/main/java/io/quarkus/runtime/graal/Java2DSubstitutions.java. Fails in native.
     */
    @Test
    public void testFailTiffEndpoint() {
        given()
                .when().get("/imageio/fail/tiff")
                .then()
                .statusCode(200)
                .body(is("Tiff image width: 997"));
    }

}