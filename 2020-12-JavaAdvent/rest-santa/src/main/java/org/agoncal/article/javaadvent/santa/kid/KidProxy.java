package org.agoncal.article.javaadvent.santa.kid;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

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
@Path("/api/kids")
@RegisterRestClient
public interface KidProxy {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Child> getAllKidsPerCountry(String country);
}
