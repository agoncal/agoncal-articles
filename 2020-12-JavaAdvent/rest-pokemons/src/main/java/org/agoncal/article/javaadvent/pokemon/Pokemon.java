package org.agoncal.article.javaadvent.pokemon;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.Random;

/**
 * @author Antonio Goncalves @agoncal
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
public class Pokemon extends PanacheEntity {

    public String name;
    public String manufacturer;
    public int weight;

    public static Pokemon findARandomPokemon() {
        long count = Pokemon.count();
        int random = new Random().nextInt((int) count);
        return Pokemon.findAll().page(random, 1).firstResult();
    }
}
