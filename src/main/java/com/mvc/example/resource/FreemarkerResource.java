package com.mvc.example.resource;

/**
 * Created by eigenharsha on 3/22/2017.
 */

import org.glassfish.jersey.server.mvc.Viewable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreemarkerResource {

    final String templatePath;
    public FreemarkerResource(String templatePath) {
        this.templatePath = templatePath;
    }

    public Viewable getTemplate(String user) {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", user);
        final List<String> list = new ArrayList<String>();
        list.add("item1");
        list.add("item2");
        list.add("item3");
        map.put("items", list);

        return new Viewable(templatePath, map);
    }
}
