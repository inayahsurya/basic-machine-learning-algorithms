package wordmatching;

import java.util.Scanner;

/**
 *
 * @author inayah
 */
public class Main {

    public static void main(String[] args) {
        WordMatching wordmatching = new WordMatching();
        Scanner scan = new Scanner(System.in);
        
        System.out.println("---WORD MATCHING---");
        wordmatching.setTarget();
        System.out.print("Banyak generasi : ");
        int generation = scan.nextInt();
        wordmatching.setIndividu();
        System.out.println();
        
        int iteration = 0;
        while (iteration < generation) {
            wordmatching.parentRoullete();
            wordmatching.crossOver();
            wordmatching.mutation();
            System.out.println("Generasi : " + (iteration+1));
            wordmatching.elitism();
            iteration++;
        }
        wordmatching.printResult();

    }

}
