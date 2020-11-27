package org.agoncal.article.javaadvent.santa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Stop extends PanacheEntity {

    public String kidName;
    public String kidAddress;
    public String kidChimney;
    public String toyName;
}
