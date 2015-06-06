package com.example.gohorse.pokefight.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bruno on 28/05/2015.
 */

public class Pokemon {

    private List<Ability> abilities = new ArrayList<Ability>();
    private Integer attack;
    private Integer catchRate;
    private String created;
    private Integer defense;
    private List<Description> descriptions = new ArrayList<Description>();
    private Integer eggCycles;
    private List<EggGroup> eggGroups = new ArrayList<EggGroup>();
    private String evYield;
    private List<Evolution> evolutions = new ArrayList<Evolution>();
    private Integer exp;
    private String growthRate;
    private Integer happiness;
    private String height;
    private Integer hp;
    private String maleFemaleRatio;
    private String modified;
    private List<Move> moves = new ArrayList<Move>();
    private String name;
    private Integer national_id;
    private Integer pkdxId;
    private String resourceUri;
    private Integer sp_atk;
    private Integer sp_def;
    private String species;
    private Integer speed;
    private List<Sprite> sprites = new ArrayList<Sprite>();
    private Integer total;
    private List<Type> types = new ArrayList<Type>();
    private String weight;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The abilities
     */
    public List<Ability> getAbilities() {
        return abilities;
    }

    /**
     *
     * @param abilities
     * The abilities
     */
    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public Pokemon withAbilities(List<Ability> abilities) {
        this.abilities = abilities;
        return this;
    }

    /**
     *
     * @return
     * The attack
     */
    public Integer getAttack() {
        return attack;
    }

    /**
     *
     * @param attack
     * The attack
     */
    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Pokemon withAttack(Integer attack) {
        this.attack = attack;
        return this;
    }

    /**
     *
     * @return
     * The catchRate
     */
    public Integer getCatchRate() {
        return catchRate;
    }

    /**
     *
     * @param catchRate
     * The catch_rate
     */
    public void setCatchRate(Integer catchRate) {
        this.catchRate = catchRate;
    }

    public Pokemon withCatchRate(Integer catchRate) {
        this.catchRate = catchRate;
        return this;
    }

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

    public Pokemon withCreated(String created) {
        this.created = created;
        return this;
    }

    /**
     *
     * @return
     * The defense
     */
    public Integer getDefense() {
        return defense;
    }

    /**
     *
     * @param defense
     * The defense
     */
    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Pokemon withDefense(Integer defense) {
        this.defense = defense;
        return this;
    }

    /**
     *
     * @return
     * The descriptions
     */
    public List<Description> getDescriptions() {
        return descriptions;
    }

    /**
     *
     * @param descriptions
     * The descriptions
     */
    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public Pokemon withDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    /**
     *
     * @return
     * The eggCycles
     */
    public Integer getEggCycles() {
        return eggCycles;
    }

    /**
     *
     * @param eggCycles
     * The egg_cycles
     */
    public void setEggCycles(Integer eggCycles) {
        this.eggCycles = eggCycles;
    }

    public Pokemon withEggCycles(Integer eggCycles) {
        this.eggCycles = eggCycles;
        return this;
    }

    /**
     *
     * @return
     * The eggGroups
     */
    public List<EggGroup> getEggGroups() {
        return eggGroups;
    }

    /**
     *
     * @param eggGroups
     * The egg_groups
     */
    public void setEggGroups(List<EggGroup> eggGroups) {
        this.eggGroups = eggGroups;
    }

    public Pokemon withEggGroups(List<EggGroup> eggGroups) {
        this.eggGroups = eggGroups;
        return this;
    }

    /**
     *
     * @return
     * The evYield
     */
    public String getEvYield() {
        return evYield;
    }

    /**
     *
     * @param evYield
     * The ev_yield
     */
    public void setEvYield(String evYield) {
        this.evYield = evYield;
    }

    public Pokemon withEvYield(String evYield) {
        this.evYield = evYield;
        return this;
    }

    /**
     *
     * @return
     * The evolutions
     */
    public List<Evolution> getEvolutions() {
        return evolutions;
    }

    /**
     *
     * @param evolutions
     * The evolutions
     */
    public void setEvolutions(List<Evolution> evolutions) {
        this.evolutions = evolutions;
    }

    public Pokemon withEvolutions(List<Evolution> evolutions) {
        this.evolutions = evolutions;
        return this;
    }

    /**
     *
     * @return
     * The exp
     */
    public Integer getExp() {
        return exp;
    }

    /**
     *
     * @param exp
     * The exp
     */
    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Pokemon withExp(Integer exp) {
        this.exp = exp;
        return this;
    }

    /**
     *
     * @return
     * The growthRate
     */
    public String getGrowthRate() {
        return growthRate;
    }

