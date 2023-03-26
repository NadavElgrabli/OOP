/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import java.util.Map;

/**
 * @author Nadav Elgrabli
 * Class creates the logical expression 'Nand'.
 */
public class Nand extends  BinaryExpression {
    public static final int MEANINGLESS_NUM = 1;

    /**
     * Constructor.
     * @param leftExpression leftExpression of logical expression.
     * @param rightExpression right expression fo logical expression.
     */
    public Nand(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    /**
     *
     * @param assignment map assignment with true of false values.
     * @return true or false
     * @throws Exception exception
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        //if condition returns false if left expression and right expression are true.
        if (getLeftExpression().evaluate(assignment) && getRightExpression().evaluate(assignment)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param var var are replaced with the provided expression (Does not modify the current expression).
     * @param expression that replaces var.
     * @return Returns a new expression in which all occurrences of the variable
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Nand(getLeftExpression().assign(var, expression), getRightExpression().assign(var, expression));
    }

    /**
     *
     * @return 'Nand' form of the expression.
     */
    @Override
    public Expression nandify() {
        return new Nand(getLeftExpression().nandify(), getRightExpression().nandify());
    }

    /**
     *
     * @return 'Nor' from of the expression.
     */
    @Override
    public Expression norify() {

        //assign short variable names.
        Expression leftNorLeft = new Nor(getLeftExpression().norify(), getLeftExpression().norify());
        Expression rightNorRight = new Nor(getRightExpression().norify(), getRightExpression().norify());

        //return desired expression.
        return new Nor(new Nor(leftNorLeft, rightNorRight), new Nor(leftNorLeft, rightNorRight));
    }

    /**
     *
     * @return a simplification of the expression.
     */
    @Override
    public Expression simplify() {

        //assign short variable names.
        Expression leftSimplified = getLeftExpression().simplify();
        Expression rightSimplified = getRightExpression().simplify();

        if (leftSimplified.getVariables().isEmpty()) {
            try {
                boolean leftValue = leftSimplified.evaluate();

                // if leftValue == true
                if (leftValue) {

                    //return 'not' form of right expression
                    return new Not(getRightExpression()).simplify();
                } else {
                    return new Val(true);
                }
            } catch (Exception e) {

                //According to ant check must have at least one statement, its meaningless.
                int a = MEANINGLESS_NUM;
        }

        if (rightSimplified.getVariables().isEmpty()) {
            try {
                boolean rightValue = rightSimplified.evaluate();

                //if right value is true
                if (rightValue) {

                    //return 'not' form of left expression
                    return new Not(getLeftExpression()).simplify();
                } else {
                    return new Val(true);
                }
            } catch (Exception e) {

                //According to ant check must have at least one statement, its meaningless.
                int a = MEANINGLESS_NUM;
            }
          }
        }

        //if left expression is similar to right expression, return 'not' form of either one of them.
        if (leftSimplified.toString().equals(rightSimplified.toString())) {
            return new Not(leftSimplified).simplify();
        }

        //return 'Nand' form.
        return new Nand(leftSimplified, rightSimplified);
    }

    /**
     *
     * @return the expression as wanted string.
     */
    @Override
    public String toString() {
        return "(" + getLeftExpression().toString() + " A " + getRightExpression().toString() + ")";
    }
}
