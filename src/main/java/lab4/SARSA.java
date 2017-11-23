/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Marciano & Joshua
 */
public class SARSA {

    public double[][][] QVals;
    private Random random = new Random(1024);

    public SARSA(double MINPOS, double MAXPOS, double POSSTEP, int numActions, double MINVEL, double MAXVEL, double VELSTEP) {
//        int numQVals = new Double(((MAXPOS-MINPOS)/POSSTEP) * numActions * ((MAXVEL-MINVEL)/VELSTEP)).intValue();
        QVals = new double[(int)((MAXPOS-MINPOS)/POSSTEP)][numActions][(int)((MAXVEL-MINVEL)/VELSTEP)];

        for(int i =0; i<QVals.length; i++) {
            for(int j = 0; j<QVals[i].length; j++) {
                for(int k = 0; k<QVals[i][j].length; k++) {
                    QVals[i][j][k] = random.nextDouble()*2-1;
                }
            }
        }
    }

    public void learning() {

    }

    public void getVals(double position, int action, double velocity) {

    }

}
