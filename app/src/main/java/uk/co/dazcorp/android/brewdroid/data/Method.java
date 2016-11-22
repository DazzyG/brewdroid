
package uk.co.dazcorp.android.brewdroid.data;

import java.util.ArrayList;
import java.util.List;

public class Method {

    private Fermentation fermentation;
    private List<Mash_temp> mash_temp = new ArrayList<Mash_temp>();
    private String twist;

    /**
     * 
     * @return
     *     The fermentation
     */
    public Fermentation getFermentation() {
        return fermentation;
    }

    /**
     * 
     * @param fermentation
     *     The fermentation
     */
    public void setFermentation(Fermentation fermentation) {
        this.fermentation = fermentation;
    }

    /**
     * 
     * @return
     *     The mash_temp
     */
    public List<Mash_temp> getMash_temp() {
        return mash_temp;
    }

    /**
     * 
     * @param mash_temp
     *     The mash_temp
     */
    public void setMash_temp(List<Mash_temp> mash_temp) {
        this.mash_temp = mash_temp;
    }

    /**
     * 
     * @return
     *     The twist
     */
    public String getTwist() {
        return twist;
    }

    /**
     * 
     * @param twist
     *     The twist
     */
    public void setTwist(String twist) {
        this.twist = twist;
    }

}
