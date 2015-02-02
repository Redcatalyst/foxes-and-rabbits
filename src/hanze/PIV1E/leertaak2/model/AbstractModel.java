package hanze.PIV1E.leertaak2.model;

import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.view.*;

import java.util.*;

/**
 * Defines the basics of a model as part of the MVC-model.
 * Every model should extend this class.
 */
public abstract class AbstractModel {
	private List<AbstractView> views;
	
	/**
	 * Creats a model and a list of views connected to this model.
	 */
	public AbstractModel() {
		views = new ArrayList<AbstractView>();
	}
	
	/**
	 * Adds a view to the list.
	 * @param view The view to add to the list.
	 */
	public void addView(AbstractView view) {
		views.add(view);
	}
	
	/**
	 * Notify all the views in the list that the model has changed.
	 */
	public void notifyViews() {
		for(AbstractView v: views) v.updateView();
	}
	
	/**
	 * Gives the field the simulation works in
	 * @return the field.
	 */
	public abstract Field getField();
	
	/**
	 * Gives the current step of the simulation.
	 * @return the step.
	 */
	public abstract int getStep();	
	
	/**
	 * Gives the fieldstats the simulation works in
	 * @return the fieldstats.
	 */
	public abstract FieldStats getStats();
}
