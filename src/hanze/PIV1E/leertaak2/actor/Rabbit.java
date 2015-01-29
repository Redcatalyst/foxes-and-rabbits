package hanze.PIV1E.leertaak2.actor;
import hanze.PIV1E.leertaak2.helper.Randomizer;
import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.location.Location;
import hanze.PIV1E.leertaak2.model.AbstractModel;
import hanze.PIV1E.leertaak2.model.SimulationModel;

import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Rabbit extends Animal
{
    // Characteristics shared by all rabbits (class variables).

    // The age at which a rabbit can start to breed.
    public static int BREEDING_AGE = 5;
    // The age to which a rabbit can live.
    public static int MAX_AGE = 40;
    // The likelihood of a rabbit breeding.
    public static double BREEDING_PROBABILITY = 0.12;
    // The maximum number of births.
    public static int MAX_LITTER_SIZE = 4;
    // The chance a rabbit can get infected
    public double rabbit_infection_chance = 0.9;
    // Indicates if a rabbit is infected
    public boolean infected = false;
    // A shared random number generator to control breeding.
    public static final Random rand = Randomizer.getRandom();
    
    
    // Individual characteristics (instance fields).
    
    // The rabbit's age.
    private int age;

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Rabbit(boolean randomAge, Field field, Location location, AbstractModel model)
    {
        super(field, location, model);
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to return newly born rabbits.
     */
    public void act()
    {
        incrementAge();
        if(isAlive()) {
        	setInfected();
            giveBirth();            
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
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
     * Increase the age.
     * This could result in the rabbit's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newRabbits A list to return newly born rabbits.
     */
    private void giveBirth()
    {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Rabbit young = new Rabbit(false, field, loc, getModel());
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
            births = rand.nextInt(getLitterSize()) + 1;
        }
        return births;
    }
    
    private int getLitterSize() 
    {

    	if(getRabbitCount() > 400 && getRabbitCount() <= 800){
    		return MAX_LITTER_SIZE / 2; 
    	}
    	if(getRabbitCount() > 800){
    		return MAX_LITTER_SIZE / 4;
    	}
		return MAX_LITTER_SIZE;
    }

    /**
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
    
    /**
     * @return age of the rabbit
     */
    public int getAge(){
    	return age;
    }
    
    
    /*
     * A rabbit can get ill with a rabbit virus when rabbits around him got the virus. 
     * 
     */
    public void checkAndInfect()
    {
    	Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            // Check if one of the adjacent locations has a rabbit
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                // Infect rabbit if other rabbit is infected
                if(rabbit.checkForInfection()) {
                	if(infected = false) {
                		setInfected();
                	}
                }
            }
        }
    }
    
    /*
     * Rabbits have a determined chance to become infected
     */
    public void setInfected()
    {
		if(rand.nextDouble() <= rabbit_infection_chance)
		{
			infected = true;
			age = MAX_AGE - 5;
		}
    }
    
    /*
     * Check if the rabbit is infected or not
     */
    public boolean checkForInfection()
    {
    	return infected;
    }
    
    private int getRabbitCount()
    {
    	return getModel().getStats().getPopulationCount(getModel().getField(), getClass());
    }
    
}
