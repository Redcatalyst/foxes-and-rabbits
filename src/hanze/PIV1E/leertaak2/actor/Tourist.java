package hanze.PIV1E.leertaak2.actor;

import hanze.PIV1E.leertaak2.helper.Randomizer;
import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.location.Location;
import hanze.PIV1E.leertaak2.model.AbstractModel;
import hanze.PIV1E.leertaak2.model.SimulationModel;
import hanze.PIV1E.leertaak2.music.MusicFile;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a tourist.
 * tourists age, move, make campfires and leave.
 * @author Tsjeard de Winter en Rick van der Poel
 * @Version 24.01.2015
 */
public class Tourist extends Human 
{
    // Characteristics shared by all tourists (class variables).
    
	// The sound a hunter makes
	private static final MusicFile sound = SimulationModel.tourist;
    // The likelihood of a tourist making a campfire.
    public static final double CAMPFIRE_PROBABILITY = 0.001;
    public static double campfire_probability = CAMPFIRE_PROBABILITY;
    // A shared random number generator to control the friends a tourist brings.
    public static final Random rand = Randomizer.getRandom();
    // A shared random number generator to control campfires a tourist can make.
    public static final Random rand2 = Randomizer.getRandom();
    // Individual characteristics (instance fields).
    // The tourists age.
    private int age;

    
    /**
     * Create a tourist. A tourist can be created as a new born (age zero)
     * or with a random age.
     * 
     * @param randomAge If true, the tourist will have random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Tourist( Field field, Location location, AbstractModel model)
    {
        super(field, location, model);
    } 
    
    /**
     * This is what the tourist does most of the time: it walks around
     * and makes campfires. Meanwhile it may leave or bring some friends.
     * @param field The field currently occupied.
     */
    public void act()
    {

        if(isAlive()) {   
        	if(makeCampfire() == true){
        		startFire();
        	}
            Location newLocation = moveTourist();
            if(newLocation == null) { 
                // try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            /* else {
                // Overcrowding.
                setDead();
            } */
            
        }
    }
    
    /**
     * @return age of the tourist.
     */
    public int getAge(){
    	return age;
    }
    
    private Location moveTourist()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object actor = field.getObjectAt(where);
            if(actor instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) actor;
                if(rabbit.isAlive()) { 
                    return null;
                }
            }
            if(actor instanceof Fox) {
                Fox fox = (Fox) actor;
                if(fox.isAlive()) { 
                    return null;
                }
            }  
            if(actor instanceof Bear) {
                Bear bear = (Bear) actor;
                if(bear.isAlive()) { 
                    return null;
                }
            }
            if(actor instanceof Hunter) {
                Hunter hunter = (Hunter) actor;
                if(hunter.isAlive()) { 
                    return null;
                }
            }
        }
        return null;
    }
    
    /**
     * Method to make a campfire.
     */
    private boolean makeCampfire()
    {
    	if(rand2.nextDouble() <= campfire_probability) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    
    /**
     * Check if forestfire is true.
     * If so, start a fire that will spread to adjacent locations, killing everything in it's way.
     */
    private void startFire()
    {
    	Field field = getField();
    	List<Location> adjacent = field.adjacentLocations(getLocation());
    	for(int i = 0; i < adjacent.size(); i++) {
    		Location loc = adjacent.remove(0);
    		Fire young = new Fire(field, loc, getModel());
            SimulationModel.newActors.add(young);
    	}
    }
    
	@Override
	public MusicFile getSound() {
		return sound;
	}
    
}
