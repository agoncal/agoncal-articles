package org.agoncal.article.javaadvent.santa;

import org.agoncal.article.javaadvent.santa.proxy.Child;
import org.agoncal.article.javaadvent.santa.proxy.ChildProxy;
import org.agoncal.article.javaadvent.santa.proxy.PresentProxy;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
@ApplicationScoped
public class SantaService {

    private static final Logger LOGGER = Logger.getLogger(SantaService.class);

    @Inject
    @RestClient
    ChildProxy childProxy;

    @Inject
    @RestClient
    PresentProxy presentProxy;

    @Retry(maxRetries = 5, delay = 1, delayUnit = ChronoUnit.SECONDS)
    @Fallback(fallbackMethod = "getLastYearScheduleForASpecificCountry")
    public Schedule getAllTheChildrenForASpecificCountry(String country) {
        LOGGER.info("Getting the children from " + country);
        Schedule schedule = new Schedule(2020, country);

        List<Child> allChildrenPerCountry = childProxy.getAllChildrenPerCountry(country);
        for (Child child : allChildrenPerCountry) {
            schedule.addStop(child);
        }
        return schedule;
    }

    public Schedule getLastYearScheduleForASpecificCountry(String country) {
        LOGGER.info("Getting last year schedule for " + country);
        return Schedule.findByYearAndCountry(2019, country).get();
    }

    @Fallback(fallbackMethod = "getEachChildASantaToy")
    public Schedule getEachChildAToy(Schedule schedule) {
        LOGGER.info("Getting a few toys");

        for (Stop stop : schedule.stops) {
            stop.toyName = presentProxy.getAToy().name;
        }
        return schedule;
    }

    public Schedule getEachChildASantaToy(Schedule schedule) {
        LOGGER.info("Getting a Santa toy for each child");

        for (Stop stop : schedule.stops) {
            stop.toyName = "Santa Toy";
        }
        return schedule;
    }
}
