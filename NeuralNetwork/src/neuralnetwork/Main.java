package neuralnetwork;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author inayah
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Data input = new Data();
        NeuralNetwork nn = new NeuralNetwork();
        Scanner scan = new Scanner(System.in);

        System.out.println("Data : ");
        System.out.println("1. AND");
        System.out.println("2. OR");
//        System.out.println("3. Ruspini");
        System.out.print("pilihan : ");
        String chooseData = scan.nextLine();

        switch (chooseData) {
            case "1":
                nn.setInputSequence(input.getInput());
                nn.setTarget(input.getTargetAND());
                nn.randomWeight();
                nn.summation();
                break;
            case "2":
                nn.setInputSequence(input.getInput());
                nn.setTarget(input.getTargetOR());
                nn.randomWeight();
                nn.summation();
                break;
//            case "3":
//                FileConvert file = new FileConvert();
//                file.readFile();
//                input.setDataRuspini(file.getRecords());
////                input.printArray(input.getDataRuspini());
//                nn.setInputSequence(input.getDataRuspini());
//                nn.setTarget(input.getTargetRuspini());
//                nn.setEpoch();
//                nn.randomWeight();
//                nn.summationRuspini();
//                nn.dataTestingSummation();
//                break;
        }

    }

}
