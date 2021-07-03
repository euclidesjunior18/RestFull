package org.example;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Swagger;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/docs")
@Singleton
public class SwaggerResource {
    private final Swagger swagger;

    public SwaggerResource() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("0.0.1");
        beanConfig.setSchemes(new String[] { "http" });
        beanConfig.setBasePath("");
        beanConfig.setResourcePackage("org.example");
        beanConfig.setScan(true);
        this.swagger = beanConfig.getSwagger();
    }

    @GET
    @Produces({"application/json"})
    @Path("/swagger.json")
    public Response getListingJson() {
        return Response.ok(this.swagger).build();
    }
}
