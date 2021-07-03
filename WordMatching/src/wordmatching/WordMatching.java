package wordmatching;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class WordMatching {

    int MAX;
    final double PROB_CONS_CROSSOVER = 0.9;
    final double PROB_CONS_MUTATION = 0.1;
    Scanner scan = new Scanner(System.in);
    Random random = new Random();
    int fitness[];
    int target[];
    int individu[][];
    int parent[][];
    int offSpring[][];

    public void setTarget() {
        System.out.print("Target kata \t: ");
        String str = scan.nextLine();
        char sTarget[] = str.toCharArray();
        target = new int[sTarget.length];
        for (int i = 0; i < sTarget.length; i++) {
            target[i] = (int) sTarget[i] - 96;
        }
    }

    public void setIndividu() {
        System.out.print("Banyak individu : ");
        int n = scan.nextInt();
        MAX = n * 26;

        individu = new int[n][target.length];
        fitness = new int[individu.length];
        for (int i = 0; i < individu.length; i++) {
            for (int j = 0; j < individu[i].length; j++) {
                individu[i][j] = getRandomInt(1, 26);
            }
        }
        fitness = getFitness(individu);
    }

    public int[] getFitness(int[][] individu) {
        int fitness[] = new int[individu.length];
        for (int i = 0; i < individu.length; i++) {
            for (int j = 0; j < individu[i].length; j++) {
                fitness[i] += Math.abs(target[j] - individu[i][j]);
            }
            fitness[i] = MAX - fitness[i];
        }
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
    }

    public void mutation() {
        double p;
        int index, rand;
        int mutValue = 1;
        for (int i = 0; i < offSpring.length; i++) {
            p = Math.random();
            if (p <= PROB_CONS_MUTATION) {
                index = getRandomInt(0, offSpring[i].length - 1);
                rand = getRandomInt(1, 2);
                if (rand == 1 && offSpring[i][index] != 26) {
                    offSpring[i][index] += mutValue;
                }
                if (rand == 2 && offSpring[i][index] != 1) {
                    offSpring[i][index] -= mutValue;
                }
            }
        }
    }

    public void elitism() {
        int offSpringFitness[] = new int[offSpring.length];
        int parentFitness[] = new int[parent.length];
        int population[][] = new int[individu.length * 2][3];

        offSpringFitness = getFitness(offSpring);
        parentFitness = getFitness(parent);
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

        Arrays.sort(population, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0], o1[0]);
            }
        });

        for (int i = 0; i < individu.length; i++) {
            if (population[i][2] < individu.length) {
                individu[i] = parent[population[i][1]];
            }
            if (population[i][2] >= individu.length) {
                individu[i] = offSpring[population[i][1]];
            }
        }
        printArrayToString(individu);
    }

    public int[] getRange(int[] range) {
        range[0] = 0;
        for (int i = 1; i < range.length; i++) {
            range[i] = range[i - 1] + fitness[i - 1];
        }

        return range;
    }

    public int getRandomInt(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public void printResult() {
        char sBestIndividu[] = new char[target.length];

        System.out.print("Individu terbaik : ");
        for (int i = 0; i < sBestIndividu.length; i++) {
            sBestIndividu[i] = (char) (individu[0][i] + 96);
            System.out.print(sBestIndividu[i] + "");
        }
        System.out.println();

    }

    public void printArrayToString(int[][] array) {
        char cArray[][] = new char[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                cArray[i][j] = (char) (array[i][j] + 96);
                System.out.print(cArray[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
