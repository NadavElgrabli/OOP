/******************************
 * Name: Nadav Elgrabli.
 * ID: 316082791
 *****************************/
import java.util.List;
import java.util.Map;

/**
 * @author Nadav Elgrabli
 * Class reads each line seperately.
 */
public class LineLearner {
    private HypernymCounter counter;
    private MatchChecker matchChecker;
    private MatchParser matchParser;

    /**
     * Constructor.
     * @param counter HypernymCounter counter.
     */
    public LineLearner(HypernymCounter counter) {
        this.counter = counter;
        matchChecker = new MatchChecker();
        matchParser = new MatchParser();
    }

    /**
     * check if line contains matching regex.
     * @param line string line.
     */
    public void learnLine(String line) {
        Map<String, MatchChecker.Option> matches = matchChecker.checkMatches(line);
        List<Hypernym> hypernymies = MatchParser.parseMap(matches);
        counter.updateMap(hypernymies);
    }
}
