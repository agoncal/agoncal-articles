package org.agoncal.article.javaadvent.kid;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
@Path("/api/kids")
public class KidResource {

    /**
     * curl "http://localhost:8702/api/kids?country=Angola" | jq
     * curl "http://localhost:8702/api/kids?country=Venezuela" | jq
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kid> getAllKidsPerCountry(@QueryParam("country") String country) {
        return Kid.findByCountry(country);
    }
}
