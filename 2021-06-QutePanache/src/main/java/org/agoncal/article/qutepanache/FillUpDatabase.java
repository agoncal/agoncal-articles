package org.agoncal.article.qutepanache;

import com.github.javafaker.Faker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;

public class FillUpDatabase {

  private static Faker faker = new Faker();
  private static FileWriter fileWriter;
  private static PrintWriter printWriter;

  public static final int NB_BOOKS = 100;

  public static void main(String[] args) throws IOException {
    fileWriter = new FileWriter("./src/main/resources/import.sql");
    printWriter = new PrintWriter(fileWriter);

    fillUpBooks();

    printWriter.printf("\nALTER SEQUENCE hibernate_sequence RESTART WITH %d;", NB_BOOKS);
    printWriter.close();
    fileWriter.close();
  }

  private static void fillUpBooks() {
    printWriter.println("-- BOOKS");
    printWriter.println("INSERT INTO t_books (id, title, isbn, price, nb_of_pages, publication_date, description, created_date) VALUES");
    String insert = "\t(%d, '%s', '%s', %d, %d, '%s', '%s', '%s')%s\n";
    for (int i = 1; i < NB_BOOKS; i++) {
      printWriter.printf(insert, i, format(faker.book().title()), faker.code().isbn13(true), faker.number().numberBetween(1, 100), faker.number().numberBetween(10, 800), LocalDate.now().minusDays(faker.number().numberBetween(100, 10_000)), faker.lorem().paragraph(), Instant.now(), ",");
    }
    printWriter.printf(insert, NB_BOOKS, format(faker.book().title()), faker.code().isbn13(true), faker.number().numberBetween(1, 100), faker.number().numberBetween(10, 800), LocalDate.now().minusDays(faker.number().numberBetween(100, 10_000)), faker.lorem().paragraph(), Instant.now(), ";");
  }

  private static String format(String value) {
    value = value.trim();
    value = value.replaceAll("'", "");
    value = value.replaceAll("\"", "");
    return value;
  }
}
