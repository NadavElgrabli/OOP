/******************************
 * Name: Nadav Elgrabli.
 * ID: 316082791
 *****************************/
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nadav Elgrabli.
 * Class checks if matching regex exists in data.
 */
public class MatchChecker {
    private final String npString = "<np>[A-Za-z\\\\s]+</np>";

    /**
     * enum for short variable names.
     */
    enum Option {
        Regex_1,
        Regex_2,
        Regex_3,
        Regex_4,
        Regex_5,
    }

    //Define regexes.
    private final String firstRegex = ("NP( ,)? such as NP( , NP)*(( ,)? (and|or) <np>"
            + ".+?</np>)?").replaceAll("NP", npString);
    private final String secondRegex = ("such NP as NP( , NP)*(( ,)? (and|or) <np>"
            + ".+?</np>)?").replaceAll("NP", npString);
    private final String thridRegex = ("NP( ,)? including NP( , NP)*(( ,)? (and|or) <np>"
            + ".+?</np>)?").replaceAll("NP", npString);
    private final String fourthRegex =
            ("NP( ,)? especially NP( , NP)*(( ,)? (and|or) NP)?").replaceAll("NP", npString);
    private final String fifthRegex =
            ("NP( ,)? which is ((an example|a kind| a class)? of)? NP").replaceAll("NP", npString);

    /**
     * method receives a string line and returns map with the string and its matching regex.
     * @param line string
     * @return map.
     */
    public Map<String, MatchChecker.Option> checkMatches(String line) {
        Map<String, MatchChecker.Option> map = new TreeMap<>();

        //check if first regex inside line.
        Pattern p1 = Pattern.compile(firstRegex);
        Matcher m1 = p1.matcher(line);

        //check if second regex inside line.
        Pattern p2 = Pattern.compile(secondRegex);
        Matcher m2 = p2.matcher(line);

        //check if third regex inside line.
        Pattern p3 = Pattern.compile(thridRegex);
        Matcher m3 = p3.matcher(line);

        //check if fourth regex inside line.
        Pattern p4 = Pattern.compile(fourthRegex);
        Matcher m4 = p4.matcher(line);

        //check if fifth regex inside line.
        Pattern p5 = Pattern.compile(fifthRegex);
        Matcher m5 = p5.matcher(line);

        //While loops adds to map the substring according to index of hypernym and matching regex.
        while (m1.find()) {
            map.put(line.substring(m1.start(), m1.end()), Option.Regex_1);
        }
        while (m2.find()) {
            map.put(line.substring(m2.start(), m2.end()), Option.Regex_2);
        }
        while (m3.find()) {
            map.put(line.substring(m3.start(), m3.end()), Option.Regex_3);
        }
        while (m4.find()) {
            map.put(line.substring(m4.start(), m4.end()), Option.Regex_4);
        }
        while (m5.find()) {
            map.put(line.substring(m5.start(), m5.end()), Option.Regex_5);
        }
        return map;
    }
}