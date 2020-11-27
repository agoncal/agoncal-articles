package org.agoncal.article.javaadvent.santa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
public class SantaDeliverySchedule extends PanacheEntity {

    public int year;
    public String country;
    @OneToMany
    public List<Stop> stops;

//    public static SantaDeliverySchedule findByYearAndCountry(int year, String country) {
//        return find("country", country);
//    }
}
