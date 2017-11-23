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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Marciano
 */
@SuppressWarnings("serial")
public class MountainCarWindowGoWithTheFlowExploration extends JFrame{

	private MountainCar mc;
	private MountainCarViewer view;

	public MountainCarWindowGoWithTheFlowExploration(MountainCar mc) {
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
		for (int i=0; i<10; i++) {
			mc.randomInit();
			int stepcounter = 0;
                        double[] currentState;
                        double[] nextState;
                        int action = 0;
                        double delta = 0;
                        double gamma = 0.5;
                        double alpha = 0.1;
                        double lambda = 1;
			while (!mc.done()) {
				pw.paintCar();

				currentState = mc.getState();
                                //This exploration basically completes the episode
                                //Within 200 steps always.
                                //This is not the purpose of an exploration function
                                //So we diverted to a different method
				if(currentState[1] == 0){
					action = (int)(Math.random()*3);
				} else if(currentState[1] > 0 ){
					action = 2;
				} else {
					action = 0;
				}

				mc.apply(action);

				nextState = mc.getState();

				delta = mc.getReward() + gamma * sarsa.getQVals(nextState[0], action, nextState[1]) - sarsa.getQVals(currentState[0], action, currentState[1]);
				sarsa.setEValsCount(currentState[0], action, currentState[1]);

				for(int j = 0; j < sarsa.QVals.length; j++){
					for(int k = 0; k < sarsa.QVals[j].length; k++){
						for(int l = 0; l < sarsa.QVals[j][k].length; l++){
							sarsa.setQVals(j, k, l, alpha * delta * sarsa.getEVals(j, k, l));
							sarsa.setEValsUpdate(j, k, l, gamma, lambda);
						}
					}
				}
				stepcounter++;
			}
			System.out.println("Episode " + i + " took " + stepcounter + " steps.");
		}
		pw.dispose();
	}
	
}

