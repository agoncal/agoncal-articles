package org.agoncal.article.javaadvent.cli;

import com.github.javafaker.Faker;
import picocli.CommandLine;

import java.text.MessageFormat;

@CommandLine.Command(name = "db")
public class DatabaseCommand implements Runnable {

    @CommandLine.Option(names = {"-l", "--nb-lines"}, description = "How many lines to generate", defaultValue = "100")
    int nbLines;

    @CommandLine.Option(names = {"-t", "--table"}, description = "Which table to generate (toy, child)", defaultValue = "child")
    String table;

    private static final String INSERT_TOY = "INSERT INTO Toy(id, name, manufacturer, weight) VALUES ({0,number,####}, \"{1}\", \"{2}\", \"{3}\");";
    private static final String INSERT_KID = "INSERT INTO Kid(id, name, address, chimney, country) VALUES ({0,number,####}, \"{1}\", \"{2}\", \"{3}\", \"{4}\");";
    public static final String ESC = "\"";
    public static final int START = 1000;
    private Faker faker = new Faker();

    @Override
    public void run() {

        if (table.toLowerCase().equals("toy")) {
            generateToyInsertStatements();
        } else {
            generateKidInsertStatements();
        }

    }

    private void generateKidInsertStatements() {
        for (int i = 0; i < nbLines; i++) {
            String kidName = faker.name().firstName() + " " + faker.name().lastName();
            String kidAddress = faker.address().streetAddress() + " " + faker.address().zipCode() + " " + faker.address().city();
            boolean chimney = faker.bool().bool();
            String kidCountry = faker.country().name();

            String statement = MessageFormat.format(INSERT_KID, START + i, kidName , kidAddress, chimney, kidCountry);
            System.out.println(statement);
        }
    }

    private void generateToyInsertStatements() {
        for (int i = 0; i < nbLines; i++) {
            String toyName = faker.pokemon().name();
            String toyManufacturer = faker.company().name();
            int toyWeight = faker.number().numberBetween(500, 10000);

            String statement = MessageFormat.format(INSERT_TOY, START + i, toyName, toyManufacturer, toyWeight);
            System.out.println(statement);
        }
    }
}
