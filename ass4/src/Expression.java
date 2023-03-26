/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import java.util.List;
import java.util.Map;

/**
 * Expression interface.
 */
public interface Expression {

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     * @param assignment assignment
     * @return boolean
     * @throws Exception exeption
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @return boolean
     * @throws Exception exeption
     */
    Boolean evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     * @return list
     */
    List<String> getVariables();

    /**
     * @return Returns a nice string representation of the expression.
     */
    String toString();

    /**
     *
     * @param var var are replaced with the provided expression (Does not modify the current expression).
     * @param expression that replaces var.
     * @return Returns a new expression in which all occurrences of the variable
     */
    Expression assign(String var, Expression expression);

    /**
     *
     * @return Returns the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    Expression nandify();


    /**
     *
     * @return Returns the expression tree resulting from converting all the operations to the logical Nor operation.
     */
    Expression norify();

    /**
     *
     * @return Returned a simplified version of the current expression.
     */
    Expression simplify();
}