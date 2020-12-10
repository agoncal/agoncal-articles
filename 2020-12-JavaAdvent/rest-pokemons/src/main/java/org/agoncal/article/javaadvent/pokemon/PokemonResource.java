package org.agoncal.article.javaadvent.pokemon;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
@Path("/api/pokemons")
@Produces(MediaType.APPLICATION_JSON)
public class PokemonResource {

    private static final Logger LOGGER = Logger.getLogger(PokemonResource.class);

    @GET
    @Path("/random")
    @APIResponse(responseCode = "200", description = "Returns a random Pokemon")
    public Pokemon getARandomPokemon() {
        LOGGER.info("Get a random Pokemon");
        return Pokemon.findARandomPokemon();
    }

    @GET
    @APIResponse(responseCode = "200", description = "Returns all the Pokemons", content = @Content(schema = @Schema(implementation = Pokemon.class, type = SchemaType.ARRAY)))
    public List<Pokemon> getAllPokemons() {
        LOGGER.info("Returns all the Pokemons");
        return Pokemon.findAll().list();
    }
}
