
package uk.co.dazcorp.android.brewdroid.data;

import java.util.ArrayList;
import java.util.List;

public class Beer {

    private Double abv;
    private Double attenuation_level;
    private Boil_volume boil_volume;
    private String brewers_tips;
    private String contributed_by;
    private String description;
    private Integer ebc;
    private String first_brewed;
    private List<String> food_pairing = new ArrayList<String>();
    private Integer ibu;
    private Ingredients ingredients;
    private Method method;
    private String name;
    private Double ph;
    private Integer srm;
    private String tagline;
    private Integer target_fg;
    private Integer target_og;
    private Volume volume;

    /**
     * 
     * @return
     *     The abv
     */
    public Double getAbv() {
        return abv;
    }

    /**
     * 
     * @param abv
     *     The abv
     */
    public void setAbv(Double abv) {
        this.abv = abv;
    }

    /**
     * 
     * @return
     *     The attenuation_level
     */
    public Double getAttenuation_level() {
        return attenuation_level;
    }

    /**
     * 
     * @param attenuation_level
     *     The attenuation_level
     */
    public void setAttenuation_level(Double attenuation_level) {
        this.attenuation_level = attenuation_level;
    }

    /**
     * 
     * @return
     *     The boil_volume
     */
    public Boil_volume getBoil_volume() {
        return boil_volume;
    }

    /**
     * 
     * @param boil_volume
     *     The boil_volume
     */
    public void setBoil_volume(Boil_volume boil_volume) {
        this.boil_volume = boil_volume;
    }

    /**
     * 
     * @return
     *     The brewers_tips
     */
    public String getBrewers_tips() {
        return brewers_tips;
    }

    /**
     * 
     * @param brewers_tips
     *     The brewers_tips
     */
    public void setBrewers_tips(String brewers_tips) {
        this.brewers_tips = brewers_tips;
    }

    /**
     * 
     * @return
     *     The contributed_by
     */
    public String getContributed_by() {
        return contributed_by;
    }

    /**
     * 
     * @param contributed_by
     *     The contributed_by
     */
    public void setContributed_by(String contributed_by) {
        this.contributed_by = contributed_by;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The ebc
     */
    public Integer getEbc() {
        return ebc;
    }

    /**
     * 
     * @param ebc
     *     The ebc
     */
    public void setEbc(Integer ebc) {
        this.ebc = ebc;
    }

    /**
     * 
     * @return
     *     The first_brewed
     */
    public String getFirst_brewed() {
        return first_brewed;
    }

    /**
     * 
     * @param first_brewed
     *     The first_brewed
     */
    public void setFirst_brewed(String first_brewed) {
        this.first_brewed = first_brewed;
    }

    /**
     * 
     * @return
     *     The food_pairing
     */
    public List<String> getFood_pairing() {
        return food_pairing;
    }

    /**
     * 
     * @param food_pairing
     *     The food_pairing
     */
    public void setFood_pairing(List<String> food_pairing) {
        this.food_pairing = food_pairing;
    }

    /**
     * 
     * @return
     *     The ibu
     */
    public Integer getIbu() {
        return ibu;
    }

    /**
     * 
     * @param ibu
     *     The ibu
     */
    public void setIbu(Integer ibu) {
        this.ibu = ibu;
    }

    /**
     * 
     * @return
     *     The ingredients
     */
    public Ingredients getIngredients() {
        return ingredients;
    }

    /**
     * 
     * @param ingredients
     *     The ingredients
     */
    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * 
     * @return
     *     The method
     */
    public Method getMethod() {
        return method;
    }

    /**
     * 
     * @param method
     *     The method
     */
    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The ph
     */
    public Double getPh() {
        return ph;
    }

    /**
     * 
     * @param ph
     *     The ph
     */
    public void setPh(Double ph) {
        this.ph = ph;
    }

    /**
     * 
     * @return
     *     The srm
     */
    public Integer getSrm() {
        return srm;
    }

    /**
     * 
     * @param srm
     *     The srm
     */
    public void setSrm(Integer srm) {
        this.srm = srm;
    }

    /**
     * 
     * @return
     *     The tagline
     */
    public String getTagline() {
        return tagline;
    }

    /**
     * 
     * @param tagline
     *     The tagline
     */
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    /**
     * 
     * @return
     *     The target_fg
     */
    public Integer getTarget_fg() {
        return target_fg;
    }

    /**
     * 
     * @param target_fg
     *     The target_fg
     */
    public void setTarget_fg(Integer target_fg) {
        this.target_fg = target_fg;
    }

    /**
     * 
     * @return
     *     The target_og
     */
    public Integer getTarget_og() {
        return target_og;
    }

    /**
     * 
     * @param target_og
     *     The target_og
     */
    public void setTarget_og(Integer target_og) {
        this.target_og = target_og;
    }

    /**
     * 
     * @return
     *     The volume
     */
    public Volume getVolume() {
        return volume;
    }

    /**
     * 
     * @param volume
     *     The volume
     */
    public void setVolume(Volume volume) {
        this.volume = volume;
    }

}
