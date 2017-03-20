package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("user")
public class Resource {

    final Core core;

    public Resource(Core c){
        core = c;
    }

    @GET
    public String getResource() {
        return core.getData();
    }
}
