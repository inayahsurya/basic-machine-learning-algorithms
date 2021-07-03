package neuralnetwork;

import java.util.Random;
import java.util.Scanner;

public class NeuralNetwork {

    private int inputSequence[][];
    private int target[];
    private float weight[];
    private int epoch;
    private int output[];
    private final float treshold = 0;

    public void setInputSequence(int[][] inputSequence) {
        this.inputSequence = inputSequence;
    }

    public void setTarget(int[] target) {
        this.target = target;
    }

    public void setEpoch() {
        Scanner scan = new Scanner(System.in);
        System.out.print("epoch = ");
        epoch = Integer.parseInt(scan.nextLine());
    }

    public void randomWeight() {
        weight = new float[inputSequence[0].length];
        for (int i = 0; i < weight.length; i++) {
            weight[i] = getRandom(-1,1);
        }
    }
    
    public int getRandom(int min, int max){
        return (int) (Math.random()* ((max-min)+1) + min);
    }

    public void summation() {
        float sum = 0;
        float error;
        output = new int[inputSequence.length];
        int iterasi = 0;
        do {
            for (int i = 0; i < inputSequence.length; i++) {
                for (int j = 0; j < inputSequence[j].length; j++) {
                    sum += inputSequence[i][j] * weight[j];
                }
                if (sum < treshold) {
                    output[i] = 0;
                } else {
                    output[i] = 1;
                }
                if(target[i] != output[i]){
                    error = target[i] - output[i];
                    singlePerceptronLearning(i, error);
                }
            }
            iterasi++;
        } while (check());
        printResult2(iterasi, output);
    }

    public void summationRuspini() {
        double sum = 0;
        float error;
        output = new int[inputSequence.length];
        for(int k=0; k<epoch; k++){
            for (int i = 0; i < inputSequence.length; i++) {
                for (int j = 0; j < inputSequence[j].length; j++) {
                    sum = sum + inputSequence[i][j] * weight[j];
                }
                System.out.print(sum + " ");
                if (sum < treshold) {
                    output[i] = 0;
                } else {
                    output[i] = 1;
                }
                
                error = target[i] - output[i];
                if(target[i] != output[i]){
                    singlePerceptronLearning(i, error);
                }
            }
            System.out.println();
            if(check()==false)
                break;
        }
        printResult(output);
    }

    public void singlePerceptronLearning(int i, float error) {     
        float miu = (float) 0.1;
            for (int j = 0; j < weight.length; j++) {
                weight[j] = weight[j] + (miu * inputSequence[i][j] * error);
            }
        
    }

    public void dataTestingSummation() {
        int dataTesting[] = new int[inputSequence[0].length];
        int output;
        Scanner scan = new Scanner(System.in);
        String line;
        String lineSplit[];
        String lagi = "y";
        float sum = 0;

        System.out.println("\n---DATA TRAINING---");
        do {
            System.out.print("Data training (x,y) = ");
            line = scan.nextLine();
            lineSplit = line.split(",");
            dataTesting[0]=1;
            for (int i = 1; i < dataTesting.length; i++) {
                dataTesting[i] = Integer.parseInt(lineSplit[i]);
            }

            
            if (sum < treshold) {
                output = 0;
            } else {
                output = 1;
            }
            for (int i = 0; i < dataTesting.length; i++) {
                System.out.print(dataTesting[i] + " ");
            }
            System.out.println("= " + output);
            System.out.print("Apakah anda ingin mencoba data lain?(y/n) ");
            lagi = scan.nextLine();
        } while (lagi.equalsIgnoreCase("y"));

    }

    public boolean check() {
        for (int i = 0; i < target.length; i++) {
            if (target[i] != output[i]) {
                return true;
            }
        }
        return false;
    }

    public void printResult(int[] output) {
        System.out.println("\n---HASIL NEURAL NETWORK---");
        System.out.println("Iterasi : " + epoch);
        for (int k = 0; k < inputSequence.length; k++) {
            for (int j = 0; j < inputSequence[k].length; j++) {
                System.out.print(inputSequence[k][j] + " ");
            }
            System.out.println("= " + output[k]);
        }
    }

    public void printResult2(int i, int[] output) {
        System.out.println("\n---HASIL NEURAL NETWORK---");
        System.out.println("Iterasi : " + i);
        for (int k = 0; k < inputSequence.length; k++) {
            for (int j = 0; j < inputSequence[k].length; j++) {
                System.out.print(inputSequence[k][j] + " ");
            }
            System.out.println("= " + output[k]);
        }
    }
}
