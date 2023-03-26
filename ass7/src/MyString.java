/******************************
 * Name: Nadav Elgrabli.
 * ID: 316082791
 *****************************/

/**
 * @author Nadav Elgrabli.
 * Class make sure the hypernyms are case insensitive.
 */
public class MyString implements Comparable<MyString> {
    private final String value;

    /**
     * Constructor.
     * @param value string.
     */
    public MyString(String value) {
        this.value = value;
    }

    /**
     *
     * @param o MyString
     * @return int value.
     */
    @Override
    public int compareTo(MyString o) {
        return value.toLowerCase().compareTo(o.value.toLowerCase());
    }

    /**
     * equals method.
     * @param obj Object
     * @return true/false.
     */
    @Override
    public boolean equals(Object obj) {
        MyString other = (MyString) obj;
        return value.equals(other.value);
    }

    /**
     * toString method.
     * @return String
     */
    @Override
    public String toString() {
        return value;
    }
}
