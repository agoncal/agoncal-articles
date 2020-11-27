package org.agoncal.article.javaadvent.toy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/toys")
@Produces(MediaType.APPLICATION_JSON)
public class ToyResource {

    @GET
    @Path("/random")
    public Toy getARandomToy() {
        return Toy.findARandomToy();
    }

    @GET
    public List<Toy> getAllToys() {
        return Toy.findAll().list();
    }
}
