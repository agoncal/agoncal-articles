package org.agoncal.article.qutepanache;

import io.quarkus.panache.common.Sort;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/page/books")
@Produces(MediaType.TEXT_HTML)
@ApplicationScoped
public class BookPage {

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance book(Book book);

    public static native TemplateInstance books(List<Book> books);
  }

  @GET
  @Path("/{id}")
  public TemplateInstance showBookById(@PathParam("id") Long id) {
    return Templates.book(Book.findById(id));
  }

  @GET
  public TemplateInstance showAllBooks(@QueryParam("query") String query, @QueryParam("sort") @DefaultValue("id") String sort, @QueryParam("page") @DefaultValue("0") Integer pageIndex, @QueryParam("size") @DefaultValue("1000") Integer pageSize) {
    return Templates.books(Book.find(query, Sort.by(sort)).page(pageIndex, pageSize).list())
      .data("query", query)
      .data("sort", sort)
      .data("pageIndex", pageIndex)
      .data("pageSize", pageSize);
  }
}
