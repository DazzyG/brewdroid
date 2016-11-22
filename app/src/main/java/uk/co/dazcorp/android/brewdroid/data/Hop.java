
package uk.co.dazcorp.android.brewdroid.data;

public class Hop {

    private String add;
    private Amount amount;
    private String attribute;
    private String name;

    /**
     * 
     * @return
     *     The add
     */
    public String getAdd() {
        return add;
    }

    /**
     * 
     * @param add
     *     The add
     */
    public void setAdd(String add) {
        this.add = add;
    }

    /**
     * 
     * @return
     *     The amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The amount
     */
    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    /**
     * 
     * @return
     *     The attribute
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * 
     * @param attribute
     *     The attribute
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
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

}
