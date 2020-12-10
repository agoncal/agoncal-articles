package org.agoncal.article.javaadvent.santa;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title = "API to manage Santa's schedule", version = "1.0"))
public class SantaMicroservice extends Application {
}
