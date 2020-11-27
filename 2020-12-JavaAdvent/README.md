# agoncal-article-java-advent-2020
Code for the article written for Java Advent 2020

## Generating the Article

```
docs$ mvn clean generate-resources
```

## Generating Code

For REST Endpoints:

```
mvn io.quarkus:quarkus-maven-plugin:1.9.2.Final:create \
    -DplatformVersion=1.9.2.Final \
    -DprojectGroupId=org.agoncal.article.javaadvent \
    -DprojectArtifactId=rest-kids \
    -DprojectVersion=1.0-SNAPSHOT \
    -DclassName="org.agoncal.article.javaadvent.kid.KidResource" \
    -Dpath="/api/kids" \
    -Dextensions="resteasy, resteasy-jsonb,  smallrye-openapi"
```

For CLI Commands:

```
mvn io.quarkus:quarkus-maven-plugin:1.9.2.Final:create \
    -DplatformVersion=1.9.2.Final \
    -DprojectGroupId=org.agoncal.article.javaadvent \
    -DprojectArtifactId=cli-database \
    -DprojectVersion=1.0-SNAPSHOT \
    -DpackageName="org.agoncal.article.javaadvent.cli" \
    -Dextensions="picocli"
```
