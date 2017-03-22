package com.mvc.example;

import com.mvc.example.configuration.Configuration;
import com.mvc.example.core.Core;
import com.mvc.example.resource.FreemarkerResource;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by eigenharsha on 3/22/2017.
 */

@Path("resource")
public class Resource {

    final Core core;
    final Configuration config;

    /**
     * prametarised constructor
     * @param core
     */
    public Resource(Core core, Configuration config ) {
        this.core = core;
        this.config = config;
    }

    @GET
    public String get() {
        return  this.core.getCoreInfo();
    }

    @GET
    @Path("/user")
    public String getUser() {
        return config.getUser();
    }

    @GET
    @Path("/freemarker")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getFreemarkerMvc () {

        //create FreemarkerResource Instance
        final FreemarkerResource freemarkerResource = new FreemarkerResource("/hello-freemarker.ftl");
        return freemarkerResource.getTemplate();
    }

    /**
     * render jsp page
     * @return
     */
    @GET
    @Path("/jsp")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getJspMvc() {
        return new Viewable("/hello-jsp");
    }
}
