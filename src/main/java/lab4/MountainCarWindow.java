/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MountainCarWindow extends JFrame {

	private MountainCar mc;
	private MountainCarViewer view;

	private static Random random = new Random(42);
    private static double randomActionThreshold = 0.9;

	public MountainCarWindow(MountainCar mc) {
		super("Pendulum Environment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mc = mc;
		//Add a MountainCarViewer
		view = new MountainCarViewer(mc);
		add(view, BorderLayout.CENTER);
		//Add a button to reset the car
		JButton randomButton = new JButton();
		randomButton.setText("Random");
		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {setRandomAngle();}
		    });
		//This is ugly, but easy ...
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(randomButton, null);
		add(buttonPanel, BorderLayout.SOUTH);
		//Open in center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = view.getPreferredSize(); 
 		int left = (screenSize.width - frameSize.width)/ 2;
		int top  = (screenSize.height - frameSize.height)/ 2;
		setLocation(left, top);
		
		pack();
	}

	public void paintCar() {
		setVisible(true);
		view.repaint();
		try {Thread.sleep(15);} catch (InterruptedException e) {}
	}
	
	private void setRandomAngle() {
		mc.randomInit();
	}
	
	public static void main(String[] args) {
		MountainCar mc = new MountainCar();
		MountainCarWindow pw = new MountainCarWindow(mc);
		SARSA sarsa = new SARSA(mc.MIN_POS, mc.MAX_POS, 0.0005, 3, -mc.MAX_SPEED, mc.MAX_SPEED, 0.0005);
                //System.out.println(sarsa.QVals.containsKey(new PAVTuple(-0.0405, 1, 0.0205)));
        int iterations = 100000;
        double highestQ = -1000000;
        double lowestQ = 1000000;
		for (int i=0; i<iterations; i++) {
			mc.randomInit();
			int stepcounter = 0;
                        double[] currentState;
                        double[] nextState;
                        int action = 0;
                        double delta = 0;
                        double gamma = 0.9;
                        double alpha = 0.1;
                        double lambda = 1;
			while (!mc.done()) {
//				pw.paintCar();

                currentState = mc.getState();
                if(random.nextDouble()>=randomActionThreshold) {
                    action = random.nextInt(3);
                }
                else {
                    double action0Q = sarsa.getQVals(currentState[0], 0, currentState[1]);
                    double action1Q = sarsa.getQVals(currentState[0], 1, currentState[1]);
                    double action2Q = sarsa.getQVals(currentState[0], 2, currentState[1]);
                    if(action0Q<lowestQ) lowestQ=action0Q;
                    if(action1Q<lowestQ) lowestQ=action1Q;
                    if(action2Q<lowestQ) lowestQ=action2Q;
                    if(action0Q>highestQ) highestQ=action0Q;
                    if(action1Q>highestQ) highestQ=action1Q;
                    if(action2Q>highestQ) highestQ=action2Q;
                    if(action0Q>=action1Q && action0Q>=action2Q) action=0;
                    else if(action1Q>=action0Q && action1Q>=action2Q) action=1;
                    else action=2;
                }

                mc.apply(action);

                nextState = mc.getState();

                sarsa.QVals[sarsa.getPositionIndex(currentState[0])][action][sarsa.getVelocityIndex( currentState[1])] = mc.getReward() + gamma*(Math.max(sarsa.getQVals(nextState[0],2, nextState[1]), Math.max(sarsa.getQVals(nextState[0],0, nextState[1]), sarsa.getQVals(nextState[0],1, nextState[1]))));

//                delta = mc.getReward() + gamma * sarsa.getQVals(nextState[0], action, nextState[1]) - sarsa.getQVals(currentState[0], action, currentState[1]);
//                sarsa.setEValsCount(currentState[0], action, currentState[1]);
//
//                for(int j = 0; j < sarsa.QVals.length; j++){
//                    for(int k = 0; k < sarsa.QVals[j].length; k++){
//                        for(int l = 0; l < sarsa.QVals[j][k].length; l++){
//                            sarsa.setQVals(j, k, l, alpha * delta * sarsa.getEVals(j, k, l));
//                            sarsa.setEValsUpdate(j, k, l, gamma, lambda);
//                        }
//                    }
//                }
                stepcounter++;
			}
			if(i>=iterations-10)
			    System.out.println("Episode " + i + " took " + stepcounter + " steps.");
		}
        Plot plot = new Plot("ACTION", sarsa);
        System.out.println("highest:"+highestQ+"     lowest:"+lowestQ);
        pw.dispose();
	}
	
}