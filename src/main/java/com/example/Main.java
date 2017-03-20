package com.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;

import java.io.File;

public class Main {

    public static void main (String[] args) {
        final Core c;
        c = new Core();

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9000);

        String baseDoc = new File("src/main/webapp").getAbsolutePath();
        Context ctx = tomcat.addContext("/servletSample", baseDoc);

        tomcat.addWebapp(null, "", baseDoc);

        final ResourceConfig application = new ResourceConfig();
        application.packages(Main.class.getPackage().getName()).register(JspMvcFeature.class);
        application.property(ServletProperties.FILTER_FORWARD_ON_404, true);
        application.property(ServerProperties.TRACING, "ALL");

        //bind the core instance to resource
        //requirement : Register ResourceConfig through object Instance
        application.register(new Resource(c));

        ServletContainer jerseyServlet
                = new ServletContainer(application);

        tomcat.addServlet(ctx, "HelloWorld", jerseyServlet);
        ctx.addServletMapping("/*", "HelloWorld");

        try {

            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
