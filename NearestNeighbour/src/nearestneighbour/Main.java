
package nearestneighbour;

import java.io.FileNotFoundException;

/**
 *
 * @author inayah
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("K NEAREST NEIGHBORS \n");
        FileConvert file = new FileConvert();
        file.readFile();
        
        KNearestNeighbors knn = new KNearestNeighbors(file.getRecords(), 80, 4);
        knn.print();
//        knn.printAllDistance();
    }
}
