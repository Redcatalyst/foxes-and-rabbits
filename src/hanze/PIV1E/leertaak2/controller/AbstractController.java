package hanze.PIV1E.leertaak2.controller;

import hanze.PIV1E.leertaak2.model.*;

import javax.swing.*;

public abstract class AbstractController extends JPanel {
	protected SimulationModel simulation;
	
	public AbstractController(SimulationModel simulation) {
		this.simulation = simulation;
	}
}

