/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import java.util.Map;

/**
 * @author Nadav Elgrabli
 * Class of logical expression Xor.
 */
public class Xor extends BinaryExpression {
    public static final int MEANINGLESS_NUM = 1;

    /**
     * Constructor.
     * @param leftExpression left part of expression.
     * @param rightExpression rigth part of expression.
     */
    public Xor(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    /**
     *
     * @param assignment map assignment with values and keys.
     * @return true / false.
     * @throws Exception exception.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        //If both expressions are true
        if (getLeftExpression().evaluate(assignment) && getRightExpression().evaluate(assignment)) {
            return false;
        }

        //if both expressions are false
        if (!getLeftExpression().evaluate(assignment) && !getRightExpression().evaluate(assignment)) {
            return false;
        }

        //Any other form, return true.
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
        return new Xor(getLeftExpression().assign(var, expression), getRightExpression().assign(var, expression));
    }

    /**
     *
     * @return 'nand' form of expression
     */
    @Override
    public Expression nandify() {

        //assign shot variable name
        Expression leftNandRight = new Nand(getLeftExpression().nandify(), getRightExpression().nandify());

        //return 'nand' form of expreesion
        return new Nand(new Nand(getLeftExpression().nandify(), leftNandRight),
                new Nand(getRightExpression().nandify(),
                leftNandRight));
    }

    /**
     *
     * @return 'nor' form of expression.
     */
    @Override
    public Expression norify() {

        //assign short variable names.
        Expression leftNorLeft = new Nor(getLeftExpression().norify(), getLeftExpression().norify());
        Expression rightNorRight = new Nor(getRightExpression().norify(), getRightExpression().norify());
        Expression leftNorRight = new Nor(getLeftExpression().norify(), getRightExpression().norify());

        //return 'nor' form of expression.
        return new Nor(new Nor(leftNorLeft, rightNorRight), leftNorRight);
    }

    /**
     *
     * @return simplified version of expression.
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
                    return new Not(rightSimplified).simplify();

                    // else condition for leftValue == false
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

                //rightValue == true
                if (rightValue) {
                    return new Not(leftSimplified).simplify();

                    //rightValue is false.
                } else {
                    return leftSimplified;
                }
            } catch (Exception e) {

                //According to ant check must have at least one statement, its meaningless.
                int a = MEANINGLESS_NUM;
            }
        }

        //if both expressions are similar, return false.
        if (leftSimplified.toString().equals(rightSimplified.toString())) {
            return new Val(false);
        }
        return new Xor(leftSimplified, rightSimplified);
    }

    /**
     *
     * @return string form of expression.
     */
    public String toString() {
        return "(" + getLeftExpression().toString() + " ^ " + getRightExpression().toString() + ")";
    }
}
