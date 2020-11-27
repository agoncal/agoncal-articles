package org.agoncal.article.javaadvent.santa;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SantaResourceTest {

    @Test
    public void getAScheduleForBrazilIn2019() {
        given()
            .param("country", "Brazil")
            .param("year", 2019).
        when()
            .get("/api/santa").
        then()
            .statusCode(200)
                .body("stops.size()", is(11));
    }

    @Test
    public void getAScheduleForPortugalIn2019() {
        given()
            .param("country", "Macau")
            .param("year", 2019).
        when()
            .get("/api/santa").
        then()
            .statusCode(200)
            .body("stops.size()", is(20));
    }
}
