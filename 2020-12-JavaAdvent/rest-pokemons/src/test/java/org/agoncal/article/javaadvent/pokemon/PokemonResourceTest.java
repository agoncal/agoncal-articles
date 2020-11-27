package org.agoncal.article.javaadvent.pokemon;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PokemonResourceTest {

    @Test
    void shouldGetARandomPokemon() {
        given().
        when()
            .get("/api/pokemons/random").
        then()
            .statusCode(200);
    }

    @Test
    void shouldGetAllPokemons() {
        given().
        when()
            .get("/api/pokemons").
        then()
            .statusCode(200)
            .body("size()", is(100));
    }
}
