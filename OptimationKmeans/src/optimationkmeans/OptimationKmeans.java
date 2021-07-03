package optimationkmeans;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class OptimationKmeans {

    public int individu[][];
    public int dataCluster[][];
    public double fitness[];
    public int parent[][];
    public int offSpring[][];
    public int centroids[][];
    public int centroidsTemp[][];
    public int k;
    public int M = 100;
    final double PROB_CONS_CROSSOVER = 0.8;
    final double PROB_CONS_MUTATION = 0.2;
    Scanner scan = new Scanner(System.in);
    Print tes = new Print();

    public void setDataCluster(int[][] dataSet) {
        System.out.print("Banyak cluster \t: ");
        k = scan.nextInt();

        dataCluster = new int[dataSet.length][dataSet[0].length];
        for (int i = 0; i < dataCluster.length; i++) {
            for (int j = 0; j < dataCluster[i].length; j++) {
                dataCluster[i][j] = dataSet[i][j];
            }
        }
    }

    public void setIndividu(int xMax, int yMax) {
        System.out.print("Banyak individu : ");
        int n = scan.nextInt();

        individu = new int[n][k * 2];
        fitness = new double[individu.length];

        for (int i = 0; i < individu.length; i++) {
            for (int j = 0; j < individu[i].length; j += 2) {
                individu[i][j] = getRandomInt(1, xMax);
                individu[i][j + 1] = getRandomInt(1, yMax);
            }
        }
        fitness = fitnessEvaluation(individu);

        tes.printArray(individu);
    }

    public double[] fitnessEvaluation(int[][] individu) {
        double fitness[] = new double[individu.length];
        double distance[] = new double[k];
        double minimumJ[] = new double[individu.length];
        double nearestDistance[] = new double[dataCluster.length];
        for (int i = 0; i < individu.length; i++) {
            for (int j = 0; j < dataCluster.length; j++) {
                int a=0;
                for (int k = 0; k < individu[i].length; k += 2) {
                    int x = Math.abs(dataCluster[j][0] - individu[i][k]);
                    int y = Math.abs(dataCluster[j][1] - individu[i][k + 1]);
                    distance[a] = getDistance(x, y);
                    a++;
                }
                nearestDistance[j] = getNearestDistance(distance);
                minimumJ[i] += nearestDistance[j];
                fitness[i] = M - (minimumJ[i]/dataCluster.length);
            }
        }
//        tes.printArray(minimumJ);
//        tes.printArray(fitness);
        return fitness;
    }
    
    public void parentRoullete() {
        parent = new int[individu.length][individu[0].length];
        int range[] = new int[fitness.length + 1];
        int number[] = new int[individu.length];

        range = getRange(range);
        for (int i = 0; i < number.length; i++) {
            number[i] = getRandomInt(0, range[range.length - 1]);
        }
        
        for (int i = 0; i < parent.length; i++) {
            int j = 1;
            for (int index = 0; index < individu.length; index++) {
                if (number[i] > range[j - 1] && number[i] <= range[j]) {
                    parent[i] = individu[index];
                }
                j++;
            }
        }
        tes.printArray(parent);
    }

    public void crossOver() {
        offSpring = new int[parent.length][parent[0].length];
        double p;
        int batasKiri, batasKanan;

        for (int i = 0; i < offSpring.length; i++) {
            for (int j = 0; j < offSpring[i].length; j++) {
                offSpring[i][j] = parent[i][j];
            }
        }
        for (int i = 0; i < parent.length; i += 2) {
            p = Math.random();
            if (p <= PROB_CONS_CROSSOVER) {
                batasKiri = getRandomInt(0, parent[i].length - 2);
                batasKanan = getRandomInt(batasKiri + 1, parent[i].length - 1);
                for (int j = batasKiri; j <= batasKanan; j++) {
                    offSpring[i][j] = parent[i + 1][j];
                    offSpring[i + 1][j] = parent[i][j];
                }
            }
        }
        tes.printArray(offSpring);
    }

    public void mutation(int xMax, int yMax) {
        double p;
        int index, rand;
        int mutValue = 2;
        for (int i = 0; i < offSpring.length; i++) {
            p = Math.random();
            if (p <= PROB_CONS_MUTATION) {
                index = getRandomInt(0, offSpring[i].length - 1);
                rand = getRandomInt(1, 2);
//                System.out.println(index + " " + rand);
                if (rand == 1) {
                    if((index+1)%2!=0 && offSpring[i][index] != xMax){
//                        System.out.println("ganjil +");
                        offSpring[i][index] += mutValue;
                    }
                    else if((index+1)%2==0 && offSpring[i][index] != yMax){
//                        System.out.println("genap +");
                        offSpring[i][index] += mutValue;
                    }
                    
                }
                if (rand == 2) {
                    if(offSpring[i][index] != 1){
                        offSpring[i][index] -= mutValue;
                    }
                }
            }
        }
        tes.printArray(offSpring);
    }

    public void elitism() {
        double offSpringFitness[] = new double[offSpring.length];
        double parentFitness[] = new double[parent.length];
        double population[][] = new double[individu.length * 2][3];

        offSpringFitness = fitnessEvaluation(offSpring);
        System.out.println("ofspring fit");
        tes.printArray(offSpringFitness);
        
        parentFitness = fitnessEvaluation(parent);
        System.out.println("parent fit");
        tes.printArray(parentFitness);
        for (int i = 0; i < parent.length; i++) {
            population[i][0] = parentFitness[i];
            population[i][1] = i;
            population[i][2] = i;
        }
        for (int i = offSpring.length; i < offSpring.length * 2; i++) {
            population[i][0] = offSpringFitness[i - offSpring.length];
            population[i][1] = i - offSpring.length;
            population[i][2] = i;
        }
        
//        System.out.println("popul sebelum sort");
//        tes.printArray(population);

        Arrays.sort(population, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o2[0], o1[0]);
            }
        });
        
