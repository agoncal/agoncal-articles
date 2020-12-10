package org.agoncal.article.javaadvent.santa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.agoncal.article.javaadvent.santa.proxy.Child;

import javax.persistence.Entity;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
public class Delivery extends PanacheEntity {

    public String childName;
    public String childAddress;
    public boolean childHasChimney;
    public String presentName;

    public Delivery() {
    }

    public Delivery(Child child) {
        childName = child.name;
        childAddress = child.address;
        childHasChimney = child.chimney;
    }
}
