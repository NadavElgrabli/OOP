/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nadav Elgrabli
 * abstract class of binary expression, consists of methods for all logical classes that extend this class.
 */
public abstract class  BinaryExpression extends BaseExpression {
    private Expression leftExpression;
    private Expression rightExpression;

    /**
     * Constructor.
     * @param leftExpression left expression of binary expression.
     * @param rightExpression right expression of binary expression.
     */
    public BinaryExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    /**
     * Getter method.
     * @return left expression.
     */
    public Expression getLeftExpression() {
        return this.leftExpression;
    }

    /**
     * Getter method.
     * @return right expression.
     */
    public Expression getRightExpression() {
        return this.rightExpression;
    }

    /**
     *
     * @return returns all variables in the expression.
     */
    @Override
    public List<String> getVariables() {

        //Creates empty list of strings.
        List<String> expressionList = new ArrayList<>();

        //If both expressions are similar, return a list with one of the expressions.
        if (leftExpression.getVariables().equals(rightExpression.getVariables())) {
            expressionList.addAll(leftExpression.getVariables());
            return expressionList;
        }

        /*
         adds variables of left expression, and checks if similar variables are in the right expression, if so, only
         add the variables that aren't similar.
         */
        expressionList.addAll(leftExpression.getVariables());
        for (int i = 0; i < rightExpression.getVariables().size(); i++) {
            if (!leftExpression.getVariables().contains(rightExpression.getVariables().get(i))) {
                expressionList.add(rightExpression.getVariables().get(i));
            }
        }

        //returns new list.
        return expressionList;

    }
}
