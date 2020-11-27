package org.agoncal.article.javaadvent.toy;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ToyResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/toys")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}