package org.agoncal.article.javaadvent.pokemon;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title = "API that returns Pokemons", version = "1.0"))
public class PokemonMicroservice extends Application {
}
