package kmeans;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileConvert {
    private final String src = "G:\\Tugas\\Tugas Semester 5\\machine learning"
            + "\\PartitionClustering\\src\\kmeans\\ruspini.csv";
    private List<List<Integer>> records = new ArrayList<>();

    public void readFile() throws FileNotFoundException { 
        try (Scanner scanner = new Scanner(new File(src));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }
    }

    private List<Integer> getRecordFromLine(String nextLine) {
        List<Integer> values = new ArrayList<Integer>();
        try (Scanner rowScanner = new Scanner(nextLine)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(Integer.parseInt(rowScanner.next()));
            }
        }
        return values;
    }

    public List<List<Integer>> getRecords() {
        return records;
    }
}
