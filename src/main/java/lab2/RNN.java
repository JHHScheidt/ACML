package lab2;

import org.deeplearning4j.nn.conf.layers.BaseRecurrentLayer;
import org.deeplearning4j.nn.conf.layers.GravesLSTM;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class RNN {

    RNN() {
        String[] data = importData();
        GravesLSTM LSTM = new GravesLSTM();
    }

    private String[] importData() {
        ArrayList<String> names = new ArrayList<>();

        try{
            Scanner in = new Scanner(new FileReader("data"+ File.separator+"boyNames.txt"));
            while(in.hasNext()) {
                names.add(in.nextLine());
            }
            System.out.println(names.size());
            for(int i = 0; i<(names.size()>10?10:0); i++) {
                System.out.println(names.get(i));
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("error");
        }
        String[] results = new String[names.size()];
        results = names.toArray(results);

        return results;
    }
}
