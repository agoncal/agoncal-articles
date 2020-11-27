package org.agoncal.article.javaadvent.toy;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.Random;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
public class Toy extends PanacheEntity {

    public String name;
    public String manufacturer;
    public int weight;

    public static Toy findARandomToy() {
        long countToys = Toy.count();
        int randomToy = new Random().nextInt((int) countToys);
        return Toy.findAll().page(randomToy, 1).firstResult();
    }
}
