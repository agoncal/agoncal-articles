package org.agoncal.article.nubesgen.githubactions;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.startsWith;

// tag::adocSnippet[]
@QuarkusTest
public class GreetingResourceTest {

  @Test
  public void testHelloEndpoint() {
    given()
      .when().get("/hello")
      .then()
      .statusCode(200)
      .body(startsWith("Hello NubesGen"));
  }
}
