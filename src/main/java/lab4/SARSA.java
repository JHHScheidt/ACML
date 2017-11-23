/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.util.Random;

/**
 *
 * @author Marciano & Joshua
 */
public class SARSA {

    public double[][][] QVals;
    public double[][][] e;
    double MINPOS;
    double MAXPOS;
    double POSSTEP;
    int numActions;
    double MINVEL;
    double MAXVEL;
    double VELSTEP;
    private Random random = new Random(1024);

    public SARSA(double MINPOS, double MAXPOS, double POSSTEP, int numActions, double MINVEL, double MAXVEL, double VELSTEP) {
        this.MINPOS=MINPOS;
        this.MAXPOS=MAXPOS;
        this.POSSTEP=POSSTEP;
        this.numActions=numActions;
        this.MINVEL=MINVEL;
        this.MAXVEL=MAXVEL;
        this.VELSTEP=VELSTEP;
//        int numQVals = new Double(((MAXPOS-MINPOS)/POSSTEP) * numActions * ((MAXVEL-MINVEL)/VELSTEP)).intValue();
        QVals = new double[(int)((MAXPOS-MINPOS)/POSSTEP)+1][numActions][(int)((MAXVEL-MINVEL)/VELSTEP)+1];
        e = new double[(int)((MAXPOS-MINPOS)/POSSTEP)+1][numActions][(int)((MAXVEL-MINVEL)/VELSTEP)+1];
        for(int i =0; i<QVals.length; i++) {
            for(int j = 0; j<QVals[i].length; j++) {
                for(int k = 0; k<QVals[i][j].length; k++) {
                    QVals[i][j][k] = random.nextDouble()-1;
                    QVals[i][2][k] = random.nextDouble()*0.10-0.10;
                    e[i][j][k] = 0.0;
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

    public double getEVals(int positionIndex, int actionIndex, int velocityIndex) {
        return e[positionIndex][actionIndex][velocityIndex];
    }

    public void setQVals(int positionIndex, int actionIndex, int velocityIndex, double update) {
        QVals[positionIndex][actionIndex][velocityIndex] += update;
    }
    
    public void setQVals(double position, int action, double velocityIndex, double newQ){
        QVals[this.getPositionIndex(position)][action][this.getVelocityIndex(velocityIndex)] = newQ;
    }
    
    public void setEValsCount(double position, int action, double velocity) {
        e[getPositionIndex(position)][action][getVelocityIndex(velocity)] += 1;
    }

    public void setEValsUpdate(int positionIndex, int actionIndex, int velocityIndex, double gamma, double lambda) {
        e[positionIndex][actionIndex][velocityIndex] *= (gamma*lambda);
    }

}
