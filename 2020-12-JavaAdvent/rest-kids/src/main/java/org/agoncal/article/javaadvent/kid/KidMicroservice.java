package org.agoncal.article.javaadvent.kid;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title = "API that returns the name and address of nice kids who deserve a present", version = "1.0"))
public class KidMicroservice extends Application {
}
