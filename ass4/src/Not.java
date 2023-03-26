/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import java.util.Map;

/**
 * @author Nadav Elgralbi
 * class for logical expression of 'Not'.
 */
public class Not extends  UnaryExpression {
    public static final int MEANINGLESS_NUM = 1;

    /**
     * Constructor.
     * @param expression expression
     */
    public Not(Expression expression) {
        super(expression);
    }

    /**
     *
     * @param assignment map of assignement with keys and values.
     * @return true / false
     * @throws Exception exception
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        // '!' for the not form.
        return !getExpression().evaluate(assignment);
    }

    /**
     *
     * @param var var are replaced with the provided expression (Does not modify the current expression).
     * @param expression that replaces var.
     * @return Returns a new expression in which all occurrences of the variable.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(getExpression().assign(var, expression));
    }

    /**
     *
     * @return new expression in 'Nand' form.
     */
    @Override
    public Expression nandify() {
        return new Nand(getExpression().nandify(), getExpression().nandify());
    }

    /**
     *
     * @return new expression in 'Nor' form.
     */
    @Override
    public Expression norify() {
        return new Nor(getExpression().norify(), getExpression().norify());
    }

    /**
     *
     * @return simplified version of expression.
     */
    @Override
    public Expression simplify() {

        //short variable name.
        Expression simplifiedExpression = getExpression().simplify();
        if (simplifiedExpression.getVariables().isEmpty()) {
            try {
                boolean value = simplifiedExpression.evaluate();

                //if value is true.
                if (value) {
                    return new Val(false);
                } else {

                    //return value of true
                    return new Val(true);
                }
            } catch (Exception e) {

                //According to ant check must have at least one statement, its meaningless.
                int a = MEANINGLESS_NUM;
            }
        }

        //return simplified expression in not form.
        return new Not(simplifiedExpression);
    }

    /**
     *
     * @return return expression as string.
     */
    public String toString() {
        return "~(" + getExpression().toString() + ")";
    }
}
