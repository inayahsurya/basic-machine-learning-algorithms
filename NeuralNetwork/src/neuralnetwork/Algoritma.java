package neuralnetwork;

import java.util.Scanner;

public class Algoritma {

    public int dataSet[][]
            = {{1, 0, 0},
            {1, 0, 1},
            {1, 1, 0},
            {1, 1, 1}};
//    public int target[] = {0, 0, 0, 1};    //AND
    public int target[] = {0,1,1,1};  //OR
    public float w[] = new float[dataSet[0].length];
    public float miu = (float) 0.1;
    public float treshold = 0;
    public int epoch;

    public static int getRandom(int min, int max) {
        int x = (int) (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

    public void randW() {
        for (int i = 0; i < w.length; i++) {
            w[i] = getRandom(-1, 1);
        }
    }

    public void entryEpoch() {
        System.out.print("Memasukkan banyak Epoch : ");
        Scanner key = new Scanner(System.in);
        epoch = Integer.parseInt(key.nextLine());
        System.out.println();
    }
    
    public void trainingPerceptron() {
        float perceptron = 0;
        float err;
        float output[] = new float[dataSet.length];
        for (int k = 0; k < epoch; k++) {
            System.out.println("iterasi - " + k);
            for (int i = 0; i < dataSet.length; i++) {
                for (int j = 0; j < w.length; j++) {
                    
                    perceptron = perceptron + (dataSet[i][j] * w[j]);
                    
                }
                
                if (perceptron < treshold) {
                    output[i] = 0;
                } else {
                    output[i] = 1;
                }
                
                System.out.print(output[i] + " ");
                
                err = target[i] - output[i];
                if (target[i] != output[i]) {
                    singlePerceptron(i, err);
                }
            }
            System.out.println();
            if(check(output) == true){
                break;
            }
        }
    }

    public boolean check(float output[]){
        for(int i = 0; i < output.length; i++){
            if(output[i] != target[i]){
                return false;
            }
        }
        return true;
    }
    
    public void singlePerceptron(int i, float err) {
        for (int j = 0; j < w.length; j++) {
            w[j] = w[j] + (miu * dataSet[i][j] * err);
        }
    }
    
    public void cetakTarget(){
        System.out.println("Target : ");
        for(int i = 0; i < target.length; i++){
            System.out.print(target[i]+" ");
        }
        System.out.println();
    }
}
