package com.mvc.example;

import com.mvc.example.configuration.Configuration;
import com.mvc.example.core.Core;
import com.mvc.example.util.YMLConfiguration;
import org.apache.catalina.Context;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;

import javax.servlet.Filter;
import java.io.File;

import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

/**
 * Created by eigenharsha on 3/22/2017.
 */

/**
 * endpoint : default configuration
 *
 * http://localhost:9292/api/resource/jsp
 * http://localhost:9292/api/resource/user
 * http://localhost:9292/api/resource
 * http://localhost:9292/api/resource/freemarker
 * http://localhost:9292/webapp/index.html
 */


public class Main {

    private static Configuration configuration;

    public static void main(String... args) throws Exception {

        //get configuration
        configuration = new YMLConfiguration().getYMLConfigration(System.getProperty("user.dir") + "/configuration.yml");
        System.out.println("reading configuration : " + System.getProperty("user.dir") + "/configuration.yml");

        Tomcat tomcat = new Tomcat();

        //set tomcat port form yml configuration file
        tomcat.setPort(configuration.getServer().getrPort());
        System.out.println("Tomcat running on port : " + configuration.getServer().getrPort());

        File base = new File("src/main/webapp");
        Context context = tomcat.addContext("/api", base.getAbsolutePath());
        tomcat.addWebapp(null, "/webapp", base.getAbsolutePath());

        Tomcat.addServlet(context, "default", new DefaultServlet());
        context.addServletMapping("/*", "default");

        final FilterDef def = new FilterDef();
        final FilterMap map = new FilterMap();

        def.setFilterName("jerseyFilter");
        def.setFilter(getJerseyFilter());
        context.addFilterDef(def);

        map.setFilterName("jerseyFilter");
        map.addURLPattern("/*");
        context.addFilterMap(map);

        tomcat.start();

        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * register object in jersey com.mvc.example.resource and configure
     * jsp and freemarker mvc
     * @return
     */
    private static Filter getJerseyFilter(){

        final ResourceConfig config = new ResourceConfig()
                .packages(Main.class.getPackage().getName())

                // create instance of Resource and dynamically configure to ResourceConfig
                .register(new Resource(new Core(), configuration))


                .register(JspMvcFeature.class) // register jspMVC

                // register FreemarkerMVC
                .register(FreemarkerMvcFeature.class)
                .property(ServletProperties.FILTER_FORWARD_ON_404, true);

        return new ServletContainer(config);
    }
}
