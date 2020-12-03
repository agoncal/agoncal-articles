package org.agoncal.article.javaadvent.santa.proxy;

import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
@Mock
@ApplicationScoped
@RestClient
public class MockChildProxy implements ChildProxy {

    public List<Child> getAllChildrenPerCountry(String country) {
        return null;
    }
}
