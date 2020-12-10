package org.agoncal.article.javaadvent.santa.proxy;

import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
@Mock
@ApplicationScoped
@RestClient
public class MockPresentProxy implements PresentProxy {

    public Present getAPresent() {
        return new Present("mock toy");
    }
}
