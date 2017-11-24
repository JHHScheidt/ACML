package lab4;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Plot extends JFrame {
    public Plot(SARSA sarsa) {
        super("Plot of Q-Learner with actions drawn");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                for(double i = sarsa.MINPOS; i<=sarsa.MAXPOS; i+=sarsa.POSSTEP) {
                    for(double j = sarsa.MINVEL; j<=sarsa.MAXVEL; j+=sarsa.VELSTEP) {
                        double act0=sarsa.getQVals(i,0,j);
                        double act1=sarsa.getQVals(i,1,j);
                        double act2=sarsa.getQVals(i,2,j);
                        if(act0>=act1 && act0>=act2) {
                            //RED for action 0
                            g.setColor(new Color(255, 0, 0));
                        }
                        else if(act1>=act0 && act1>=act2) {
                            //BLACK for action 1
                            g.setColor(new Color(0,0,0));
                        }
                        else {
                            //GREEN for action 2
                            g.setColor(new Color(0, 255, 0));
                        }
                        g.drawRect(sarsa.getPositionIndex(i), sarsa.getVelocityIndex(j), 1, 1);
                    }
                }
            }
        };

        setContentPane(panel);
        setBounds(20, 20, 200, 200);
        setVisible(true);

    }

    public Plot(SARSA sarsa, double hiQ, double loQ) {
        super("Plot of Q-Learner with Q-values drawn");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                for(double i = sarsa.MINPOS; i<=sarsa.MAXPOS; i+=sarsa.POSSTEP) {
                    for(double j = sarsa.MINVEL; j<=sarsa.MAXVEL; j+=sarsa.VELSTEP) {
                        double act0=sarsa.getQVals(i,0,j);
                        double act1=sarsa.getQVals(i,1,j);
                        double act2=sarsa.getQVals(i,2,j);
                        double chosenQ;
                        if(act0>=act1 && act0>=act2) {
                            chosenQ=act0;
                        }
                        else if(act1>=act0 && act1>=act2) {
                            chosenQ=act1;
                        }
                        else {
                            chosenQ=act2;
                        }
                        chosenQ = Math.max(Math.min((chosenQ+10)/2,10),0);
                        g.setColor(new Color((int)((255*chosenQ)/10), (int)((255*(10-chosenQ))/10), 0));
                        g.drawRect(sarsa.getPositionIndex(i), sarsa.getVelocityIndex(j), 1, 1);
                    }
                }
            }
        };

        setContentPane(panel);
        setBounds(20, 20, 200, 200);
        setVisible(true);
    }
    
    public void writeToFileEpisodeVSTimestep(int[] timesteps){
        try{
            File f = new File("src//data//EpisodesVSTimestep.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i < timesteps.length; i++){
                bw.write(i + "," + timesteps[i]);
                bw.newLine();
            }
        } catch (IOException e){System.out.println("IOException");}
    }
    public void writeToFile(double[][][] values, boolean action){
        try{
            if(action){
                File f = new File("src//data//PositionVSVelocityHeatAction.txt");
                FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw);
                double action0, action1, action2;
                for(int i = 0; i < values.length; i++){
                    for(int j = 0; j < values[i][0].length; j++){
                        double act0= values[i][0][j];
                        double act1= values[i][1][j];
                        double act2= values[i][2][j];
                        double chosenQ;
                        int bestAction;
                        if(act0>=act1 && act0>=act2) {
                            chosenQ=act0;
                            bestAction = 0;
                        }
                        else if(act1>=act0 && act1>=act2) {
                            chosenQ=act1;
                            bestAction = 1;
                        }
                        else {
                            chosenQ=act2;
                            bestAction = 2;
                        }
                        bw.write(i + "," + j + "," + bestAction);
                        bw.newLine();
                    }
                }
            } else {
                File f = new File("src//data//PositionVSVelocityHeatQValues.txt");
                FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw);
                for(int i = 0; i < values.length; i++){
                    for(int j = 0; j < values[i][0].length; j++){
                        double act0= values[i][0][j];
                        double act1= values[i][1][j];
                        double act2= values[i][2][j];
                        double chosenQ;
                        if(act0>=act1 && act0>=act2) {
                            chosenQ=act0;
                        }
                        else if(act1>=act0 && act1>=act2) {
                            chosenQ=act1;
                        }
                        else {
                            chosenQ=act2;
                        }
                        bw.write(i + "," + j + "," + chosenQ);
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e){System.out.println("IOException");}
    }
}
