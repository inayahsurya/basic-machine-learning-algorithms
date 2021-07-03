package bayesianlearning;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileConvert {
    private final String src = "G:\\Tugas\\Semester 5\\machine learning\\"
            + "BayesianLearning\\src\\bayesianlearning\\bayesian.csv";
    private final List<List<String>> records = new ArrayList<>();

    public void readFile() throws FileNotFoundException { 
        try (Scanner scanner = new Scanner(new File(src));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }
    }

    private List<String> getRecordFromLine(String nextLine) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(nextLine)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public List<List<String>> getRecords() {
        return records;
    }

}
