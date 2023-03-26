/******************************
 * Name: Nadav Elgrabli.
 * ID: 316082791
 *****************************/
import java.util.Map;

/**
 * @author Nadav Elgrabli
 * class discovers to which hypernym the lemma belongs.
 */
public class DiscoverHypernym {

    /**
     * Main method.
     * @param args string (1) of the absolute path to the directory of the corpus and (2) a lemma.
     */
    public static void main(String[] args) {

        //must contain 2 arguments.
        if (args.length < 2) {
            return;
        }
        String absDirPath = args[0];

        //the given hyponym.
        String lemma = args[1];
        HypernymCounter hypCounter = new HypernymCounter();
        DirectoryLearner dirLearner = new DirectoryLearner(hypCounter);
        try {
            dirLearner.learnDir(absDirPath);

            // relationMap will look like this: {(fruit, {(apple, 1), (banana, 2)})}
            Map<MyString, Map<String, Integer>> relationMap = hypCounter.buildRelationMap();
            for (MyString hypernym : relationMap.keySet()) {
                Map<String, Integer> map = relationMap.get(hypernym);
                if (map.containsKey(lemma)) {
                    System.out.printf("%s: (%d)\n", hypernym, map.get(lemma));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
