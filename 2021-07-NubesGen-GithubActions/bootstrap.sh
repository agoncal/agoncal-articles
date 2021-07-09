#!/usr/bin/env bash
# tag::adocSnippet[]
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.article \
        -DprojectArtifactId=quarkus-nubesgen-github-actions \
        -DpackageName="org.agoncal.article.nubesgen.githubactions" \
        -Dextensions="quarkus-resteasy"
# end::adocSnippet[]
