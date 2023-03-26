/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import java.util.Map;

/**
 * @author Nadav Elgrabli
 * Class of logical expression 'or'
 */
public class Or extends BinaryExpression {
    public static final int MEANINGLESS_NUM = 1;

    /**
     * Constructor.
     * @param leftExpression left expression of logical expression.
     * @param rightExpression right expression of logical expression.
     */
    public Or(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    /**
     *
     * @param assignment map assignment with values and keys
     * @return true / false
     * @throws Exception exception
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        //Return value for expressions with logical operator 'or'
        return getLeftExpression().evaluate(assignment) || getRightExpression().evaluate(assignment);
    }

    /**
     *
     * @param var var are replaced with the provided expression (Does not modify the current expression).
     * @param expression that replaces var.
     * @return Returns a new expression in which all occurrences of the variable
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Or(getLeftExpression().assign(var, expression), getRightExpression().assign(var, expression));
    }

    /**
     *
     * @return expression in 'nand' version.
     */
    @Override
    public Expression nandify() {
        return new Nand(new Nand(getLeftExpression().nandify(), getLeftExpression().nandify()),
                new Nand(getRightExpression().nandify(),
            getRightExpression().nandify()));
    }

    /**
     *
     * @return expression in 'nor' version.
     */
    @Override
    public Expression norify() {
        Expression leftNorRight = new Nor(getLeftExpression().norify(), getRightExpression().norify());
        return new Nor(leftNorRight, leftNorRight);
    }

    /**
     *
     * @return simplified expression
     */
    @Override
    public Expression simplify() {

        //assign short variable names.
        Expression leftSimplified = getLeftExpression().simplify();
        Expression rightSimplified = getRightExpression().simplify();

        // if condition checks that left simplified expression is empty.
        if (leftSimplified.getVariables().isEmpty()) {
            try {
                boolean leftValue = leftSimplified.evaluate();

                // if leftValue == true
                if (leftValue) {
                    return new Val(true);
                } else {
                    return rightSimplified;
                }
            } catch (Exception e) {

                //According to ant check must have at least one statement, its meaningless.
                int a = MEANINGLESS_NUM;
            }
        }

        // if condition checks that right simplified expression is empty.
        if (rightSimplified.getVariables().isEmpty()) {
            try {
                boolean rightValue = rightSimplified.evaluate();

                //if right value is true
                if (rightValue) {
                    return new Val(true);
                } else {
                    return leftSimplified;
                }
            } catch (Exception e) {

                //According to ant check must have at least one statement, its meaningless.
                int a = MEANINGLESS_NUM;
            }
        }

        //if condition if both left and right expression are similar, we can return either one
        if (leftSimplified.toString().equals(rightSimplified.toString())) {
            return leftSimplified;
        }

        //return new expression in Or form.
        return new Or(leftSimplified, rightSimplified);
    }

    /**
     *
     * @return string version of expression.
     */
    public String  toString() {
        return "(" + getLeftExpression().toString() + " | " + getRightExpression().toString() + ")";
    }
}
