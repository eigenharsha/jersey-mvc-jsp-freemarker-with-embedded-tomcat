package com.mvc.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.mvc.example.configuration.Configuration;

import java.io.File;

/**
 * Created by eigenharsha on 3/22/2017.
 */
public class YMLConfiguration {

    private Configuration configuration;

    public Configuration getYMLConfigration(String path) {

        configuration = new Configuration();
        //thread safe singleton class

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            configuration = mapper.readValue(new File(path), Configuration.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration;
    }
}
