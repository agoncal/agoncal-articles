package org.agoncal.article.javaadvent.santa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.agoncal.article.javaadvent.santa.proxy.Child;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
public class Schedule extends PanacheEntity {

    public int year;
    public String country;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Delivery> deliveries = new ArrayList<>();

    public Schedule() {
    }

    public Schedule(int year, String country) {
        this.year = year;
        this.country = country;
    }

    public static Optional<Schedule> findByYearAndCountry(int year, String country) {
        return find("year = ?1 and country = ?2", year, country).firstResultOptional();
    }

    public void addDelivery(Child child) {
        deliveries.add(new Delivery(child));
    }

    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
    }
}
