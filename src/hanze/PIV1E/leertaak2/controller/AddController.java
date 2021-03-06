package hanze.PIV1E.leertaak2.controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import hanze.PIV1E.leertaak2.model.SimulationModel;
/**
 * Defines the controller for adding actors to the simulation
 */
public class AddController extends AbstractController {
	
	/**
	 * Add the controller to the simulation
	 * @param simulation The simulation that is to be connected with this controller
	 */
	public AddController(SimulationModel simulation) {
		super(simulation);
		makePanel();
	}
	
	/**
	 * Make the panel for adding individual animals.
	 */
	public void makePanel(){
		JPanel flow = new JPanel();
		GridLayout layout = new GridLayout(0, 1);
		layout.setVgap(3);
	    flow.setLayout(layout);
	    
	    // Add the Add rabbit button
	    JButton rabbit = new JButton("Add rabit");
	    rabbit.addActionListener(new ActionListener() {
	    							public void actionPerformed(ActionEvent e) {simulation.addRabbit();}
	    						});
	    flow.add(rabbit);

	    add(flow);
	    
	    // Add the Add fox button
	    JButton fox = new JButton("Add fox");
	    fox.addActionListener(new ActionListener() {
	    							public void actionPerformed(ActionEvent e) {simulation.addFox(); }
	    						});
	    flow.add(fox);

	    add(flow);
	    
	    // Add the Add bear button
	    JButton bear = new JButton("Add bear");
	    bear.addActionListener(new ActionListener() {
	    							public void actionPerformed(ActionEvent e) {simulation.addBear(); }
	    						});
	    flow.add(bear);

	    add(flow);
	    
	    // Add the Add hunter button
	    JButton hunter = new JButton("Add hunter");
	    hunter.addActionListener(new ActionListener() {
	    							public void actionPerformed(ActionEvent e) {simulation.addHunter(); }
	    						});
	    flow.add(hunter);

	    add(flow);
	    
	    // Add the Add tourist button
	    JButton tourist = new JButton("Add tourist");
	    tourist.addActionListener(new ActionListener() {
	    							public void actionPerformed(ActionEvent e) {simulation.addTourist(); }
	    						});
	    flow.add(tourist);

	    add(flow);
	}
}
