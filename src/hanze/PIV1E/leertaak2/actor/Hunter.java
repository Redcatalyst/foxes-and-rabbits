package hanze.PIV1E.leertaak2.actor;

import hanze.PIV1E.leertaak2.helper.Randomizer;
import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.location.Location;
import hanze.PIV1E.leertaak2.model.SimulationModel;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a hunter.
 * hunters age, move, eat foxes, rabbits and bears and die.
 * @author Tsjeard de Winter en Rick van der Poel
 * @Version 24.01.2015
 */
public class Hunter extends Human 
{
    // Characteristics shared by all hunters (class variables).
    
    public static final int INTRODUCING_AGE = 28;
	// The age to which a hunter can live.
    public static final int MAX_AGE = 280;
    // The likelihood of a hunter breeding.
    public static final double INTRODUCING_PROBABILITY = 0.03;
    // The maximum number of new hunters.
    public static final int MAX_NEW_HUNTERS = 1;
    // The food value of a single rabbit.
    public static final int RABBIT_FOOD_VALUE = 6;
    // The food value of a single fox.
    public static final int FOX_FOOD_VALUE = 12;
    // The food value of a single bear.
    // This is also the number of steps a hunter can go before it has to eat again.
    public static final int BEAR_FOOD_VALUE = 24;    
    // A shared random number generator to control breeding.
    public static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The hunter's age.
    private int age;
    // The hunter's food level, which is increased by eating rabbits, foxes or bears.
    private int foodLevel;
    
    /**
     * Create a hunter. A hunter can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the hunter will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Hunter(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(BEAR_FOOD_VALUE);
        }
        else {
            age = 0;
            foodLevel = BEAR_FOOD_VALUE;
        }
    } 
    
    /**
     * This is what the hunter does most of the time: it hunts for bears,
     * rabbits and foxes. In the process it might die by a bear of old age.
     * @param field The field currently occupied.
     */
    public void act()
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {   
        	newHunter(); 
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    
    /**
     * Increase the age. This could result in the hunters death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * This makes the hunter more hungry. This could result in the hunters death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Look for rabbits, foxes and bears adjacent to the current location.
     * Only the first live rabbit, fox or bear is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
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
                    rabbit.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
            if(actor instanceof Fox) {
                Fox fox = (Fox) actor;
                if(fox.isAlive()) { 
                    fox.setDead();
                    foodLevel = FOX_FOOD_VALUE;
                    // Remove the dead fox from the field.
                    return where;
                }
            }  
            if(actor instanceof Bear) {
                Bear bear = (Bear) actor;
                if(bear.isAlive()) { 
                    bear.setDead();
                    foodLevel = BEAR_FOOD_VALUE;
                    // Remove the dead bear from the field.
                    return where;
                }
            }
        }
        return null;
    }
    
    /**
     * @return age of the hunter.
     */
    public int getAge(){
    	return age;
    }
    
    /**
     * @return foodLevel of the hunter.
     */
    public int getFoodLevel(){
    	return foodLevel;
    }
    
    /**
     * Check whether or not the hunter lets his child go hunt.  
     * New hunters will be made into free adjacent locations.
     * @param newFoxes A list to return newly born foxes.
     */
    private void newHunter()
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int introduces = introduceHunting();
        for(int b = 0; b < introduces && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Hunter young = new Hunter(false, field, loc);
            SimulationModel.newActors.add(young);
        }
    }
    
    /**
     * Generate a number representing the number of introduces,
     * if it can introduce.
     * @return The number of introduces (may be zero).
     */
    private int introduceHunting()
    {
        int newHunters = 0;
        if(canIntroduce() && rand.nextDouble() <= INTRODUCING_PROBABILITY) {
        	newHunters = rand.nextInt(MAX_NEW_HUNTERS) + 1;
        }
        return newHunters;
    }

    /**
     * A fox can breed if it has reached the breeding age.
     */
    private boolean canIntroduce()
    {
        return age >= INTRODUCING_AGE;
    }
    
}
