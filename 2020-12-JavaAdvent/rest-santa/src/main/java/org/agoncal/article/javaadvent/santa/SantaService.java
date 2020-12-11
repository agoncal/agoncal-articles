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
import javax.persistence.EntityManager;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocSnippet[]
@ApplicationScoped
public class SantaService {

    // tag::adocSkip[]
    private static final Logger LOGGER = Logger.getLogger(SantaService.class);

    @Inject
    EntityManager em;

    // end::adocSkip[]
    // tag::adocChildProxy[]
    @Inject
    @RestClient
    ChildProxy childProxy;

    // tag::adocChildProxyFallback[]
    @Retry(maxRetries = 5, delay = 2, delayUnit = ChronoUnit.SECONDS)
    @Fallback(fallbackMethod = "getLastYearSchedule")
    // end::adocChildProxyFallback[]
    public Schedule getAllGoodChildren(String country) {
        // tag::adocSkip[]
        LOGGER.info("Getting the children from " + country);
        // end::adocSkip[]
        Schedule schedule = new Schedule(2020, country);

        List<Child> allChildrenPerCountry = childProxy.getAllGoodChildren(country);
        for (Child child : allChildrenPerCountry) {
            schedule.addDelivery(child);
        }
        return schedule;
    }
    // tag::adocChildProxyFallback[]

    public Schedule getLastYearSchedule(String country) {
        // tag::adocSkip[]
        LOGGER.info("Getting last year schedule for " + country);
        // end::adocSkip[]
        Schedule schedule = Schedule.findByYearAndCountry(2019, country).get();
        return deepCopy(schedule);
    }
    // end::adocChildProxyFallback[]
    // end::adocChildProxy[]
    // tag::adocSkip[]

    private Schedule deepCopy(Schedule schedule) {
        em.clear();

        Schedule scheduleCopy = new Schedule();
        scheduleCopy.year = 2020;
        scheduleCopy.country = schedule.country;

        for (Delivery delivery : schedule.deliveries) {
            Delivery deliveryCopy = new Delivery();
            deliveryCopy.childName = delivery.childName;
            deliveryCopy.childAddress = delivery.childAddress;
            deliveryCopy.childHasChimney = delivery.childHasChimney;
            scheduleCopy.addDelivery(deliveryCopy);
        }
        return scheduleCopy;
    }

    // end::adocSkip[]
    // tag::adocPresentProxy[]
    @Inject
    @RestClient
    PresentProxy presentProxy;

    // tag::adocPresentProxyFallback[]
    @Fallback(fallbackMethod = "getEachChildSomeLollies")
    // end::adocPresentProxyFallback[]
    public Schedule getEachChildAPresent(Schedule schedule) {
        // tag::adocSkip[]
        LOGGER.info("Getting a few presents");

        // end::adocSkip[]
        for (Delivery delivery : schedule.deliveries) {
            delivery.presentName = presentProxy.getAPresent().name;
        }
        return schedule;
    }

    // tag::adocPresentProxyFallback[]
    public Schedule getEachChildSomeLollies(Schedule schedule) {
        // tag::adocSkip[]
        LOGGER.info("Getting some lollies for each child");

        // end::adocSkip[]
        for (Delivery delivery : schedule.deliveries) {
            delivery.presentName = "Santa Lollies";
        }
        return schedule;
    }
    // end::adocPresentProxyFallback[]
    // end::adocPresentProxy[]
}
// end::adocSnippet[]
