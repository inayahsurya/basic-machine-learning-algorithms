package optimationkmeans;

import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ScatterPlot extends JFrame {
    XYDataset dataset;

    public ScatterPlot(String title, int[][] centroids, int[][] dataCluster, int k) {
        super(title);

        // Create dataset
        dataset = createDataset(dataCluster, k);

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Clustering data ruspini", "", "", dataset);

        //Changes background color
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(255, 255, 255));

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset(int[][] dataCluster, int k) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series;

        for (int i = 0; i < k; i++) {
            String cluster = "cluster " + (i + 1);
            series = new XYSeries(cluster);
                      
            for (int j = 0; j < dataCluster.length; j++) {
                if (dataCluster[j][2] == i+1) {
                    int x = dataCluster[j][0];
                    int y = dataCluster[j][1];
                    series.add(x, y);
                }
            }
            dataset.addSeries(series);
        }
        return dataset;
    }
    
    private XYDataset createCentroid(int[][] centroids){
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series;
        
        for(int i=0; i<centroids.length;i++){
            String centroid = "centroid " + (i+1);
            series = new XYSeries(centroid);
            
            int x = centroids[i][0];
            int y = centroids[i][1];
            series.add(x,y);
            dataset.addSeries(series);
        }
        
        return dataset;
    }

}
