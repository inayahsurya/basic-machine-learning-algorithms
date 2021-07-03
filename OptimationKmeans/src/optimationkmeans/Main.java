/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimationkmeans;

import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author Inayah
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        FileConvert ruspini = new FileConvert();
        ruspini.readFile();

        Data data = new Data();
        data.setDataSet(ruspini.getRecords());

        System.out.print("Banyak generasi : ");
        int generation = scan.nextInt();

        OptimationKmeans optimationKmeans = new OptimationKmeans();
        optimationKmeans.setDataCluster(data.getDataSet());
        optimationKmeans.setIndividu(data.getxMax(), data.getyMax());

        int iteration = 0;
        while (iteration < generation) {
            System.out.println("rolet");
            optimationKmeans.parentRoullete();
            System.out.println("crosover");
            optimationKmeans.crossOver();
            System.out.println("mutasi");
            optimationKmeans.mutation(data.getxMax(), data.getyMax());
            System.out.println("elitism");
            System.out.println("Generasi : " + iteration);
            optimationKmeans.elitism();
            iteration++;
        }

        optimationKmeans.clustering();

        SwingUtilities.invokeLater(() -> {
            ScatterPlot plot = new ScatterPlot("Partition Clustering | KMeans",
                    optimationKmeans.centroids, optimationKmeans.dataCluster,
                    optimationKmeans.k);
            plot.setSize(800, 400);
            plot.setLocationRelativeTo(null);
            plot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            plot.setVisible(true);
        });

    }

}
