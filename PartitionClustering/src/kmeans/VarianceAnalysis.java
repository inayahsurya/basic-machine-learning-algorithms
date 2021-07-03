package kmeans;

public class VarianceAnalysis {

    private double vC[];
    private double vW;
    private double vB;
    private double v;
    private int dataCluster[][];
    private int centroids[][];
    private int dataPerCluster[];

    public VarianceAnalysis(int[][] dataCluster, int[][] centroids) {
        this.dataCluster = new int[dataCluster.length][3];
        this.dataCluster = dataCluster.clone();
        this.centroids = new int[centroids.length][3];
        this.centroids = centroids.clone();
        dataPerCluster();
    }

    public void dataPerCluster() {
        dataPerCluster = new int[centroids.length];
        for (int i = 0; i < centroids.length; i++) {
            for (int j = 0; j < dataCluster.length; j++) {
                if (dataCluster[j][2] == i + 1) {
                    dataPerCluster[i]++;
                }
            }
        }
    }

    public void varianceCluster() {
        vC = new double[centroids.length];
        for (int i = 0; i < centroids.length; i++) {
            double d = 0;
            int n = 0;
            for (int j = 0; j < dataCluster.length; j++) {
                if (dataCluster[j][2] == i + 1) {
                    int dX = dataCluster[j][0] - centroids[i][0];
                    int dY = dataCluster[j][1] - centroids[i][1];
                    d += (Math.pow(dX, 2) + Math.pow(dY, 2));
                    n++;
                }
            }
            vC[i] = d / (n - 1);
//            System.out.println(vC[i]);
        }
    }

    public void varianceWithin() {
        double n = 0;
        for (int i = 0; i < centroids.length; i++) {
            n += ((dataPerCluster[i] - 1) * vC[i]);
        }
        vW = n / (dataCluster.length - centroids.length);
        System.out.println("Variance within \t= " + vW);
    }

    public int[] mean(int[][] data) {
        int sum[] = new int[data.length];
        int sumX = 0;
        int sumY = 0;
        for (int i = 0; i < data.length; i++) {
            sumX += data[i][0];
            sumY += data[i][1];
        }
        sum[0] = sumX / data.length;
        sum[1] = sumY / data.length;

        return sum;
    }

    public void varianceBetween() {
        int c = 0;
        int meanCentroid[] = new int[2];
        meanCentroid = mean(centroids);
        for (int i = 0; i < centroids.length; i++) {
            int cX = centroids[i][0] - meanCentroid[0];
            int cY = centroids[i][1] - meanCentroid[1];
            c += (dataPerCluster[i] * ((Math.pow(cX, 2) + Math.pow(cY, 2))));
        }
        vB = c / (centroids.length - 1);
        System.out.println("variance between \t= " + vB);
    }

    public void variance() {
        v = vW / vB;
//        System.out.println(v);
    }

    public void printAnalysis() {
        System.out.printf("Hasil variance \t= %.4f \n", v);
    }
}
