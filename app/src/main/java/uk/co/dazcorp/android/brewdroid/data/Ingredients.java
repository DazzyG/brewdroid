
package uk.co.dazcorp.android.brewdroid.data;

import java.util.ArrayList;
import java.util.List;

public class Ingredients {

    private List<Hop> hops = new ArrayList<Hop>();
    private List<Malt> malt = new ArrayList<Malt>();
    private String yeast;

    /**
     * 
     * @return
     *     The hops
     */
    public List<Hop> getHops() {
        return hops;
    }

    /**
     * 
     * @param hops
     *     The hops
     */
    public void setHops(List<Hop> hops) {
        this.hops = hops;
    }

    /**
     * 
     * @return
     *     The malt
     */
    public List<Malt> getMalt() {
        return malt;
    }

    /**
     * 
     * @param malt
     *     The malt
     */
    public void setMalt(List<Malt> malt) {
        this.malt = malt;
    }

    /**
     * 
     * @return
     *     The yeast
     */
    public String getYeast() {
        return yeast;
    }

    /**
     * 
     * @param yeast
     *     The yeast
     */
    public void setYeast(String yeast) {
        this.yeast = yeast;
    }

}
