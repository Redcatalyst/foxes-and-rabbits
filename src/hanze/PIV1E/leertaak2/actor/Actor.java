package hanze.PIV1E.leertaak2.actor;

import hanze.PIV1E.leertaak2.location.Location;
import hanze.PIV1E.leertaak2.music.*;



/**
 * The interface Actor.
 * @author Tsjeard de Winter en Rick van der Poel
 * @version 26/01/2015
 *
 */
public interface Actor {
	/**
	 * The method act, used by the actor to act.
	 * @param newActors, a list containing actors.
	 */
	 public void act();
	
	/**
	 * The method isAlive, used by the actor to check if it's alive or not.
	 * @return true if the actor is alive. 
	 */
	 public boolean isAlive();
	
	/**
	 * The method setDead removes the actor from the simulation.
	 */
	 public void setDead();
	 
	 /**
	  * The method getLocation returns the location of the actor.
	  * @return location of the actor
	  */
	 public Location getLocation();
	 
	 /**
	  * The method setLocation sets a new location for the actor.
	  * @param newLocation, a location.
	  */
	 public void setLocation(Location newLocation);
	 
	 /**
	  * Gets the name of the Actor.
	  * @return Name of the Actor
	  */
	 public String getName();
	 
	 /**
	  * The method getSound gets the music file that belongs to the actor
	  * @return MusicFile that belongs to the actor
	  */
	 public MusicFile getSound();
	 
}
