/******************************
 * Name: Nadav Elgrabli.
 * ID: 316082791
 *****************************/
import java.io.File;

/**
 * @author Nadav Elgrabli
 * Class that goes through all the files in directory one by one.
 */
public class DirectoryLearner {
    private FileLearner fileLearner;

    /**
     * Constructor.
     * @param counter counter
     */
    public DirectoryLearner(HypernymCounter counter) {
        fileLearner = new FileLearner(counter);
    }

    /**
     * method goes one by one in the files of directory.
     * @param pathToDirectory string of directory path.
     * @throws Exception exception.
     */
    public void learnDir(String pathToDirectory) throws Exception {
        final File folder = new File(pathToDirectory);
        if (!folder.isDirectory()) {
            throw new Exception("not a directory");
        }
        File[] files = folder.listFiles();
        if (files == null) {
            throw new Exception("no permission to open file");
        }

        //Go through each file one by one to avoid too much memory problem.
        for (final File fileEntry : files) {
            fileLearner.learnFile(pathToDirectory + "/" + fileEntry.getName());
        }
    }
}