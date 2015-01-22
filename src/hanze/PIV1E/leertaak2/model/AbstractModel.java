package hanze.PIV1E.leertaak2.model;

import hanze.PIV1E.leertaak2.main.Simulator;
import hanze.PIV1E.leertaak2.view.*;

import java.util.*;

public abstract class AbstractModel {
	private List<AbstractView> views;
	
	public AbstractModel() {
		views = new ArrayList<AbstractView>();
	}
	
	public void addView(AbstractView view) {
		views.add(view);
	}
	
	public void notifyViews() {
		for(AbstractView v: views) v.updateView();
	}
}
