package hanze.PIV1E.leertaak2.view;

import javax.swing.*;
import hanze.PIV1E.leertaak2.model.*;

public abstract class AbstractView extends JPanel {
	protected SimulationModel simulation;

	public AbstractView(SimulationModel simulation) {
		this.simulation = simulation;
		simulation.addView(this);
	}
	
	public SimulationModel getModel() {
		return simulation;
	}
	
	public void updateView() {
		repaint();
	}
}