    /**
     *
     * @param growthRate
     * The growth_rate
     */
    public void setGrowthRate(String growthRate) {
        this.growthRate = growthRate;
    }

    public Pokemon withGrowthRate(String growthRate) {
        this.growthRate = growthRate;
        return this;
    }

    /**
     *
     * @return
     * The happiness
     */
    public Integer getHappiness() {
        return happiness;
    }

    /**
     *
     * @param happiness
     * The happiness
     */
    public void setHappiness(Integer happiness) {
        this.happiness = happiness;
    }

    public Pokemon withHappiness(Integer happiness) {
        this.happiness = happiness;
        return this;
    }

    /**
     *
     * @return
     * The height
     */
    public String getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * The height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    public Pokemon withHeight(String height) {
        this.height = height;
        return this;
    }

    /**
     *
     * @return
     * The hp
     */
    public Integer getHp() {
        return hp;
    }

    /**
     *
     * @param hp
     * The hp
     */
    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Pokemon withHp(Integer hp) {
        this.hp = hp;
        return this;
    }

    /**
     *
     * @return
     * The maleFemaleRatio
     */
    public String getMaleFemaleRatio() {
        return maleFemaleRatio;
    }

    /**
     *
     * @param maleFemaleRatio
     * The male_female_ratio
     */
    public void setMaleFemaleRatio(String maleFemaleRatio) {
        this.maleFemaleRatio = maleFemaleRatio;
    }

    public Pokemon withMaleFemaleRatio(String maleFemaleRatio) {
        this.maleFemaleRatio = maleFemaleRatio;
        return this;
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

    public Pokemon withModified(String modified) {
        this.modified = modified;
        return this;
    }

    /**
     *
     * @return
     * The moves
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     *
     * @param moves
     * The moves
     */
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Pokemon withMoves(List<Move> moves) {
        this.moves = moves;
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

    public Pokemon withName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @return
     * The nationalId
     */
    public Integer getNationalId() {
        return national_id;
    }

    /**
     *
     * @param nationalId
     * The national_id
     */
    public void setNationalId(Integer nationalId) {
        this.national_id = nationalId;
    }

    public Pokemon withNationalId(Integer nationalId) {
        this.national_id = nationalId;
        return this;
    }

    /**
     *
     * @return
     * The pkdxId
     */
    public Integer getPkdxId() {
        return pkdxId;
    }

    /**
     *
     * @param pkdxId
     * The pkdx_id
     */
    public void setPkdxId(Integer pkdxId) {
        this.pkdxId = pkdxId;
    }

    public Pokemon withPkdxId(Integer pkdxId) {
        this.pkdxId = pkdxId;
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

    public Pokemon withResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
        return this;
    }

    /**
     *
     * @return
     * The spAtk
     */
    public Integer getSpAtk() {
        return sp_atk;
    }

    /**
     *
     * @param spAtk
     * The sp_atk
     */
    public void setSpAtk(Integer spAtk) {
        this.sp_atk = spAtk;
    }

    public Pokemon withSpAtk(Integer spAtk) {
        this.sp_atk = spAtk;
        return this;
    }

    /**
     *
     * @return
     * The sp_def
     */
    public Integer getSp_def() {
        return sp_def;
    }

    /**
     *
     * @param sp_def
     * The sp_def
     */
    public void setSp_def(Integer sp_def) {
        this.sp_def = sp_def;
    }

    public Pokemon withSpDef(Integer spDef) {
        this.sp_def = spDef;
        return this;
    }

    /**
     *
     * @return
     * The species
     */
    public String getSpecies() {
        return species;
    }

    /**
     *
     * @param species
     * The species
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    public Pokemon withSpecies(String species) {
        this.species = species;
        return this;
    }

    /**
     *
     * @return
     * The speed
     */
    public Integer getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     * The speed
     */
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Pokemon withSpeed(Integer speed) {
        this.speed = speed;
        return this;
    }

    /**
     *
     * @return
     * The sprites
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     *
     * @param sprites
     * The sprites
     */
    public void setSprites(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    public Pokemon withSprites(List<Sprite> sprites) {
        this.sprites = sprites;
        return this;
    }

    /**
     *
     * @return
     * The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    public Pokemon withTotal(Integer total) {
        this.total = total;
        return this;
    }

    /**
     *
     * @return
     * The types
     */
    public List<Type> getTypes() {
        return types;
    }

    /**
     *
     * @param types
     * The types
     */
    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public Pokemon withTypes(List<Type> types) {
        this.types = types;
        return this;
    }

    /**
     *
     * @return
     * The weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     * The weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Pokemon withWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Pokemon withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
