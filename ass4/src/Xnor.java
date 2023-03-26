/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import java.util.Map;

/**
 * @author Nadav Elgrabli
 * Class of logical expression Xnor.
 */
public class Xnor extends BinaryExpression {

    /**
     * Constructor.
     * @param leftExpression left part of logical expression.
     * @param rightExpression right part of logical expression.
     */
    public Xnor(Expression leftExpression, Expression rightExpression) {
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

        //Both expressions are true
        if (getLeftExpression().evaluate(assignment) && getRightExpression().evaluate(assignment)) {
            return true;
        }

        //Both expressions are false
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
        return new Xnor(getLeftExpression().assign(var, expression), getRightExpression().assign(var, expression));
    }

    /**
     *
     * @return 'nand' version of expression.
     */
    @Override
    public Expression nandify() {

        //short variable names.
        Expression leftNandLeft = new Nand(getLeftExpression().nandify(), getLeftExpression().nandify());
        Expression rightNandRight = new Nand(getRightExpression().nandify(), getRightExpression().nandify());
        Expression leftNandRight = new Nand(getLeftExpression().nandify(), getRightExpression().nandify());

        //return 'nand' version of expression
        return new Nand((new Nand(leftNandLeft, rightNandRight)), leftNandRight);
    }

    /**
     *
     * @return 'nor' version of expression.
     */
    @Override
    public Expression norify() {

        //short variable names.
        Expression leftNorRight = new Nor(getLeftExpression().norify(), getRightExpression().norify());

        //return 'nor' version of expression
        return new Nor(new Nor(getLeftExpression().norify(), leftNorRight), new Nor(getRightExpression().norify(),
                leftNorRight));
    }

    /**
     *
     * @return simplified expression
     */
    @Override
    public Expression simplify() {

        //short variable names.
        Expression leftSimplified = getLeftExpression().simplify();
        Expression rightSimplified = getRightExpression().simplify();

        //if both expressions are similar, return true.
        if (leftSimplified.toString().equals(rightSimplified.toString())) {
            return new Val(true);
        }

        /*
         we want to evaluate the expression if possible(e.g if the expression is built only from vals-- T#F=F)
         so to do so we we'll call to evaluate of the simplification of this expression. if succeeds sso that's mean
         we got value and return it, otherwise we cant simplify anymore and return new new expression with the
         simplify fields.
         */
        try {
                return new Val(new Xnor(leftSimplified, rightSimplified).evaluate());
        } catch (Exception e) {
            return new Xnor(leftSimplified, rightSimplified);
        }
    }

    /**
     *
     * @return string version of expression.
     */
    public String toString() {
        return "(" + getLeftExpression().toString() + " # " + getRightExpression().toString() + ")";
    }
}
