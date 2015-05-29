package com.example.gohorse.pokefight.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bruno on 28/05/2015.
 */

public class Game {

    private String name;
    private String resourceUri;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The resourceUri
     */
    public String getResourceUri() {
        return resourceUri;
    }

    /**
     *
     * @param resourceUri
     * The resource_uri
     */
    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
