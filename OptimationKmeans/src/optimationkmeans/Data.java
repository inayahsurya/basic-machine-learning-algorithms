
package optimationkmeans;

import java.util.List;

public class Data {

    private int dataSet[][];
    private int xMax;
    private int yMax;
    private int dimensi = 2;
    Print tes = new Print();

    public void setDataSet(List<List<Integer>> records) {
        dataSet = new int[records.size()][dimensi];
        for (int i = 0; i < dataSet.length; i++) {
            dataSet[i] = new int[records.get(i).size()];
        }
        for (int i = 0; i < records.size(); i++) {
            for (int j = 0; j < records.get(i).size(); j++) {
                dataSet[i][j] = records.get(i).get(j);
            }
        }
        max();
        
//        tes.printArray(dataSet);
    }

    public void max() {
        xMax = dataSet[0][0];
        yMax = dataSet[0][1];
        for (int i = 0; i < dataSet.length; i++) {
            for (int j = 0; j < dataSet[i].length; j++) {
                if (xMax < dataSet[i][0]) {
                    xMax = dataSet[i][0];
                }
                if (yMax < dataSet[i][1]) {
                    yMax = dataSet[i][1];
                }
            }
        }
    }

    public int[][] getDataSet() {
        return dataSet;
    }

    public int getxMax() {
        return xMax;
    }

    public int getyMax() {
        return yMax;
    }

    public int getDimensi() {
        return dimensi;
    }
    
}
