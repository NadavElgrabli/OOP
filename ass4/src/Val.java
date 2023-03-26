/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Nadav Elgrabli
 * Class of simple value.
 */
public class Val implements Expression {
    private boolean value;

    /**
     * Constructor.
     * @param val value.
     */
    public Val(boolean val) {
        this.value = val;
    }

    /**
     *
     * @param assignment assignment map with keys and values.
     * @return value, because there are no variables.
     * @throws Exception exception
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return value;
    }

    /**
     *
     * @return value.
     * @throws Exception exception.
     */
    @Override
    public Boolean evaluate() throws Exception {
        return value;
    }

    /**
     * Getter of values.
     * @return return new list of strings of values.
     */
    @Override
    public List<String> getVariables() {
        List<String> valList = new ArrayList<>();
        return valList;
    }

    /**
     *
     * @param var var are replaced with the provided expression (Does not modify the current expression).
     * @param expression that replaces var.
     * @return simple value, becase we only have value and no expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Val(value);
    }

    /**
     *
     * @return value, no 'nand' form.
     */
    @Override
    public Expression nandify() {
        return new Val(value);
    }

    /**
     *
     * @return value, no 'nor'' form.
     */
    @Override
    public Expression norify() {
        return new Val(value);
    }

    /**
     *
     * @return value, no simplification needed.
     */
    @Override
    public Expression simplify() {
        return new Val(value);
    }

    /**
     *
     * @return return "T" or "F" as string..
     */
    public String toString() {
        if (value) {
            return "T";
        }
        return "F";
    }
}
