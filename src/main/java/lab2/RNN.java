package lab2;

import org.deeplearning4j.nn.conf.layers.BaseRecurrentLayer;
import org.deeplearning4j.nn.conf.layers.GravesLSTM;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class RNN {

    RNN() {
        importData();

    }

    private Object[] importData() {

        try{
            Scanner in = new Scanner(new FileReader("boyNames.txt"));
        }
        catch(FileNotFoundException e) {
            System.out.println("error");
        }

        return new Object[]{};
    }
}
