/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import java.util.Map;

/**
 * @author Nadav Elgrabli
 * Class creates the logical expression 'And'.
 */
public class And extends BinaryExpression {

    /**
     * Constructor of logical expression.
     * @param leftExpression left expression of the logical expression
     * @param rightExpression right expression of the logical expression
     */
    public And(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    /**
     * Example: x & y,      x = T, y = F,   return:  F.
     * @param assignment receives a map with values of true a false.
     * @return true or false
     * @throws Exception exception.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        //Return value for expressions with logical operator 'and'
        return getLeftExpression().evaluate(assignment) && getRightExpression().evaluate(assignment);
    }

    /**
     * Example: x & y,    x = T , y = F,    x ----> z = F         we will return: F.
     * @param var receives string with a specific value
     * @param expression receives an expression.
     * @return a new expression after we assigned new values.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new And(getLeftExpression().assign(var, expression), getRightExpression().assign(var, expression));
    }

    /**
     *
     * @return returns the new expression if form of 'Nand'
     */
    @Override
    public Expression nandify() {
        Expression leftNandRight = new Nand(getLeftExpression().nandify(), getRightExpression().nandify());
        return new Nand(leftNandRight, leftNandRight);
    }

    /**
     *
     * @return new expression in form 'Nor'
     */
    @Override
    public Expression norify() {

        //assigning short variable names.
        Expression leftNorLeft = new Nor(getLeftExpression().norify(), getLeftExpression().norify());
        Expression rightNorRight = new Nor(getRightExpression().norify(), getRightExpression().norify());

        //returning desired output to norify.
        return new Nor(leftNorLeft, rightNorRight);
    }

    /**
     *
     * @return simplified expression according to logical rules
     */
    @Override
    public Expression simplify() {

        //assign short variable names.
        Expression leftSimplified = getLeftExpression().simplify();
        Expression rightSimplified = getRightExpression().simplify();

        //if left expression is true we can return right expression
        if (leftSimplified.toString().equals("T")) {
            return rightSimplified;
        }

        //if right expression is true we can return left expression.
        if (rightSimplified.toString().equals("T")) {
            return leftSimplified;
        }

        // if condition checks that left simplified expression is empty.
        if (leftSimplified.getVariables().isEmpty()) {
            try {

                //assign leftValue true/false
                boolean leftValue = leftSimplified.evaluate();
                if (leftValue) {
                    return rightSimplified;

                    //meaning the leftValue is false
                } else {
                    return new Val(false);
                }
            } catch (Exception e) {

                //According to ant check must have at least one statement, its meaningless.
                int a = 5;
            }

            //if condition checks that right simplified expression is empty.
            if (rightSimplified.getVariables().isEmpty()) {
                try {

                    //assign rightValue true/false
                    boolean rightValue = rightSimplified.evaluate();
                    if (rightValue) {
                        return leftSimplified;
                    } else {
                        return new Val(false);
                    }
                } catch (Exception e) {

                    //According to ant check must have at least one statement, its meaningless.
                    int a = 5;
                }
            }
        }

        //If leftSimplified is similar to rightSimplified, return either one.
        if (leftSimplified.toString().equals(rightSimplified.toString())) {
            return leftSimplified;
        }

        if (leftSimplified.toString().equals("F") || rightSimplified.toString().equals("F")) {
            return new Val(false);
        }

        //Return And expression.
        return new And(leftSimplified, rightSimplified);
    }

    /**
     *
     * @return the expression in string form.
     */
    public String toString() {
        return "(" + getLeftExpression().toString() + " & " + getRightExpression().toString() + ")";
    }

}
