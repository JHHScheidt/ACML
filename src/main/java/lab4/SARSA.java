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
    public int[][][] e;
    private Random random = new Random(1024);

    public SARSA(double MINPOS, double MAXPOS, double POSSTEP, int numActions, double MINVEL, double MAXVEL, double VELSTEP) {
//        int numQVals = new Double(((MAXPOS-MINPOS)/POSSTEP) * numActions * ((MAXVEL-MINVEL)/VELSTEP)).intValue();
        QVals = new double[(int)((MAXPOS-MINPOS)/POSSTEP)][numActions][(int)((MAXVEL-MINVEL)/VELSTEP)];
        e = new int[(int)((MAXPOS-MINPOS)/POSSTEP)][numActions][(int)((MAXVEL-MINVEL)/VELSTEP)];
        for(int i =0; i<QVals.length; i++) {
            for(int j = 0; j<QVals[i].length; j++) {
                for(int k = 0; k<QVals[i][j].length; k++) {
                    QVals[i][j][k] = random.nextDouble()*2-1;
                    e[i][j][k] = 0;
                }
            }
        }
    }

    public int getPositionIndex(double position) {
        return (int)(position*2000+2400);
    }

    public int getVelocityIndex(double velocity) {
        return (int)(velocity*2000+140);
    }

    public double getQVals(double position, int action, double velocity) {
        return QVals[getPositionIndex(position)][action][getVelocityIndex(velocity)];
    }

    public double getEVals(double position, int action, double velocity) {
        return e[getPositionIndex(position)][action][getVelocityIndex(velocity)];
    }

    public void setQVals(double position, int action, double velocity, double QVal) {
        QVals[getPositionIndex(position)][action][getVelocityIndex(velocity)] = QVal;
    }

    public void setEVals(double position, int action, double velocity) {
        e[getPositionIndex(position)][action][getVelocityIndex(velocity)] += 1;
    }

}
