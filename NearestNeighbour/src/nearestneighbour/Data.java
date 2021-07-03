package nearestneighbour;

import java.util.List;

public class Data {

    public int dataSet[][];
    public int dataTraining[][];
    public int dataTesting[][];
    private final int[] indexTerakhir = new int[5];
    private final int[][] banyakLabel = new int[5][2];
    public int labelTraining[];
    public int labelTesting[];
    
    public Data(List<List<Integer>> records, int splitRatio) {
        setDataSet(records);
        banyakLabel();
        setDataTraining(dataSet, splitRatio);
        setDataTesting(dataSet, splitRatio);
    }

    public void setDataSet(List<List<Integer>> records) {
        dataSet = new int[records.size()][];
        for (int i = 0; i < dataSet.length; i++) {
            dataSet[i] = new int[records.get(i).size()];
        }
        for (int i = 0; i < records.size(); i++) {
            for (int j = 0; j < records.get(i).size(); j++) {
                dataSet[i][j] = records.get(i).get(j);
            }
        }
    }

    public void banyakLabel() {
        int j = 1;

        for (int i = 1; i < dataSet.length; i++) {
            if (dataSet[i][2] != dataSet[i - 1][2]) {
                indexTerakhir[j] = i;
                j++;
            } else if (i == (dataSet.length - 1)) {
                indexTerakhir[j] = i + 1;
            }
        }
        for (int i = 0; i < indexTerakhir.length - 1; i++) {
            banyakLabel[i][0] = dataSet[indexTerakhir[i]][2];
            banyakLabel[i][1] = indexTerakhir[i + 1] - indexTerakhir[i];
        }
        
    }

    public void setDataTraining(int[][] dataSet, int splitRatio) {
        int k = 0;
        int batasAkhir;
        float ratio = (splitRatio / 100f);
        dataTraining = new int[Math.round(dataSet.length * ratio)][3];

        for (int m = 0; m < indexTerakhir.length; m++) {
            batasAkhir = (indexTerakhir[m] + Math.round(banyakLabel[m][1] * ratio));
            for (int i = 0; i < dataSet.length; i++) {
                if (i >= indexTerakhir[m] && i < batasAkhir) {
                    for (int j = 0; j < dataSet[i].length; j++) {
                        dataTraining[k][j] = dataSet[i][j];
                    }
                    k++;
                }
            }
        }
        labelTraining = new int[dataTraining.length];
        for (int i = 0; i < labelTraining.length; i++) {
            labelTraining[i] = dataTraining[i][2];
        }
    }

    public void setDataTesting(int[][] dataSet, int splitRatio) {
        int k = 0;
        int batasAwal;
        float ratio = splitRatio / 100f;
        dataTesting = new int[Math.round(dataSet.length * (1 - ratio))][3];

        for (int m = 1; m < indexTerakhir.length; m++) {
            batasAwal = (indexTerakhir[m - 1] + Math.round(banyakLabel[m - 1][1] * ratio));
            for (int i = 0; i < dataSet.length; i++) {
                if (i >= batasAwal && i < indexTerakhir[m]) {
                    for (int j = 0; j < dataSet[i].length; j++) {
                        dataTesting[k][j] = dataSet[i][j];
                    }
                    k++;
                }
            }
        }
        labelTesting = new int[dataTesting.length];
        for (int i = 0; i < labelTesting.length; i++) {
            labelTesting[i] = dataTesting[i][2];
        }
    }
}
