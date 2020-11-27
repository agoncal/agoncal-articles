package org.agoncal.article.javaadvent.kid;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class KidResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/kids")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}