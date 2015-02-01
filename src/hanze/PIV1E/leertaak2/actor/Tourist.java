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
    
    public static final int INTRODUCING_AGE = 0;
	// The age to which a tourist can live.
    public static final int MAX_AGE = 500;
    // The likelihood of a tourist bringing friends.
    public static final double INTRODUCING_PROBABILITY = 0.01;
    // The likelihood of a tourist making a campfire.
    public static final double CAMPFIRE_PROBABILITY = 0.9;
    // The likelihood of a campfire setting off a forestfire.
    public static final double FORESTFIRE_PROBABILITY = 0.9;
    // The maximum number of new tourists.
    public static final int MAX_NEW_TOURISTS = 1;   
    // A shared random number generator to control the friends a tourist brings.
    public static final Random rand = Randomizer.getRandom();
    // A shared random number generator to control campfires a tourist can make.
    public static final Random rand2 = Randomizer.getRandom();
    // A shared random number generator to control chance a campfire sets off a forestfire..
    public static final Random rand3 = Randomizer.getRandom();
    // Individual characteristics (instance fields).
    // The tourists age.
    private int age;
    // boolean for a forestfire.
    private boolean forestfire = false;
    
    /**
     * Create a tourist. A tourist can be created as a new born (age zero)
     * or with a random age.
     * 
     * @param randomAge If true, the tourist will have random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Tourist(boolean randomAge, Field field, Location location, AbstractModel model)
    {
        super(field, location, model);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
        else {
            age = 0;
        }
    } 
    
    /**
     * This is what the tourist does most of the time: it walks around
     * and makes campfires. Meanwhile it may leave or bring some friends.
     * @param field The field currently occupied.
     */
    public void act()
    {
        incrementAge();
        if(isAlive()) {   
        	// newTourist(); 
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
            if(makeCampfire() && canForestfire()){
            	forestfire = true;
            }
        }
    }

    
    /**
     * Increase the age. This could result in the tourist leaving the forest.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
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
    	if(rand2.nextDouble() <= CAMPFIRE_PROBABILITY) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
     * Method to check if there can be a forestfire.
     */
    private boolean canForestfire()
    {
    	if(rand3.nextDouble() <= FORESTFIRE_PROBABILITY) {
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
            
    
    /**
     * Check whether or not the tourist brings friends.  
     * New tourist will be made into free adjacent locations.
     * @param newTourists A list to return newly introduced tourists.
     */
    private void newTourist()
    {
        // New tourists are introduced into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int introduces = introduceFriends();
        for(int b = 0; b < introduces && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Tourist young = new Tourist(false, field, loc, getModel());
            SimulationModel.newActors.add(young);
        }
    }
    
    /**
     * Generate a number representing the number of introduces,
     * if it can introduce.
     * @return The number of introduces (may be zero).
     */
    private int introduceFriends()
    {
        int newTourists = 0;
        if(canIntroduce() && rand.nextDouble() <= INTRODUCING_PROBABILITY) {
        	newTourists = rand.nextInt(MAX_NEW_TOURISTS) + 1;
        }
        return newTourists;
    }

    /**
     * A fox can breed if it has reached the breeding age.
     */
    private boolean canIntroduce()
    {
        return age >= INTRODUCING_AGE;
    }

	@Override
	public MusicFile getSound() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
