package org.agoncal.article.javaadvent.santa.proxy;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
@Path("/api/pokemons/random")
@RegisterRestClient
public interface PresentProxy {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Present getAToy();
}
