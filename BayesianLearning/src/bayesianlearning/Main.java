package bayesianlearning;

import java.io.FileNotFoundException;

/**
 *
 * @author inayah
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        FileConvert file = new FileConvert();
        file.readFile();
        
        NaiveBayesian bayesian = new NaiveBayesian(file.getRecords());
        bayesian.fakta();
        bayesian.classifier();
        bayesian.result();
    }

}
