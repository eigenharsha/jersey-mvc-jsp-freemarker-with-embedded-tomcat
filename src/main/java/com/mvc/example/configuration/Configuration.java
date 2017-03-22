package com.mvc.example.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by eigenharsha on 3/22/2017.
 */
public class Configuration {

    @JsonProperty
    private Server server;

    @JsonProperty
    private String user;

    @JsonProperty("user")
    public String getUser() {
        return this.user;
    }

    @JsonProperty("user")
    public String setUser() {
        return this.user;
    }

    @JsonProperty("server")
    public Server getServer() {
        return server;
    }

    @JsonProperty("server")
    public void setServer(Server server) {
        this.server = server;
    }

}
