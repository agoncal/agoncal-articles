package org.agoncal.article.javaadvent.kid;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
public class Kid extends PanacheEntity {

    public String name;
    public String address;
    public boolean chimney;
    public boolean naughty;
    public String country;

    public static List<Kid> findNiceKidsByCountry(String country) {
        return list("country = ?1 and naughty = false", country);
    }
}
