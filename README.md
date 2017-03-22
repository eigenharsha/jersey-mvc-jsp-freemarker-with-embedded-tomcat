jersey-mvc-jsp-freemarker-with-embedded-tomcat
=============================================
This is a simple application that aims to create a REST full web services using embedded tomcat and jersey, for configuration information use yml file. This application also uses jsp and freemarker template that is rendered through REST api call. It mainly focuses to register the object instance to `ResourceConfig` instead of registering class.

##### Application Used
* Tomcat v8.x
* Jersey v2.5.1
* JSP
* YML
* Freemarker
* HTML/Angular/JavaScript

Application creates two tomcat context path. one for REST full api end point is `/api` and another one for web page is `/webapp` that is directly load from tomcat webapp base directory location `/src/main/webapp`.
```
File base = new File("src/main/webapp");
Context context = tomcat.addContext("/api", base.getAbsolutePath());
tomcat.addWebapp(null, "/webapp", base.getAbsolutePath());
```

This application uses a yml file for application configuration. First read configuration.yml file and pass the configuration to application.

```
#configuration.yml
#setting server configuration
server:
  port: 9292

#configure user name
user: "eigenharsha"
```

### ResourceConfig (Jersey specific)
[ResourceConfig](https://jersey.java.net/apidocs/2.0/jersey/org/glassfish/jersey/server/ResourceConfig.html) is an extension of [JAX-RS Application](https://jersey.java.net/apidocs/2.0/jersey/javax/ws/rs/core/Application.html) class but it provides some registration methods to make registering resources and providers more friendly. This applciation registered resource through class instance using  `public ResourceConfig register(Object component)`.

JspMvcFeature and FreemarkerMvcFeature used for jsp and freemarker template, html/angular/javascript directly loaded in web browser.
```
final ResourceConfig config = new ResourceConfig()
    .packages(Main.class.getPackage().getName())
    .register(new Resource(new Core(), configuration)) // create instance of Resource and dynamically register
    .register(JspMvcFeature.class) // register jspMVC
    .register(FreemarkerMvcFeature.class) // register FreemarkerMVC
    .property(ServletProperties.FILTER_FORWARD_ON_404, true);

return new ServletContainer(config);
```

An in-depth tutorial for Programmatic API for Building Resources is available [here](https://jersey.java.net/documentation/latest/resource-builder.html).

This project requires maven.

### Get started

```
$ git clone https://github.com/eigenharsha/jersey-mvc-jsp-freemarker-with-embedded-tomcat.git
$ cd jersey-mvc-jsp-freemarker-with-embedded-tomcat
$ mvn clean install
```
Deploy the .war file to your web container/application server and launch/access it according to your container's configuration.
when this application be run then we will be able to access this REST services

| Resource | Endpint |
| ------ | ------ |
| Dynamicaly created resource invoked | [http://localhost:9292/api/resource] [PlDb] |
| Fetch user information from YML file | [http://localhost:9292/api/resource/user] [PlGh] |
| Freemarker template | [http://localhost:9292/api/resource/freemarker] [PlGd] |
| Jsp template | [http://localhost:9292/api/resource/jsp] [PlOd] |
| HTML page | [http://localhost:9292/webapp/index.html] [PlMe] |

Questions or suggestions? Please don't hesitate to email me at eigenharsha@gmail.com

