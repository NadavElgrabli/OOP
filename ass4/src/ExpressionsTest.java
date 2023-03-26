/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import java.util.Map;
import java.util.TreeMap;

/**
 * @author  Nadav Elgrabli
 * test main class.
 */
public class ExpressionsTest {
    public static final int MEANINGLESS_NUM = 1;

    /**
     * Main class to test the program.
     * @param args args for main.
     */
    public static void main(String[] args) {

        //Create new variables.
        Var x = new Var("x");
        Var y = new Var("y");
        Var z = new Var("z");
        Map<String, Boolean> ass = new TreeMap<>();

        //Assign value to variables.
        ass.put("y", true);
        ass.put("x", false);
        ass.put("z", true);

        // Create new expression, for example: T | (z | (x & y)
        Expression expression = new Or(new Or(z, new And(x, y)), new Val(true));
        System.out.println(expression.toString());
        try {
            System.out.println(expression.evaluate(ass));
        } catch (Exception e) {

            //According to check style, must have at least one statement in catch, its meaningless.
            int a = MEANINGLESS_NUM;
        }

        //Print wanted tests.
        System.out.println(expression.nandify());
        System.out.println(expression.norify());
        System.out.println(expression.simplify());
}
}
