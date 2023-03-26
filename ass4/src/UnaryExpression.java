/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import java.util.List;

/**
 * @author Nadav Elgrabli
 * abstract class for Unary expression.
 */
public abstract class UnaryExpression extends  BaseExpression {
    private Expression expression;

    /**
     * constructor.
     * @param expression expression.
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Getter.
     * @return this expression.
     */
    public Expression getExpression() {
        return this.expression;
    }

    /**
     * @return list of all varaibles inside the expression.
     */
    public List<String> getVariables() {
        return expression.getVariables();
    }
}
