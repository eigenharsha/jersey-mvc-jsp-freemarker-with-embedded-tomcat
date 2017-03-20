package com.example;

import org.glassfish.jersey.server.mvc.Viewable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("hello-mvc")
public class MyResource {

    @GET
    public Viewable helloworld() {
        return new Viewable("/hello-mvc");
    }

    @GET
    @Path("greet")
    public String gteet() {
        return "hello";
    }
}
