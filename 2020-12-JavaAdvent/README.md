# agoncal-article-java-advent-2020
Code for the article written for Java Advent 2020

## Running the Sample

The idea is to start the 3 microservices, use them, and then kill both _Kids_ and _Toys_ and still use the _Santa_ microservice (thanks to MicroProfile Fault-Tolerance).

### Starting the app 

You need to start the infrastructure first (3 Postgres databases) and then the 3 Quarkus instances:

```
infrastructure$ docker-compose -f docker-compose-databases.yaml up -d
rest-kids$ mvn quarkus:dev
rest-pokemons$ mvn quarkus:dev
rest-santa$ mvn quarkus:dev
```

### Invoking the microservices through cURL commands

Getting kids per country:

```
curl "http://localhost:8702/api/kids?country=Angola"
curl "http://localhost:8702/api/kids?country=Venezuela"
```

Getting a random Pokemon:

```
curl http://localhost:8703/api/pokemons/random
```

Getting Santa schedule per country and year:

```
curl "http://localhost:8701/api/santa?country=Angola&year=2019"
curl "http://localhost:8701/api/santa?country=Venezuela"
```

Creating a new Santa schedule for 2020:

``` 
curl -X POST -H "Content-Type: text/plain" -d "Portugal" http://localhost:8701/api/santa
```

### Checking the OpenAPI contracts

``` 
curl http://localhost:8701/openapi
curl http://localhost:8702/openapi
curl http://localhost:8703/openapi
```

## Generating the Article

```
docs$ mvn clean generate-resources
```

## Generating the database scripts

```
cli-database$ java -jar target/cli-database-1.0-SNAPSHOT-runner.jar -t toy
cli-database$ java -jar target/cli-database-1.0-SNAPSHOT-runner.jar -t toy -l 100 -v
cli-database$ java -jar target/cli-database-1.0-SNAPSHOT-runner.jar -t kid
cli-database$ java -jar target/cli-database-1.0-SNAPSHOT-runner.jar -t santa
```


## Generating Code

For REST Endpoints:

```
mvn io.quarkus:quarkus-maven-plugin:1.10.2.Final:create \
    -DplatformVersion=1.10.2.Final \
    -DprojectGroupId=org.agoncal.article.javaadvent \
    -DprojectArtifactId=rest-kids \
    -DprojectVersion=1.0-SNAPSHOT \
    -DclassName="org.agoncal.article.javaadvent.kid.KidResource" \
    -Dpath="/api/kids" \
    -Dextensions="resteasy, resteasy-jsonb,  smallrye-openapi"
```

For CLI Commands:

```
mvn io.quarkus:quarkus-maven-plugin:1.10.2.Final:create \
    -DplatformVersion=1.10.2.Final \
    -DprojectGroupId=org.agoncal.article.javaadvent \
    -DprojectArtifactId=cli-database \
    -DprojectVersion=1.0-SNAPSHOT \
    -DpackageName="org.agoncal.article.javaadvent.cli" \
    -Dextensions="picocli"
```
