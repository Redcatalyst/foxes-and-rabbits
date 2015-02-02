package hanze.PIV1E.leertaak2.controller;

import hanze.PIV1E.leertaak2.model.*;

import javax.swing.*;

/**
 * Defines the basics of a controller as part of the MVC-model. 
 * Every controller should extend this class. 
 */
public abstract class AbstractController extends JPanel {
	protected SimulationModel simulation;
	
	/**
	 * Lets the simulation know that this controller is using his input.
	 * @param simulation The simulation to be connected with this controller.
	 */
	public AbstractController(SimulationModel simulation) {
		this.simulation = simulation;
	}
}

