package com.mvc.example.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Server {

    @JsonProperty
    private int port;

    @JsonProperty("port")
    public int setPort() {
        return this.port;
    }

    @JsonProperty("port")
    public int getrPort() {
        return this.port;
    }
}
