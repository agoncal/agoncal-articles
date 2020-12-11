package org.agoncal.article.javaadvent.santa;

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
// tag::adocSnippet[]
@Path("/api/santa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
public class SantaResource {

    @Inject
    SantaService service;

    // tag::adocSkip[]
    private static final Logger LOGGER = Logger.getLogger(SantaService.class);

    /**
     * curl -X POST -H "Content-Type: text/plain" -d "Portugal" http://localhost:8701/api/santa
     */
    @APIResponse(responseCode = "201", description = "Creates a new 2020 Santa's schedule for a given country")
    // end::adocSkip[]
    @POST
    @Transactional
    public Schedule createASchedule(String country) {
        // tag::adocSkip[]
        LOGGER.info("Creating a schedule for " + country);

        // end::adocSkip[]
        Schedule schedule = service.getAllGoodChildren(country);
        schedule = service.getEachChildAPresent(schedule);
        schedule.persist();
        return schedule;
    }

    // tag::adocSkip[]

    /**
     * curl "http://localhost:8701/api/santa?country=Angola&year=2019" | jq
     * curl "http://localhost:8701/api/santa?country=Venezuela" | jq
     */
    @APIResponse(responseCode = "200", description = "Returns Santa's schedule for a given country and year")
    // end::adocSkip[]
    @GET
    public Optional<Schedule> getASchedule(@QueryParam("country") String country,
                              @DefaultValue("2020") @QueryParam("year") int year) {
        // tag::adocSkip[]
        LOGGER.info("Getting the schedule of " + country + " in " + year);

        // end::adocSkip[]
        return Schedule.findByYearAndCountry(year, country);
    }
}
// end::adocSnippet[]
