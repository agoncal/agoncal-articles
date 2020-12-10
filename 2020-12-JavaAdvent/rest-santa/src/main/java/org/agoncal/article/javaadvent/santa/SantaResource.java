package org.agoncal.article.javaadvent.santa;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
@Path("/api/santa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
public class SantaResource {

    private static final Logger LOGGER = Logger.getLogger(SantaService.class);

    @Inject
    SantaService service;

    /**
     * curl -X POST -H "Content-Type: text/plain" -d "Portugal" http://localhost:8701/api/santa
     */
    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = "Creates a new 2020 Santa's schedule for a given country")
    public Schedule createASchedule(@RequestBody(description = "country", required = true) String country) {
        LOGGER.info("Creating a schedule for " + country);

        LOGGER.info("Getting all the nice children from " + country);
        Schedule schedule = service.getAllTheChildrenForASpecificCountry(country);
        LOGGER.info("Getting " + schedule.stops.size() + " children a present");
        schedule = service.getEachChildAToy(schedule);
        schedule.persist();
        return schedule;
    }

    /**
     * curl "http://localhost:8701/api/santa?country=Angola&year=2019" | jq
     * curl "http://localhost:8701/api/santa?country=Venezuela" | jq
     */
    @GET
    @APIResponse(responseCode = "200", description = "Returns Santa's schedule for a given country and year")
    public Optional<Schedule> getASchedule(@QueryParam("country") String country, @DefaultValue("2020") @QueryParam("year") int year) {
        LOGGER.info("Getting the schedule of " + country + " in " + year);

        return Schedule.findByYearAndCountry(year, country);
    }
}