//        System.out.println("popul setelah sort");
//        tes.printArray(population);

        for (int i = 0; i < individu.length; i++) {
            if (population[i][2] < individu.length) {
                individu[i] = parent[(int)population[i][1]];
            }
            if (population[i][2] >= individu.length) {
                individu[i] = offSpring[(int)population[i][1]];
            }
        }
        tes.printArray(individu);
    }
    
    public void clustering() {
        setCentroid();
        do {
            setCentroidsTemp();
            setCluster();
            middlePoint();
        } while (!checkCentroid());
        printResult();
    }
    
    public void setCentroid(){
        centroids = new int[k][2];
        for(int i=0; i<centroids.length;i++){
            for(int j=0; j<individu[0].length;j+=2){
                centroids[i][0] = individu[0][j];
                centroids[i][1] = individu[0][j+1];
            }
        }
    }
    
    public void setCluster() {
        double[] temp = new double[centroids.length];
        for (int i = 0; i < dataCluster.length; i++) {
            for (int j = 0; j < centroids.length; j++) {
                int x = Math.abs(centroids[j][0] - dataCluster[i][0]);
                int y = Math.abs(centroids[j][1] - dataCluster[i][1]);
                temp[j] = getDistance(x, y);
            }
            getNearestDistance(temp);
            for (int h = 0; h < centroids.length; h++) {
                if (temp[h] == getNearestDistance(temp)) {
                    dataCluster[i][2] = h + 1;
                }
            }
        }
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
    
    public int[] getRange(int[] range) {
        range[0] = 0;
        for (int i = 1; i < range.length; i++) {
            range[i] = range[i - 1] + (int) fitness[i - 1];
        }

        return range;
    }

    public float getDistance(int a, int b) {
        return (float) Math.sqrt((Math.pow(a, 2)) + (Math.pow(b, 2)));
    }

    public double getNearestDistance(double distance[]) {
        double min = distance[0];
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] < min) {
                min = distance[i];
            }
        }
        return min;
    }

    public int getRandomInt(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
    
}
