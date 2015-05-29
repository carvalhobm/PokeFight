package com.example.gohorse.pokefight.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bruno on 28/05/2015.
 */

public class DescriptionFinal {

    private String created;
    private String description;
    private List<Game> games = new ArrayList<Game>();
    private Integer id;
    private String modified;
    private String name;
    private Pokemon pokemon;
    private String resourceUri;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The created
     */
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     * The created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The games
     */
    public List<Game> getGames() {
        return games;
    }

    /**
     *
     * @param games
     * The games
     */
    public void setGames(List<Game> games) {
        this.games = games;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     *
     * @param modified
     * The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
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

    /**
     *
     * @return
     * The pokemon
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     *
     * @param pokemon
     * The pokemon
     */
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
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
