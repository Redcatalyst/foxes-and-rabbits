package hanze.PIV1E.leertaak2.actor;

import hanze.PIV1E.leertaak2.location.Location;

import java.util.List;


/**
 * The interface Actor.
 * @author Tsjeard de Winter
 *
 */
public interface Actor {
	
	/**
	 * The method act, used by the actor to act.
	 * @param newActors, a list containing actors.
	 */
	 void act(List<Actor> newActors);
	
	/**
	 * The method isAlive, used by the actor to check if it's alive or not.
	 * @return true if the actor is alive. 
	 */
	 boolean isAlive();
	
	/**
	 * The method setDead removes the actor from the simulation.
	 */
	 void setDead();
	 
	 /**
	  * The method getLocation returns the location of the actor.
	  * @return location of the actor
	  */
	 Location getLocation();
	 
	 /**
	  * The method setLocation sets a new location for the actor.
	  * @param newLocation, a location.
	  */
	 void setLocation(Location newLocation);
	 
}
