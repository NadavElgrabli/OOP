/******************************
 * Name: Nadav Elgrabli.
 * ID: 316082791
 *****************************/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Nadav Elgrabli
 * Class reads the specific file.
 */
public class FileLearner {
    private LineLearner lineLearner;

    /**
     * Constructor.
     * @param counter counter.
     */
    public FileLearner(HypernymCounter counter) {
        lineLearner = new LineLearner(counter);
    }

    /**
     * method reads the line in file.
     * @param pathToFile string path to file.
     * @throws IOException exception.
     */
    public void learnFile(String pathToFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
        String line;
        while ((line = reader.readLine()) != null) {
            lineLearner.learnLine(line);
        }
        reader.close();
    }
}