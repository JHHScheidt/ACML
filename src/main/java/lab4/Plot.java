package lab4;

import javax.swing.*;
import java.awt.*;

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

}
