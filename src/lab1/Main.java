package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    private static Random generator = new Random(1024);

    public static void main(String[] args) {

        int[] layers = {8,3,8};
        boolean[] biases = {true, true, false};
        LearningMethod bp = new Backpropagation();
        NeuralNet nn = new NeuralNet(layers, biases, bp);
        nn.printNet();
        nn.printWeights();


//        ArrayList<ArrayList<double[]>> data = getData(40);
//        for(int i = 0; i<data.size(); i++) {
//            System.out.println(Arrays.toString(data.get(i).get(0)));
//            System.out.println(Arrays.toString(data.get(i).get(1)));
//        }
        // write your code here
    }

    public static ArrayList getData(int numInstances) {
        ArrayList data = new ArrayList<ArrayList<double[]>>();
        for(int i = 0; i<numInstances; i++) {
            int random = (int)Math.floor(generator.nextInt(8));
//            System.out.println(random);
            double[] instance = {0,0,0,0,0,0,0,0};
            instance[random] = 1;
            ArrayList dataPoint = new ArrayList<double[]>();
            dataPoint.add(instance);
            dataPoint.add(instance);
            data.add(dataPoint);
        }
        return data;
    }
}
