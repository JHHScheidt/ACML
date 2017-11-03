package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    private static Random generator = new Random(1024);

    public static void main(String[] args) {

        int[] layers = {8,3,8};
        boolean[] biases = {true, true, false};
//        int[] layers = {1,6,5,4,3,2,1};
//        boolean[] biases = {false,false,false,false,false,false,false};
        LearningMethod bp = new Backpropagation();
        NeuralNet nn = new NeuralNet(layers, biases, bp);
        nn.printNet();
        nn.printWeights();
        nn.setData(getDataLab1());
//        nn.setData(getOtherData());
        nn.learn();
        nn.printWeights();
        nn.printOutputValue();

//        double[] test = {1,1,1,1,1,1,1,2};
        double[] test = {0,0,0,0,0,0,0,1};
        nn.predict(test);

//        ArrayList<ArrayList<double[]>> data = getData(40);
//        for(int i = 0; i<data.size(); i++) {
//            System.out.println(Arrays.toString(data.get(i).get(0)));
//            System.out.println(Arrays.toString(data.get(i).get(1)));
//        }
        // write your code here
    }
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
