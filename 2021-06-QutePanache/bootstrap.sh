#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.article \
        -DprojectArtifactId=qute-panache \
        -DclassName="org.agoncal.article.qutepanache.BookPage" \
        -Dextensions="jdbc-postgresql, hibernate-orm-panache, resteasy-qute"
