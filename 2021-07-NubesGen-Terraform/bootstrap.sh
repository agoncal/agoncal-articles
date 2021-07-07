#!/usr/bin/env bash
# tag::adocSnippet[]
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.article \
        -DprojectArtifactId=quarkus-nubesgen-terraform \
        -DpackageName="org.agoncal.article.nubesgen.terraform" \
        -Dextensions="quarkus-resteasy-jsonb"
# end::adocSnippet[]
