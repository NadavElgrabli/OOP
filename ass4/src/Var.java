/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Nadav Elgrabli
 * Class of variable.
 */
public class Var implements Expression {
    private String variableName;

    /**
     * Constructor.
     * @param name of variable.
     */
    public Var(String name) {
        this.variableName = name;
    }

    /**
     *
     * @param assignment assignment map of keys and values.
     * @return true / false
     * @throws Exception exception
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        //if condition checks if variable in map
        if (!assignment.containsKey(variableName)) {
            throw new Exception("var is not in map");
        }
        return assignment.get(variableName);
    }

    /**
     *
     * @return exception
     * @throws Exception cant evaluate with no map.
     */
    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("can't evaluate with no mapping");
    }

    /**
     *
     * @return list of strings.
     */
    @Override
    public List<String> getVariables() {

        //create new list of strings.
        List<String> varsList = new ArrayList<>();

        //ass variable and return list.
        varsList.add(variableName);
        return varsList;
    }

    /**
     *
     * @param var var are replaced with the provided expression (Does not modify the current expression).
     * @param expression that replaces var.
     * @return Returns a new expression in which all occurrences of the variable
     */
    @Override
    public Expression assign(String var, Expression expression) {

        // if condition checks that variable's name is the same as var, it will return the same expression
        if (variableName.equals(var)) {
            return expression;
        }

        //the var is different from the variable name, we return a new variable.
        return new Var(variableName);
    }

    /**
     *
     * @return variable. Cant 'nand' one variable.
     */
    @Override
    public Expression nandify() {
        return new Var(variableName);
    }

    /**
     *
     * @return variable. Cant 'nor' one variable.
     */
    @Override
    public Expression norify() {
        return new Var(variableName);
    }

    /**
     *
     * @return variable, simplify is the same variable.
     */
    @Override
    public Expression simplify() {
        return new Var(variableName);
    }

    /**
     *
     * @return variable name already as string.
     */
    public String toString() {
        return variableName;
    }

}
