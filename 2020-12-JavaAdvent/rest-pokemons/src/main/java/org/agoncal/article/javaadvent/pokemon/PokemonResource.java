package org.agoncal.article.javaadvent.pokemon;

import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/pokemons")
@Produces(MediaType.APPLICATION_JSON)
public class PokemonResource {

    private static final Logger LOGGER = Logger.getLogger(PokemonResource.class);

    @GET
    @Path("/random")
    public Pokemon getARandomPokemon() {
        LOGGER.info("Get a random pokemon");
        return Pokemon.findARandomPokemon();
    }

    @GET
    public List<Pokemon> getAllPokemons() {
        return Pokemon.findAll().list();
    }
}
