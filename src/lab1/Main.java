package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    private static Random generator = new Random(1024);

    public static void main(String[] args) {
        //Initialise the number of layers and number of nodes in this layer here
        //For our assignment we were using 3 layers with 8, 3, 8 nodes respectively
        int[] layers = {8,3,8};
        //Set the biases for every layer output layer is always false
        //The bias is always 1
        boolean[] biases = {true, true, false};
        //Set your learningRate and number of iterations for the learning
        double learningRate = 0.9;
        int iterations = 1000;
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
        //nn.printOutputValue();
        //The following print out will print the value on any layer you input
        //nn.printLayerValues(1);
        //The following allows the weights to be printed per requested edge layer
        //nn.printLayerWeights(1);
        //To run a test example through the network with learned weights one can call 
        //the validation method with any number of test instances this will return
        //a double array that includes the total error for each instance
        double[] errors = nn.validation(getDataLab1());
        //These errors can be used to find out what combination of learningrate and iterations
        //gives you the steepest decline. This is what we tried to find out in the for loop later
        //in this method
        
        
        
//        This was used for the calculation of the error and to see what would be
//        the ideal learningrate and number of iterations
//        for(int j = 10000; j <= 100000; j += 10000){
//            iterations = j;
//            for(int i = 11; i < 21; i++){
//                learningRate = 0.1*i;
//                NeuralNet nn = new NeuralNet(layers, biases, bp);
//                nn.setData(getDataLab1());
//                nn.learn(learningRate, iterations);
//                double[] errors = nn.validation(getDataLab1());
//                double accuError = 0;
//                for(int k = 0; k < errors.length; k++){
//                    accuError += errors[k];
//                }
//                System.out.print(accuError + "; ");
//                
//            }
//            System.out.println("");
//        }
    }
    /**
     * These methods show how to set your data. We encapsulate the total number of
     * instances by an arraylist to hold all the instances. Within this arraylist each instance
     * has its own arraylist that consists of 2 double arrays on the first index (0) will always
     * be the input the second index (1) will always be the output
     * @return 
     */
    public static ArrayList getOtherData(){
        ArrayList<ArrayList<double[]>> data = new ArrayList<>();
        ArrayList d1, d2, d3, d4, d5, d6, d7, d8;
        
        d1 = new ArrayList<double[]>();
        double[] temp1 = {1,1,1,1,1,1,1,2};
        d1.add(temp1);
        d1.add(temp1);
        data.add(d1);
        
        d2 = new ArrayList<double[]>();
        double[] temp2 = {1,1,1,1,1,1,2,1};
        d2.add(temp2);
        d2.add(temp2);
        data.add(d2);
        
        d3 = new ArrayList<double[]>();
        double[] temp3 = {1,1,1,1,1,2,1,1};
        d3.add(temp3);
        d3.add(temp3);
        data.add(d3);

        d4 = new ArrayList<double[]>();
        double[] temp4 = {1,1,1,1,2,1,1,1};
        d4.add(temp4);
        d4.add(temp4);
        data.add(d4);

        d5 = new ArrayList<double[]>();
        double[] temp5 = {1,1,1,2,1,1,1,1};
        d5.add(temp5);
        d5.add(temp5);
        data.add(d5);

        d6 = new ArrayList<double[]>();
        double[] temp6 = {1,1,2,1,1,1,1,1};
        d6.add(temp6);
        d6.add(temp6);
        data.add(d6);

        d7 = new ArrayList<double[]>();
        double[] temp7 = {1,2,1,1,1,1,1,1};
        d7.add(temp7);
        d7.add(temp7);
        data.add(d7);

        d8 = new ArrayList<double[]>();
        double[] temp8 = {2,1,1,1,1,1,1,1};
        d8.add(temp8);
        d8.add(temp8);
        data.add(d8);
        
        return data;        
    }
    public static ArrayList getDataLab1(){
        ArrayList<ArrayList<double[]>> data = new ArrayList<>();
        ArrayList d1, d2, d3, d4, d5, d6, d7, d8;
        
        d1 = new ArrayList<double[]>();
        double[] temp1 = {0,0,0,0,0,0,0,1};
        d1.add(temp1);
        d1.add(temp1);
        data.add(d1);
        
        d2 = new ArrayList<double[]>();
        double[] temp2 = {0,0,0,0,0,0,1,0};
        d2.add(temp2);
        d2.add(temp2);
        data.add(d2);
        
        d3 = new ArrayList<double[]>();
        double[] temp3 = {0,0,0,0,0,1,0,0};
        d3.add(temp3);
        d3.add(temp3);
        data.add(d3);

        d4 = new ArrayList<double[]>();
        double[] temp4 = {0,0,0,0,1,0,0,0};
        d4.add(temp4);
        d4.add(temp4);
        data.add(d4);

        d5 = new ArrayList<double[]>();
        double[] temp5 = {0,0,0,1,0,0,0,0};
        d5.add(temp5);
        d5.add(temp5);
        data.add(d5);

        d6 = new ArrayList<double[]>();
        double[] temp6 = {0,0,1,0,0,0,0,0};
        d6.add(temp6);
        d6.add(temp6);
        data.add(d6);

        d7 = new ArrayList<double[]>();
        double[] temp7 = {0,1,0,0,0,0,0,0};
        d7.add(temp7);
        d7.add(temp7);
        data.add(d7);

        d8 = new ArrayList<double[]>();
        double[] temp8 = {1,0,0,0,0,0,0,0};
        d8.add(temp8);
        d8.add(temp8);
        data.add(d8);

        return data;
    }
    
    public static ArrayList getRandomData(int numInstances) {
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
