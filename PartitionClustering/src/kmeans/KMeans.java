package kmeans;

import java.util.Random;
import java.util.Scanner;

public class KMeans {

    private int centroids[][];
    private int centroidsTemp[][];
    private int k;
    private int dataCluster[][];

    public void setK() {
        Scanner str = new Scanner(System.in);
        System.out.print("Jumlah cluster : ");
        k = str.nextInt();
    }

    public void setCentroids(int dimensi, int k) {
        centroids = new int[k][dimensi];
    }

    public void setDataCluster(int[][] dataSet) {
        dataCluster = new int[dataSet.length][];
        for (int i = 0; i < dataCluster.length; i++) {
            dataCluster[i] = new int[dataSet[i].length];
            for (int j = 0; j < dataCluster[i].length; j++) {
                dataCluster[i][j] = dataSet[i][j];
            }
        }
    }

    public void randomCentroids(int xMax, int yMax) {
        Random random = new Random();

        for (int i = 0; i < centroids.length; i++) {
            centroids[i][0] = random.nextInt(xMax) + 1;
            centroids[i][1] = random.nextInt(yMax) + 1;
        }
    }

    public void setCluster() {
        float[] temp = new float[centroids.length];
        for (int i = 0; i < dataCluster.length; i++) {
            for (int j = 0; j < centroids.length; j++) {
                int x = Math.abs(centroids[j][0] - dataCluster[i][0]);
                int y = Math.abs(centroids[j][1] - dataCluster[i][1]);
                temp[j] = distance(x, y);
            }
            nearestDistance(temp);
            for (int h = 0; h < centroids.length; h++) {
                if (temp[h] == nearestDistance(temp)) {
                    dataCluster[i][2] = h + 1;
                }
            }
        }
    }

    public float distance(int a, int b) {
        return (float) Math.sqrt((Math.pow(a, 2)) + (Math.pow(b, 2)));
    }

    public float nearestDistance(float distance[]) {
        float min = distance[0];
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] < min) {
                min = distance[i];
            }
        }
        return min;
    }

    public void middlePoint() {

        for (int i = 0; i < k; i++) {
            int sum = 0;
            float sumX = 0;
            float sumY = 0;

            for (int j = 0; j < dataCluster.length; j++) {
                if (dataCluster[j][2] == i + 1) {
                    sumX += dataCluster[j][0];
                    sumY += dataCluster[j][1];
                    sum++;
                }
            }
            if (sum != 0) {
                centroids[i][0] = Math.round(sumX / sum);
                centroids[i][1] = Math.round(sumY / sum);
            }
        }
    }

    public void setCentroidsTemp() {
        centroidsTemp = new int[centroids.length][];
        for (int i = 0; i < centroidsTemp.length; i++) {
            centroidsTemp[i] = new int[centroids[i].length];
            for (int j = 0; j < centroidsTemp[i].length; j++) {
                centroidsTemp[i][j] = centroids[i][j];
            }
        }
    }

    public void clustering(int[][] dataSet) {
        setDataCluster(dataSet);
        do {
            setCentroidsTemp();
            setCluster();
            middlePoint();
        } while (!checkCentroid());
        printResult();
    }

    public boolean checkCentroid() {
        for (int i = 0; i < centroids.length; i++) {
            for (int j = 0; j < centroids[i].length; j++) {
                if (centroids[i][j] != centroidsTemp[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printResult() {
        System.out.println("Centroid : ");
        for (int i = 0; i < centroids.length; i++) {
            System.out.print((i + 1) + ". ");
            for (int j = 0; j < centroids[i].length; j++) {
                System.out.print(centroids[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Hasil Clustering");
        for (int i = 0; i < dataCluster.length; i++) {
            System.out.print((i + 1) + ". ");
            for (int j = 0; j < dataCluster[i].length; j++) {
                System.out.print(dataCluster[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getK() {
        return k;
    }

    public int[][] getDataCluster() {
        return dataCluster;
    }

    public int[][] getCentroids() {
        return centroids;
    }
    
    
}
