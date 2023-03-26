/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Nadav Elgrabli
 * Class creates the abstract class of base expression, which consists of a method evaluate that is similar in all
 * the classes that extend this class.
 */
public abstract class BaseExpression implements Expression {

    /**
     *
     * @return returns an empty map.
     * @throws Exception exception.
     */
    public Boolean evaluate() throws Exception {
        Map<String, Boolean> emptyMap = new TreeMap<>();
        return evaluate(emptyMap);
    }
}
