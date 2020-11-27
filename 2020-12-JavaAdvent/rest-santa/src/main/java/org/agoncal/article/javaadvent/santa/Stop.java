package org.agoncal.article.javaadvent.santa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.agoncal.article.javaadvent.santa.kid.Child;
import org.agoncal.article.javaadvent.santa.toy.Toy;

import javax.persistence.Entity;

@Entity
public class Stop extends PanacheEntity {

    public String kidName;
    public String kidAddress;
    public boolean kidChimney;
    public String toyName;

    public Stop() {
    }

    public Stop(Child kid, Toy toy) {
        kidName = kid.name;
        kidAddress = kid.address;
        kidChimney = kid.chimney;
        toyName = toy.name;
    }
}
