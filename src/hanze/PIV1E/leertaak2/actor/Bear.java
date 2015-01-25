package hanze.PIV1E.leertaak2.actor;

import hanze.PIV1E.leertaak2.helper.Randomizer;
import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.location.Location;
import hanze.PIV1E.leertaak2.model.SimulationModel;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a bear.
 * Bears age, move, eat foxes and rabbits and die.
 * @author Tsjeard de Winter en Rick van der Poel
 * @Version 22.01.2015
 */
public class Bear extends Animal
{
    // Characteristics shared by all bears (class variables).
    
    // The age at which a bear can start to breed.
    public static final int BREEDING_AGE = 10;
    // The age to which a bear can live.
    public static final int MAX_AGE = 200;
    // The likelihood of a bear breeding.
    public static final double BREEDING_PROBABILITY = 0.05;
    // The maximum number of births.
    public static final int MAX_LITTER_SIZE = 2;
    // The food value of a single rabbit.
    public static final int RABBIT_FOOD_VALUE = 6;
    // The food value of a single fox.
    // This is also the number of steps a bear can go before it has to eat again.
    public static final int FOX_FOOD_VALUE = 12;
    // A shared random number generator to control breeding.
    public static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The bear's age.
    private int age;
    // The bear's food level, which is increased by eating rabbits or foxes.
    private int foodLevel;
    
    /**
     * Create a bear. A bear can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the bear will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Bear(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(FOX_FOOD_VALUE);
        }
        else {
            age = 0;
            foodLevel = FOX_FOOD_VALUE;
        }
    } 
    
    /**
     * This is what the bear does most of the time: it hunts for
     * rabbits and foxes. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newBears A list to return newly born foxes.
     */
    public void act()
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth();            
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
     * Increase the age. This could result in the bear's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Make this bear more hungry. This could result in the bear's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Look for rabbits and foxes adjacent to the current location.
     * Only the first live rabbit or fox is eaten.
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
        }
        return null;
    }
    
    /**
     * Check whether or not this bear is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newBears A list to return newly born foxes.
     */
    private void giveBirth()
    {
        // New bear's are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Bear young = new Bear(false, field, loc);
            SimulationModel.newActors.add(young);
        }
    }
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A bear can breed if it has reached the breeding age.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
    
    /**
     * @return age of the bear
     */
    public int getAge(){
    	return age;
    }
    
    /**
     * @return foodLevel of the bear
     */
    public int getFoodLevel(){
    	return foodLevel;
    }
}