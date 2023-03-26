/******************************
 * Name: Nadav Elgrabli.
 * ID: 316082791
 *****************************/
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nadav Elgrabli
 * Class will create list of Hypernym.
 */
public class MatchParser {

    /**
     * method receives map and returns list, example: fruit such as banana and apple --> {(fruit, banana), (fruit,
     * apple)}.
     * @param matches map.
     * @return list of Hypernym.
     */
    public static List<Hypernym> parseMap(Map<String, MatchChecker.Option> matches) {
        List<Hypernym> list = new ArrayList<>();

        // run through map entries (key, value).
        for (Map.Entry<String, MatchChecker.Option> entry : matches.entrySet()) {
            String line = entry.getKey();
            List<String> npList = getNP(line);
            String hypernym;

            // regex 5: hypernym is in second index, otherwise, hypernym is in first index.
            if (entry.getValue() == MatchChecker.Option.Regex_5) {
                hypernym = npList.get(1);
                npList.remove(1);
            } else {
                hypernym = npList.get(0);
                npList.remove(0);
            }
            for (String hyponym : npList) {
                Hypernym pair = new Hypernym(hypernym, hyponym);
                list.add(pair);
            }
        }
        return list;
    }

    /**
     * method gets string line and returns list, example: fruit such as banana and apple --> {fruit, banana, apple}.
     * @param line string.
     * @return list.
     */
    private static List<String> getNP(String line) {
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?<=<np>).+?(?=</np>)");
        Matcher matcher = pattern.matcher(line);

        //while loop to find the index of beginning and end of matcher, and add to list.
        while (matcher.find()) {
            String match = line.substring(matcher.start(), matcher.end());
            list.add(match);
        }
        return list;
    }
}
