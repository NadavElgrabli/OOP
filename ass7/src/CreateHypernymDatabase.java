/******************************
 * Name: Nadav Elgrabli.
 * ID: 316082791
 *****************************/
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

/**
 * @author Nadav Elgrabli
 * Class creates a file with the hypernym data base by order.
 */
public class CreateHypernymDatabase {

    /**
     * main method.
     * @param args string of file locations.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            return;
        }
        String dirPath = args[0];
        String outputPath = args[1];
        HypernymCounter hypCounter = new HypernymCounter();
        DirectoryLearner dirLearner = new DirectoryLearner(hypCounter);
        try {
            dirLearner.learnDir(dirPath);

            // relationMap will look like this: {(fruit, {(apple, 1), (banana, 2)})}
            Map<MyString, Map<String, Integer>> relationMap = hypCounter.buildRelationMap();
            writeFile(relationMap, outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method writes the file of the database.
     * @param relationMap map that contains map.
     * @param path string to output file.
     * @throws IOException throw exception.
     */
    private static void writeFile(Map<MyString, Map<String, Integer>> relationMap, String path) throws IOException {
        PrintWriter fileWriter = new PrintWriter(new FileWriter(path));
        List<MyString> sortedHypernyms = new ArrayList<>(relationMap.keySet());
        Collections.sort(sortedHypernyms);

        //for loop to run on all hypernyms in arraylist.
        for (MyString hypernym : sortedHypernyms) {

            //Create string builder that we can update each time.
            StringBuilder builder = new StringBuilder();
            builder.append(hypernym).append(": ");
            List<Map.Entry<String, Integer>> sortedHyponyms = new ArrayList<>(relationMap.get(hypernym).entrySet());
            sortedHyponyms.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
            });

            //for loop to run on all maps and add to builder.
            for (Map.Entry<String, Integer> entry : sortedHyponyms) {
                builder.append(entry.getKey()).append(" (").append(entry.getValue()).append("), ");
            }

            //test
            if (sortedHypernyms.size() >= 3) {
                fileWriter.println(builder.toString());
            }
        }
        fileWriter.close();
    }
}
