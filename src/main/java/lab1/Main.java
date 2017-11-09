package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * The main class for testing the neural network and the backpropagation algorithm.
 *
 * @author Marciano Geijselaers
 * @author Joshua Scheidt
 */
public class Main {

    /** The random number generator with a set seed of 1024 */
    private static Random generator = new Random(1024);

    /**
     * The main method which is used to run the application.
     *
     * @param args The initial arguments to run the application. In this case, leave the arguments empty since they will not be used
     */
    public static void main(String[] args) {
        //Initialise the number of layers and number of nodes in this layer here
        //For our assignment we were using 3 layers with 8, 3, 8 nodes respectively
        int[] layers = {8,3,8};
        //Set the biases for every layer output layer is always false
        //The bias is always 1
        boolean[] biases = {true, true, false};
        //Set your learningRate and number of iterations for the learning
        double learningRate = 1.7;
        int iterations = 5000;
        //An interface was implemented to allow introduction of another kind of 
        //learning method in the future
        LearningMethod bp = new Backpropagation();
        //This initiates you Neural network
        NeuralNet nn = new NeuralNet(layers, biases, bp);
        //Mostly below statements are for debugging purposes but can also be used
        //to check if there was proper initialisation
        //nn.printNet();
        //nn.printWeights();
        //This sets the data to be used for training
        nn.setData(getDataLab1());
        //This starts the learner
        nn.learn(learningRate, iterations);
        //Here you can see what the output value currently is after a certain learning period
//        nn.printOutputValue();
        //The following print out will print the value on any layer you input
//        nn.printLayerValues(1);
        //The following allows the weights to be printed per requested edge layer
//        nn.printLayerWeights(1);
        //To run a test example through the network with learned weights one can call 
        //the validation method with any number of test instances this will return
        //a double array that includes the total error for each instance
        double[] errors = nn.validation(getDataLab1());
        System.out.println("The errors on the test data:" + Arrays.toString(errors));
        //These errors can be used to find out what combination of learningrate and iterations
        //gives you the steepest decline. This is what we tried to find out in the for loop later
        //in this method

        //Predicting an output for some input
        double[] input = {1,0,0,0,0,0,0,0};
        System.out.println("Predict with input: "+Arrays.toString(input));
        System.out.println("Predicted output: "+Arrays.toString(nn.predict(input)));
        
        
//        This was used for the calculation of the error and to see what would be
//        the ideal learningrate and number of iterations
//        int iterationSet[] = {
//                1,2,3,4,5,6,7,8,9,
//                10,20,30,40,50,60,70,80,90,
//                100,200,300,400,500,600,700,800,900,
//                1000,2000,3000,4000,5000,6000,7000,8000,9000,
//                10000,20000,30000,40000,50000,60000,70000,80000,90000,100000
//        };
//        for(int iterations :iterationSet){ //num iterations
//            for(int i = 1; i < 21; i++){ //Learning rate
//                learningRate = 0.1*i;
//                NeuralNet nn = new NeuralNet(layers, biases, bp);
//                nn.setData(getDataLab1());
//                double start = System.nanoTime();
//                nn.learn(learningRate, iterations);
//                double end = System.nanoTime();
//                double[] errors = nn.validation(getDataLab1());
//                double accuError = 0;
//                for(int k = 0; k < errors.length; k++){
//                    accuError += errors[k];
//                }
//                System.out.print(accuError + "; ");
//                System.out.println("Time needed for "+iterations+" iterations: "+(end-start)+" nanosecond" );
//            }
//            System.out.println("");
//        }
    }

    /**
     * These methods show how to set your data. We encapsulate the total number of
     * instances by an arraylist to hold all the instances. Within this arraylist each instance
     * has its own arraylist that consists of 2 double arrays on the first index (0) will always
     * be the input the second index (1) will always be the output.
     * @return The data
     */
    private static ArrayList<ArrayList<double[]>> getDataLab1(){
        ArrayList<ArrayList<double[]>> data = new ArrayList<>();

        for(int i = 0; i<8; i++) {
            ArrayList<double[]> dataPoint = new ArrayList<>();
            double[] tmp = {0,0,0,0,0,0,0,0};
            tmp[i] = 1;
            dataPoint.add(tmp);
            dataPoint.add(tmp);
            data.add(dataPoint);
        }

        return data;
    }

    /**
     * Provides random data for neural network using a number of instances. We encapsulate the total number of
     * instances by an arraylist to hold all the instances. Within this arraylist each instance
     * has its own arraylist that consists of 2 double arrays on the first index (0) will always
     * be the input the second index (1) will always be the output.
     * @param numInstances The number of instances
     * @return The data
     */
    private static ArrayList getRandomData(int numInstances) {
        ArrayList<ArrayList<double[]>> data = new ArrayList<>();
        for(int i = 0; i<numInstances; i++) {
            int random = (int)Math.floor(generator.nextInt(8));
//            System.out.println(random);
            double[] instance = {0,0,0,0,0,0,0,0};
            instance[random] = 1;
            ArrayList<double[]> dataPoint = new ArrayList<>();
            dataPoint.add(instance);
            dataPoint.add(instance);
            data.add(dataPoint);
        }
        return data;
    }
}
