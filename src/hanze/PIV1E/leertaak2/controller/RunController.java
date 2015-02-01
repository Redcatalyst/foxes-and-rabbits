package hanze.PIV1E.leertaak2.controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import hanze.PIV1E.leertaak2.model.SimulationModel;

public class RunController extends AbstractController {

	public RunController(SimulationModel simulation) {
		super(simulation);
		makePanel();
	}
	
	public void makePanel(){
		JPanel flow = new JPanel();
		GridLayout layout = new GridLayout(0, 1);
		layout.setVgap(3);
	    flow.setLayout(layout);
	    
	    JButton step1 = new JButton("Step 1");
	    step1.addActionListener(new ActionListener() {
	    							public void actionPerformed(ActionEvent e) {step1(); }
	    						});
	    flow.add(step1);
	    
	    JButton step100 = new JButton("Step 100");
	    step100.addActionListener(new ActionListener() {
	    							public void actionPerformed(ActionEvent e) {step100(); }
	    						});
	    flow.add(step100);
	    
	    JButton reset = new JButton("reset");
	    reset.addActionListener(new ActionListener() {
	    							public void actionPerformed(ActionEvent e) {reset(); }
	    						});
	    flow.add(reset);

	    add(flow);
	}
    
	/**
     * Simulates 1 step in the simulation
     */
    private void step1(){
    	simulation.simulate(1);
    }
    
    /**
     * Simulates 100 steps in the simulation
     */
    private void step100(){
    	simulation.simulate(100);
    }
    
    /**
     * resets the simulation
     */
    private void reset(){
    	simulation.reset();
    }
}
