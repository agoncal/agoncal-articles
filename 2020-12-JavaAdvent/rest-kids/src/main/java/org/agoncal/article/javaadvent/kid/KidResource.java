package org.agoncal.article.javaadvent.kid;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

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
@Produces(MediaType.APPLICATION_JSON)
public class KidResource {

    private static final Logger LOGGER = Logger.getLogger(KidResource.class);

    /**
     * curl "http://localhost:8702/api/kids?country=Angola" | jq
     * curl "http://localhost:8702/api/kids?country=Venezuela" | jq
     */
    @GET
    @APIResponse(responseCode = "200", description = "Returns the good kids per country", content = @Content(schema = @Schema(implementation = Kid.class, type = SchemaType.ARRAY)))
    public List<Kid> getAllKidsPerCountry(@Parameter(required = true) @QueryParam("country") String country) {
        LOGGER.info("Get all the kids from " + country);
        return Kid.findNiceKidsByCountry(country);
    }
}
