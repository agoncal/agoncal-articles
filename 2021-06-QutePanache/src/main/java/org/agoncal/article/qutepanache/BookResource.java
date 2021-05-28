package org.agoncal.article.qutepanache;

import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class BookResource {

  @GET
  @Path("/{id}")
  public Book showBookById(@PathParam("id") Long id) {
    return Book.findById(id);
  }

  @GET
  public List<Book> showAllBooks(@QueryParam("query") String query, @QueryParam("sort") @DefaultValue("id") String sort, @QueryParam("page") @DefaultValue("0") Integer pageIndex, @QueryParam("size") @DefaultValue("1000") Integer pageSize) {
    return Book.find(query, Sort.by(sort)).page(pageIndex, pageSize).list();
  }
}
