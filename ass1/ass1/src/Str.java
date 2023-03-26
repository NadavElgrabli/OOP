/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

/**
 * @author Nadav Elgrabli
 * Class receives two strings as input, and checks if the first string is a substring of the second string.
 */
public class Str {
    /**
     * Method name: main
     * Input: two strings
     * Output: void, only prints.
     * Operation: The method checks if the query is a substring in any of the words separated by "_" in the
     *            sequence. It does so by using the method "split", in order to create a new array with all the
     *            string that are split by "_", and then using the method "indexOf" to check if any of th words
     *            start with query. Then using the method "contains" it checks for the strings that include the
     *            substring not from the beginning of the string, and prints all the strings that include the query
     *            inside them.
     * @param args receives an array of strings, and splits them into two different arguments.
     */
    public static void main(String[] args) {

        // Assigning values to variables we will be using.
        final int firstStrIndex = 0;
        final int secondStrIndex = 1;
        final int argsLength = 2;

        // if condition to check that sequence input is valid
        if (args.length != argsLength) {
            System.out.println("Invalid input");
            return;
        }

        // Assigning fitting variable names to variables
        String query = args[firstStrIndex];
        String sequence = args[secondStrIndex];

        // Creating a new array of strings that has all the strings separated by '_'.
        String[] arrOfStr = sequence.split("_");

        /*
         * Using for loop to run on all the strings inside the array, and using function 'indexOf' to check if each
         *  word begins with a similar string of the query.
         */
        for (int j = 0; j < arrOfStr.length; j++) {
            if (arrOfStr[j].indexOf(query) == firstStrIndex) {
                System.out.println(arrOfStr[j]);
            }
        }

        // This for loop is to check if the strings inside the array have a substring of the string of query.
        for (int i = 0; i < arrOfStr.length; i++) {

            /*
             * The if condition also checks that the word that has a substring of the query does not start as the
             * query using 'indexOf' function.
             */
            if (arrOfStr[i].contains(query) && arrOfStr[i].indexOf(query) != firstStrIndex) {
                System.out.println(arrOfStr[i]);
            }
        }
    }
}