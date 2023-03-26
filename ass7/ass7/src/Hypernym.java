/******************************
 * Name: Nadav Elgrabli.
 * ID: 316082791
 *****************************/

/**
 * @author Nadav Elgrabli
 * Class creates Hypernym defintion.
 */
public class Hypernym implements Comparable<Hypernym> {
    private String hypernym;
    private String hyponym;

    /**
     * Cosntructor.
     * @param hypernym hypernym.
     * @param hyponym hyponym.
     */
    public Hypernym(String hypernym, String hyponym) {
        this.hypernym = hypernym;
        this.hyponym = hyponym;
    }

    /**
     * getHypernym method.
     * @return String.
     */
    public String getHypernym() {
        return hypernym;
    }

    /**
     * getHyponym method.
     * @return String.
     */
    public String getHyponym() {
        return hyponym;
    }

    /**
     * method to comapare Hypernym.
     * @param obj Object
     * @return true / false.
     */
    @Override
    public boolean equals(Object obj) {
        Hypernym other = (Hypernym) obj;
        return hypernym.equals(other.hypernym) && hyponym.equals(other.hyponym);
    }

    /**
     * method to compare values of hypernym.
     * @param o Hypernym
     * @return value of int.
     */
    @Override
    public int compareTo(Hypernym o) {
        int compare1 = hypernym.compareTo(o.hypernym);
        if (compare1 == 0) {
            return hyponym.compareTo(o.hyponym);
        }
        return compare1;
    }
}