
package kmeans;

import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author inayah
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        FileConvert file = new FileConvert();
        file.readFile();
        
        Data data = new Data();
        data.setDataSet(file.getRecords());
        data.max();
        
        KMeans kmeans = new KMeans();
        kmeans.setK();
        kmeans.setCentroids(data.getDimensi(), kmeans.getK());
        kmeans.randomCentroids(data.getxMax(), data.getyMax());
        kmeans.clustering(data.getDataSet());
        
        SwingUtilities.invokeLater(() -> {
            ScatterPlot plot = new ScatterPlot("Partition Clustering | KMeans",
                kmeans.getDataCluster(), kmeans.getK());
            plot.setSize(800, 400);
            plot.setLocationRelativeTo(null);
            plot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            plot.setVisible(true);
        });
        
        System.out.println();
        VarianceAnalysis analysis = new VarianceAnalysis(kmeans.getDataCluster(),
            kmeans.getCentroids());
        analysis.varianceCluster();
        analysis.varianceWithin();
        analysis.varianceBetween();
        analysis.variance();
        analysis.printAnalysis();
    }
    
}
