package hanze.PIV1E.leertaak2.view;

import java.awt.Color;

import javax.swing.*;

import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.model.*;

/**
 * Defines the basics of a view as part of the MVC-model.
 * Every view should extend this class.
 * @author Frank
 */
public abstract class AbstractView extends JPanel {
	protected AbstractModel simulation;

	/**
	 * Lets the simulation know that this view is using his data.
	 * @param simulation The simulation to be connected with the view.
	 */
	public AbstractView(AbstractModel simulation) {
		this.simulation = simulation;
		simulation.addView(this);
	}
	
	/**
	 * Returns the model that is connected to this view
	 * @return the model connected to this view
	 */
	public AbstractModel getModel() {
		return simulation;
	}
	
	/**
	 * When a model changes every view will be notified by calling this method.
	 */
	public void updateView() {
		showStatus(getModel().getStep(), getModel().getField(), getModel().getStats());
	}
	
	/**
	 * Get's called by the updateView() and gives the information needed from the model to change the view.
	 * @param step The step the simulation is in
	 * @param field The field the simulation is currently working in
	 * @param stats The stats the simulation is currently working in (based on the field data)
	 */
	public abstract void showStatus(int step, Field field, FieldStats stats);
	
	/**
	 * Registers a class by the view.
	 * @param actor The actor to be registered
	 * @param color The color the actor should have
	 */
	public abstract void setColor(Class actor, Color color);
	
}
