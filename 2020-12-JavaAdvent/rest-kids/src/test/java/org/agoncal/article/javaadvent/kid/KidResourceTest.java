package org.agoncal.article.javaadvent.kid;
//@formatter:off

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class KidResourceTest {

    @Test
    public void getAllKidsFromBrazil() {
        given()
            .param("country", "Brazil").
        when()
            .get("/api/kids").
        then()
            .statusCode(200)
            .body("size()", is(4));
    }

    @Test
    public void getAllKidsFromMacau() {
        given()
            .param("country", "Macau").
        when()
            .get("/api/kids").
        then()
            .statusCode(200)
            .body("size()", is(5));
    }

    @Test
    public void getAllKidsFromUnkown() {
        given()
            .param("country", "Unkown").
        when()
            .get("/api/kids").
        then()
            .statusCode(200)
            .body("size()", is(0));
    }

    @Test
    public void getAllKidsWithNoParam() {
        given().
        when()
            .get("/api/kids").
        then()
            .statusCode(200)
            .body("size()", is(0));
    }
}
