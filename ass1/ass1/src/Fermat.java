/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

/**
 * @author Nadav Elgrabli
 * Class receives two strings as input, the first is exponent and the second is range of numbers, and checks for
 * triple numbers that apply Fermat's law.
 */
public class Fermat {
    /**
     * Method name: main
     * Input: Two strings
     * Output: void, only prints.
     * Operation: Method receives an exponent (power) and a range of numbers, and checks for three numbers that
     *            apply Fermat's law within the range. It does so by firstly checking that the input is valid
     *            (for example, that the range and power are positive integers), and then using three 'for' loops
     *            inside each other, to go over 3 numbers in the same range, we use method "pow" to check if
     *            Fermat's law can be applied. If so, we print the numbers. We make sure that we print the numbers
     *            in the correct order and not more than once using another 'if' statement.
     * @param args receives from user array of strings, and splits into two different arguments.
     */
    public static void main(String[] args) {

        // Assigning values to variables we will be using.
        final int firstNumIndex = 0;
        final int secondNumIndex = 1;
        final int maxExponent = 2;
        final int minValue = 0;
        final int correctArgsLength = 2;

        // Checking that the input has a correct number of values
        if (args.length != correctArgsLength) {
            System.out.println("Invalid input");
            return;
        }

        // Assigning variables to fittings variable names
        int power = Integer.parseInt(args[firstNumIndex]);
        int range = Integer.parseInt(args[secondNumIndex]);

        // Checking if one of the values is not positive, print invalid
        if (power <= minValue || range <= minValue) {
            System.out.println("Invalid input");
            return;
        }

        //if condition checks that the power is not bigger than 2, because Fermat's law wont work.
        if (power > maxExponent) {
            System.out.println("no");
            return;
        }

        /*
         * Using 3 for loops inside each other, to go over all the numbers from 1 to range - 1, and check for any
         * values that uphold Fermat's law .
         */
        for (int i = 1; i < range; i++) {
            for (int j = 1; j < range; j++) {
                for (int k = 1; k < range; k++) {

                    // if condition to check Fermat's law
                    if (Math.pow(i, power) + Math.pow(j, power) == Math.pow(k, power)) {

                        /*
                         * If condition to remove repeating values that uphold fermat's law, and make sure the
                         * printing is in correct order of values.
                         */
                        if (i <= j && j <= k) {
                            System.out.println(i + "," + j + "," + k);
                        }
                    }
                }
            }
        }
    }
}