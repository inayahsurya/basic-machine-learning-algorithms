package neuralnetworkruspini;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NeuralNetworkRuspini {

    public int dataSet[][];
    public int target[];
    public float w[];
    public float miu = (float) 0.1;
    public float treshold = 0;
    public int epoch;

    public int[][] readFile() throws FileNotFoundException {
        List<List<Integer>> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("G:\\Tugas\\Tugas Semester 5\\NeuralNetworkRuspini\\ruspini.csv"));) {
            while (scanner.hasNextLine()) {
                data.add(getRecordFromLine(scanner.nextLine()));
            }
        }
        dataSet = new int[data.size()][];
        for (int i = 0; i < dataSet.length; i++) {
            dataSet[i] = new int[data.get(i).size() + 1];
        }
        int k;
        for (int i = 0; i < data.size(); i++) {
            k = 0;
            dataSet[i][0] = 1;
            for (int j = 1; j <= data.get(i).size(); j++) {
                dataSet[i][j] = data.get(i).get(k);
                k++;
            }
        }
        return dataSet;
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

    public void setTarget() {
        target = new int[dataSet.length];
        for (int i = 0; i < dataSet.length; i++) {
            for (int j = 0; j < dataSet[i].length; j++) {
                if (dataSet[i][j] == 1 || dataSet[i][j] == 2) {
                    target[i] = 0;
                } else {
                    target[i] = 1;
                }

            }
        }
    }

    public static int getRandom(int min, int max) {
        int x = (int) (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

    public void randomWeight() {
        w = new float[dataSet[0].length - 1];
        for (int i = 0; i < w.length; i++) {
            w[i] = getRandom(-1, 1);
        }
    }

    public void setEpoch() {
        System.out.print("Epoch = ");
        Scanner key = new Scanner(System.in);
        epoch = Integer.parseInt(key.nextLine());
    }

    public void summation() {
        float perceptron = 0;
        float err;
        int output[] = new int[dataSet.length];
        for (int k = 0; k < epoch; k++) {
            for (int i = 0; i < dataSet.length; i++) {
                for (int j = 0; j < w.length; j++) {
                    perceptron = perceptron + (dataSet[i][j] * w[j]);
                }

                if (perceptron < treshold) {
                    output[i] = 0;
                } else {
                    output[i] = 1;
                }
                err = target[i] - output[i];
                if (target[i] != output[i]) {
                    singlePerceptronLearning(i, err);
                }
            }
            if (check(output) == true) {
                break;
            }
        }
        printResult(output);
    }

    public void singlePerceptronLearning(int i, float err) {
        for (int j = 0; j < w.length; j++) {
            w[j] = w[j] + (miu * dataSet[i][j] * err);
        }
    }

    public boolean check(int output[]) {
        for (int i = 0; i < output.length; i++) {
            if (output[i] != target[i]) {
                return false;
            }
        }
        return true;
    }

    public void dataTestingSummation() {
        int dataTesting[] = new int[3];
        float sum = 0;
        int hasil;
        Scanner scan = new Scanner(System.in);
        String line;
        String lineSplit[];
        String lagi = "y";
        System.out.println("\n---DATA TESTING---");
        do {
            System.out.print("Data testing (x,y) = ");
            line = scan.nextLine();
            lineSplit = line.split(",");
            dataTesting[0] = 1;
            for (int i = 1; i < dataTesting.length; i++) {
                dataTesting[i] = Integer.parseInt(lineSplit[i - 1]);
            }

            for (int i = 0; i < w.length; i++) {
                sum += dataTesting[i] * w[i];
            }
            if (sum < treshold) {
                hasil = 0;
            } else {
                hasil = 1;
            }

            for (int i = 0; i < dataTesting.length; i++) {
                System.out.print(dataTesting[i] + " ");
            }
            System.out.println("= " + hasil);
            System.out.print("Apakah anda ingin mencoba data lain?(y/n) ");
            lagi = scan.nextLine();
            System.out.println();
        } while (lagi.equalsIgnoreCase("y"));
    }

    public void printResult(int[] output) {
        System.out.println("\n---HASIL NEURAL NETWORK---");
        System.out.println("Epoch : " + epoch);
        for (int k = 0; k < dataSet.length; k++) {
            for (int j = 0; j < dataSet[k].length - 1; j++) {
                System.out.print(dataSet[k][j] + " ");
            }
            System.out.println("= " + output[k]);
        }
//        System.out.println("");
//        for (int i = 0; i < w.length; i++) {
//            System.out.println("W[" + i + "] = " + w[i]);
//        }
    }
    
}
