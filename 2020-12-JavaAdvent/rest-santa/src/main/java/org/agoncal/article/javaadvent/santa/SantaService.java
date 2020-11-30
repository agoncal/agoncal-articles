package org.agoncal.article.javaadvent.santa;

import org.agoncal.article.javaadvent.santa.kid.Child;
import org.agoncal.article.javaadvent.santa.kid.ChildProxy;
import org.agoncal.article.javaadvent.santa.toy.PresentProxy;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class SantaService {

    private static final Logger LOGGER = Logger.getLogger(SantaService.class);

    @Inject
    @RestClient
    ChildProxy kidProxy;

    @Inject
    @RestClient
    PresentProxy pokemonProxy;

    public Schedule getAllTheChildrenForASpecificCountry(Schedule schedule, String country) {
        LOGGER.info("Getting the children from " + country);

        List<Child> allChildrenPerCountry = kidProxy.getAllChildrenPerCountry(country);
        for (Child child : allChildrenPerCountry) {
            schedule.addStop(child);
        }
        return schedule;
    }

    public Schedule getEachChildAToy(Schedule schedule) {
        LOGGER.info("Getting a few toys");

        for (Stop stop : schedule.stops) {
            stop.toyName = pokemonProxy.getAToy().name;
        }
        return schedule;
    }
}
