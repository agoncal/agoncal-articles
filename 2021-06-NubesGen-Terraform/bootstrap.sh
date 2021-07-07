#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.article \
        -DprojectArtifactId=quarkus-nubesgen-terraform \
        -DpackageName="org.agoncal.article.nubesgen.terraform" \
        -Dextensions="quarkus-resteasy-jsonb"
