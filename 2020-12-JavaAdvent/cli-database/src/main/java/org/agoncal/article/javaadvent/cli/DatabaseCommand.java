package org.agoncal.article.javaadvent.cli;

import com.github.javafaker.Faker;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.jboss.logging.Logger;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum Table {toy, kid}

@QuarkusMain
@Command(name = "db", description = "Generates the database insert statements for the Java Advent databases")
public class DatabaseCommand implements Runnable, QuarkusApplication {

    private static final Logger LOGGER = Logger.getLogger(DatabaseCommand.class);

    @Inject
    CommandLine.IFactory factory;

    @Option(names = {"-t", "--table"}, description = "Which table to generate ${COMPLETION-CANDIDATES}", defaultValue = "toy")
    Table table;

    @Option(names = {"-l", "--nb-lines"}, description = "How many lines to generate", defaultValue = "100")
    int nbLines;

    @Option(names = {"-v", "--verbose"}, description = "Increases verbosity.")
    protected boolean verbose;

    private static final String KID_INSERT_STATEMENT = "INSERT INTO Kid(id, name, address, chimney, naughty, country) VALUES ({0,number,####}, ''{1}'', ''{2}'', {3}, {4}, ''{5}'');";
    private static final Path KID_IMPORT_FILE = Path.of("../rest-kids/src/main/resources/import_kids.sql");
    private static final String POKEMON_INSERT_STATEMENT = "INSERT INTO Pokemon(id, name, manufacturer, weight) VALUES ({0,number,####}, ''{1}'', ''{2}'', {3,number,###});";
    private static final Path POKEMON_IMPORT_FILE = Path.of("../rest-pokemons/src/main/resources/import_pokemons.sql");

    private static final List<String> COUNTRIES = List.of("Portugal", "Brazil", "Angola", "Mozambique", "Macau", "India", "Malaysia", "Indonesia", "Venezuela", "Argentina", "Uruguay");

    public static final int START = 1000;
    private Faker faker = new Faker();

    @Override
    public int run(String... args) {
        return new CommandLine(this, factory).execute(args);
    }

    @Override
    public void run() {
        if (verbose) LOGGER.info("db -t " + table + " -l " + nbLines + " -v " + verbose);
        if (verbose) LOGGER.info("db --table " + table + " --nb-lines " + nbLines + " --verbose " + verbose);

        switch (table) {
            case toy:
                generatePokemonInsertStatements();
                break;
            case kid:
                generateKidInsertStatements();
                break;
        }
    }

    private void generateKidInsertStatements() {
        List<String> statements = new ArrayList<>();

        for (int i = 0; i < nbLines; i++) {
            String kidName = escape(faker.name().firstName() + " " + faker.name().lastName());
            String kidAddress = escape(faker.address().streetAddress() + " " + faker.address().zipCode() + " " + faker.address().city());
            boolean chimney = faker.bool().bool();
            boolean naughty = faker.bool().bool();
            String kidCountry = randomCountry();

            String statement = MessageFormat.format(KID_INSERT_STATEMENT, START + i, kidName, kidAddress, chimney, naughty, kidCountry);
            statements.add(statement);
            if (verbose) LOGGER.info(statement);
        }

        saveStatements(statements, KID_IMPORT_FILE);
    }

    private void generatePokemonInsertStatements() {
        List<String> statements = new ArrayList<>();

        for (int i = 0; i < nbLines; i++) {
            String toyName = escape(faker.pokemon().name());
            String toyManufacturer = escape(faker.company().name());
            int toyWeight = faker.number().numberBetween(5, 1000);

            String statement = MessageFormat.format(POKEMON_INSERT_STATEMENT, START + i, toyName, toyManufacturer, toyWeight);
            statements.add(statement);
            if (verbose) LOGGER.info(statement);
        }

        saveStatements(statements, POKEMON_IMPORT_FILE);
    }

    private void saveStatements(List<String> statements, Path fileName) {
        try {
            Files.write(fileName, statements);
            if (verbose) LOGGER.info("Wrote file " + fileName.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String escape(String s) {
        return s.replace("'", "''");
    }

    private String randomCountry() {
        return COUNTRIES.get(new Random().nextInt(COUNTRIES.size()));
    }
}
