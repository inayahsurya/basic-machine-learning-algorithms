package neuralnetworkruspini;

import java.io.FileNotFoundException;

/**
 *
 * @author inayah
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        NeuralNetworkRuspini nn = new NeuralNetworkRuspini();
        
        nn.readFile();
        nn.setTarget();
        nn.setEpoch();
        nn.randomWeight();
        nn.summation();
        nn.dataTestingSummation();
    }
    
}
