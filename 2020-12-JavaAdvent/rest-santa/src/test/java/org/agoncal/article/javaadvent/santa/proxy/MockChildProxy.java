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

    public List<Child> getAllGoodChildren(String country) {
//        List<Child> children = new ArrayList<>();
//        switch (country) {
//            case "Brazil":
//                for (int i = 0; i < 11; i++) {
//                    children.add(new Child());
//                }
//                break;
//            case "Macau":
//                for (int i = 0; i < 20; i++) {
//                    children.add(new Child());
//                }
//                break;
//        }
//        return children;
        return null;
    }
}
