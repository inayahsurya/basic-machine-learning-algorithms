package nearestneighbour;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KNearestNeighbors extends Data {

    private double distance;
    private static double allDistance[][];
    private double sortedDistance[][];
    private int classificationResult[];
    private float error;
    private int k;

    public KNearestNeighbors(List<List<Integer>> records, int splitRatio, int k) {
        super(records, splitRatio);
        this.k = k;
        setAllDistance();
//        printAllDistance();
        sortedData();
        voting(k);
        getError(classificationResult, labelTesting);
    }

    public void setAllDistance() {
        int k = 0;
        int x;
        int y;
        allDistance = new double[dataTesting.length * dataTraining.length][4];

        for (int i = 0; i < dataTesting.length; i++) {
            for (int j = 0; j < dataTraining.length; j++) {
                x = Math.abs(dataTesting[i][0] - dataTraining[j][0]);
                y = Math.abs(dataTesting[i][1] - dataTraining[j][1]);
                allDistance[k][0] = i;
                allDistance[k][1] = j;
                allDistance[k][2] = dataTraining[j][2];
                allDistance[k][3] = getDistance(x, y);
                k++;
            }
        }
    }

    public double getDistance(int a, int b) {
        distance = Math.sqrt((Math.pow(a, 2)) + (Math.pow(b, 2)));
        return distance;
    }

    public void sortedData() {
        sortedDistance = allDistance;
        // sort array on distance (fourth column)
        Arrays.sort(sortedDistance, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o1[3], o2[3]);
            }
        });
        // sort array on dataTesting index (first column)
        Arrays.sort(sortedDistance, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o1[0], o2[0]);
            }
        });
    }

    public void voting(int k) {

        int temp[] = new int[k];
        int m;
        int rslt = 0;
        classificationResult = new int[dataTesting.length];
        for (int i = 0; i < sortedDistance.length; i += dataTraining.length) {
            m = 0;
            for (int j = i; j < k + i; j++) {
                if (sortedDistance[j][2] != 0.0) {
                    temp[m] = (int) sortedDistance[j][2];
                    m++;
                }
            }
            classificationResult[rslt] = modus(temp);
            rslt++;
        }
    }

    public static int modus(int sequence[]) {
        int maxValue = 0, maxCount = 0;

        for (int i = 0; i < sequence.length; ++i) {
            int count = 0;
            for (int j = 0; j < sequence.length; ++j) {
                if (sequence[j] == sequence[i]) {
                    ++count;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = sequence[i];
            }
        }
        return maxValue;
    }

    public void getError(int[] result, int[] hipotesa) {
        int e = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] != hipotesa[i]) {
                e++;
            }
        }
        error = (float) e / result.length;
    }

    public void print() {
        System.out.println("K = " + k);
        System.out.println("Perbandingan label");
        System.out.println("-----------------------");
        System.out.println("Data Testing  \tResult");
        for (int i = 0; i < dataTesting.length; i++) {
            for (int j = 0; j < dataTesting[i].length; j++) {
                System.out.print(dataTesting[i][j] + " ");
            }
            System.out.println("\t" + classificationResult[i]);
        }
        System.out.println("-----------------------");
        System.out.println("Persentase error = " + error + "%");
    }
    
    public void printAllDistance() {
        System.out.println("All distance");
        for (int i = 0; i < allDistance.length; i++) {
            for (int j = 0; j < allDistance[i].length; j++) {
                System.out.print(allDistance[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
