package org.agoncal.article.qutepanache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
@Table(name = "t_books")
public class Book extends PanacheEntity {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Column(length = 100, nullable = false)
  @NotNull
  public String title;

  @Column(length = 20)
  public String isbn;

  @Column(nullable = false)
  @NotNull
  public BigDecimal price;

  @Column(name = "nb_of_pages")
  public Integer nbOfPages;

  @Column(name = "publication_date")
  @Past
  public LocalDate publicationDate;

  @Column(length = 3000)
  public String description;

  @Column(name = "created_date", nullable = false)
  @NotNull
  public Instant createdDate = Instant.now();

  @Override
  public String toString() {
    return "Book{" +
      "title='" + title + '\'' +
      ", isbn='" + isbn + '\'' +
      ", price=" + price +
      ", nbOfPages=" + nbOfPages +
      ", publicationDate=" + publicationDate +
      ", description='" + description + '\'' +
      ", createdDate=" + createdDate +
      ", id=" + id +
      '}';
  }
}
