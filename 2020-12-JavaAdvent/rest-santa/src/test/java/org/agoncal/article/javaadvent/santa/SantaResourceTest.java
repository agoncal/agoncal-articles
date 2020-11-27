package org.agoncal.article.javaadvent.santa;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SantaResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/santa")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}