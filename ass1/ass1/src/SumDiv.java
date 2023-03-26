/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

/**
 * @author Nadav Elgrabli
 * Class receives three strings as input, converts the strings into integers, and prints all the numbers range 1 to
 * the first number, that are devided by the second or third numbers, and prints the sum of these numbers.
 */
public class SumDiv {
    /**
     * Method name: main
     * Input: three strings
     * Output: void, only prints.
     * Operation: Method receives three strings of numbers, and using method Integer.parseInt we convert the strings
     * into numbers. Then we check that the input is valid (meaning we check all the integers are positive). Then
     * we use a 'for' loop to go over all the numbers from 1 to the first number, and using operator % we check for
     * all the numbers that can be divided by the second OR third number. We print those numbers, and then we add
     * their value to the variable 'sum' which at the end we will also print as requested.
     * @param args receive from user array of strings, and splits into three different arguments.
     */
    public static void main(String[] args) {

        // Assigning values to variables we will be using.
        final int firstNumIndex = 0;
        final int secondNumIndex = 1;
        final int thirdNumIndex = 2;
        final int correctLength = 3;
        final int minValue = 0;

        // if condition checks that the number of arguments are valid
        if (args.length != correctLength) {
            System.out.println("Invalid input");
            return;
        }

        // assigning fitting variable names to the arguments
        int firstNum = Integer.parseInt(args[firstNumIndex]);
        int secondNum = Integer.parseInt(args[secondNumIndex]);
        int thirdNum = Integer.parseInt(args[thirdNumIndex]);

        /* if condition checks that all given numbers are positive */
        if (firstNum <= minValue || secondNum <= minValue || thirdNum <= minValue) {
            System.out.println("Invalid input");
            return;
        }

        /*
         * Using a for loop to calculate the sum of all the that are divisible by the second or third numbers
         * (using % to check).
         */
        int sum = 0;
        for (int i = 1; i <= firstNum; i++) {

            // Checking to see if the number is divisible by the second or third number
            if (i % secondNum == 0 || i % thirdNum == 0) {
                System.out.println(i);
                sum = sum + i;
            }
        }

        // Printing the sum
        System.out.println(sum);
    }
}