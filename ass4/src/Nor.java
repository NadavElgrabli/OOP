/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import java.util.Map;

/**
 * @author Nadav Elgrabli
 * Class creates the logical expression 'Nor'.
 */
public class Nor extends BinaryExpression {
    public static final int MEANINGLESS_NUM = 1;

    /**
     * Constructor.
     * @param leftExpression left Expression of logical expression.
     * @param rightExpression right Expression of logical expression.
     */
    public Nor(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    /**
     *
     * @param assignment assignment with values and keys
     * @return true / false
     * @throws Exception exception
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        // returns true if both the left and right expressions are false.
        if (!getLeftExpression().evaluate(assignment) && !getRightExpression().evaluate(assignment)) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param var var are replaced with the provided expression (Does not modify the current expression).
     * @param expression that replaces var.
     * @return Returns a new expression in which all occurrences of the variable
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Nor(getLeftExpression().assign(var, expression), getRightExpression().assign(var, expression));
    }

    /**
     *
     * @return 'Nand' form of the expression
     */
    @Override
    public Expression nandify() {

        //assigning short variable names.
        Expression leftNandLeft = new Nand(getLeftExpression().nandify(), getLeftExpression().nandify());
        Expression rightNandRight = new Nand(getRightExpression().nandify(), getRightExpression().nandify());

        // return desired 'nand' form of expression.
        return new Nand(new Nand(leftNandLeft, rightNandRight), new Nand(leftNandLeft, rightNandRight));
    }

    /**
     *
     * @return 'Nor' form of the expression
     */
    @Override
    public Expression norify() {
        return new Nor(getLeftExpression().norify(), getRightExpression().norify());
    }

    /**
     *
     * @return simplified expression
     */
    @Override
    public Expression simplify() {

        //assign short variable names to expression.
        Expression leftSimplified = getLeftExpression().simplify();
        Expression rightSimplified = getRightExpression().simplify();

        // if condition checks that left simplified expression is empty.
        if (leftSimplified.getVariables().isEmpty()) {
            try {
                boolean leftValue = leftSimplified.evaluate();

                // if leftValue == true
                if (leftValue) {
                    return new Val(false);

                    //else condition when leftValue is false.
                } else {
                    return new Not(rightSimplified).simplify();
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

                // rightValue == true
                if (rightValue) {
                    return new Val(false);

                    //else condition when rightValue is false
                } else {
                    return new Not(leftSimplified).simplify();
                }
            } catch (Exception e) {

                //According to ant check must have at least one statement, its meaningless.
                int a = MEANINGLESS_NUM;
            }
        }

        //if condition when left and right expressions are similar
        if (leftSimplified.toString().equals(rightSimplified.toString())) {

            //Return new expression in 'not' form.
            return new Not(leftSimplified).simplify();
        }

        //Return expression in 'Nor' form.
        return new Nor(leftSimplified, rightSimplified);
    }

    /**
     *
     * @return expression as wanted string.
     */
    @Override
    public String toString() {
        return "(" + getLeftExpression().toString() + " V " + getRightExpression().toString() + ")";
    }
}
