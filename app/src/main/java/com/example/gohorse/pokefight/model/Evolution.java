package com.example.gohorse.pokefight.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bruno on 28/05/2015.
 */

public class Evolution {

    private Integer level;
    private String method;
    private String resourceUri;
    private String to;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     *
     * @param level
     * The level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    public Evolution withLevel(Integer level) {
        this.level = level;
        return this;
    }

    /**
     *
     * @return
     * The method
     */
    public String getMethod() {
        return method;
    }

    /**
     *
     * @param method
     * The method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    public Evolution withMethod(String method) {
        this.method = method;
        return this;
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

    public Evolution withResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
        return this;
    }

    /**
     *
     * @return
     * The to
     */
    public String getTo() {
        return to;
    }

    /**
     *
     * @param to
     * The to
     */
    public void setTo(String to) {
        this.to = to;
    }

    public Evolution withTo(String to) {
        this.to = to;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Evolution withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
