package com.example.gohorse.pokefight.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bruno on 28/05/2015.
 */

public class Move {

    private String learnType;
    private String name;
    private String resourceUri;
    private Integer level;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The learnType
     */
    public String getLearnType() {
        return learnType;
    }

    /**
     *
     * @param learnType
     * The learn_type
     */
    public void setLearnType(String learnType) {
        this.learnType = learnType;
    }

    public Move withLearnType(String learnType) {
        this.learnType = learnType;
        return this;
    }

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

    public Move withName(String name) {
        this.name = name;
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

    public Move withResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
        return this;
    }

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

    public Move withLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Move withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
