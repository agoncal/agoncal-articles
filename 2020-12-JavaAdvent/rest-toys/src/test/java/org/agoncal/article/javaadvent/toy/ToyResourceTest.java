package org.agoncal.article.javaadvent.toy;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ToyResourceTest {

    @Test
    void shouldGetARandomToy() {
        given().
        when()
            .get("/api/toys/random").
        then()
            .statusCode(200);
    }

    @Test
    void shouldGetAllToys() {
        given().
        when()
            .get("/api/toys").
        then()
            .statusCode(200)
            .body("size()", is(100));
    }
}
