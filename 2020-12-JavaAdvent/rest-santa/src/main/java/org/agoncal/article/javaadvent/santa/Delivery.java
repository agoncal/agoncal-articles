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

    public String kidName;
    public String kidAddress;
    public boolean kidChimney;
    public String toyName;

    public Delivery() {
    }

    public Delivery(Child kid) {
        kidName = kid.name;
        kidAddress = kid.address;
        kidChimney = kid.chimney;
    }
}
