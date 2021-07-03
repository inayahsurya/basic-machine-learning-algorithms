package neuralnetwork;

import java.util.List;

/**
 *
 * @author asus
 */
public class Data {

    private int dataRuspini[][];
    private int input[][] = {
        {1, 0, 0},
        {1, 0, 1},
        {1, 1, 0},
        {1, 1, 1}};

    private int targetAND[] = {0, 0, 0, 1};
    private int targetOR[] = {0, 1, 1, 1};
    private int targetRuspini[];

    public void setDataRuspini(List<List<Integer>> records) {
        dataRuspini = new int[records.size()][];

        for (int i = 0; i < dataRuspini.length; i++) {
            dataRuspini[i] = new int[records.get(i).size()];
        }
        for (int i = 0; i < records.size(); i++) {
            int k = 0;
            dataRuspini[i][0] = 1;
            for (int j = 1; j < records.get(i).size(); j++) {
                dataRuspini[i][j] = records.get(i).get(k);
                k++;
            }
        }
        setTargetRuspini(records);
    }

    public void setTargetRuspini(List<List<Integer>> records) {
        targetRuspini = new int[dataRuspini.length];
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).get(2) == 1 || records.get(i).get(2) == 2) {
                targetRuspini[i] = 0;
            } else {
                targetRuspini[i] = 1;
            }
        }
    }

    public int[][] getInput() {
        return input;
    }

    public int[][] getDataRuspini() {
        return dataRuspini;
    }

    public int[] getTargetAND() {
        return targetAND;
    }

    public int[] getTargetOR() {
        return targetOR;
    }

    public int[] getTargetRuspini() {
        return targetRuspini;
    }

    public void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

}
