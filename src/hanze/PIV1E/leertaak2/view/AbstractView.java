package hanze.PIV1E.leertaak2.view;

import java.awt.Color;

import javax.swing.*;

import hanze.PIV1E.leertaak2.location.Field;
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
		showStatus(getModel().getStep(), getModel().getField(), getModel().getStats());
	}
	
	public abstract void showStatus(int step, Field field, FieldStats stats);
	
	public abstract void setColor(Class animalClass, Color color);
	
}
