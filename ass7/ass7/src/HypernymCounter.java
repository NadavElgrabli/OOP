/******************************
 * Name: Nadav Elgrabli.
 * ID: 316082791
 *****************************/
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Nadav Elgrabli
 * Class counts number of hypernyms
 */
public class HypernymCounter {
    private Map<Hypernym, Integer> map = new TreeMap<>();

    /**
     *
     * @param hypList list of hypernym.
     */
    public void updateMap(List<Hypernym> hypList) {
        for (Hypernym hyp : hypList) {
            int currentCount = 0;
            if (map.containsKey(hyp)) {
                currentCount = map.get(hyp);
            }
            map.put(hyp, currentCount + 1);
        }
    }

    /**
     * method builds a map inside a map of Hypernymy.
     * @return map inside map.
     */
    public Map<MyString, Map<String, Integer>> buildRelationMap() {
        Map<MyString, Map<String, Integer>> relationMap = new TreeMap<>();

        // run through key-value pairs for example ((fruit, banana), 1)
        for (Map.Entry<Hypernym, Integer> entry : map.entrySet()) {

            // hypernym
            String hypernym = entry.getKey().getHypernym();
            MyString hypStr = new MyString(hypernym);

            // if key fruit doesn't exists - create a new map (value) for hypernym
            if (!relationMap.containsKey(hypStr)) {
                relationMap.put(hypStr, new TreeMap<>());
            }

            // example: {(hyponym, 1)}
            Map<String, Integer> hyponymMap = relationMap.get(hypStr);
            String hyponym = entry.getKey().getHyponym();

            // add (hyponym, 2)
            hyponymMap.put(hyponym, entry.getValue());
        }
        Map<MyString, Map<String, Integer>> temp = new TreeMap<>(relationMap);
        for (MyString s :temp.keySet()) {
            if(relationMap.get(s).size() < 3) {
                relationMap.remove(s);
            }
        }
        return relationMap;
    }
}