package hanze.PIV1E.leertaak2.actor;

import hanze.PIV1E.leertaak2.helper.Randomizer;
import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.location.Location;

import java.util.Random;

/**
 * A simple model of a hunter.
 * hunters age, move, eat foxes, rabbits and bears and die.
 * @author Tsjeard de Winter
 * @Version 22.01.2015
 */
public class Hunter extends Human 
{
    // Characteristics shared by all hunters (class variables).
    
    // The age at which a hunter can start to breed.
    public static final int BREEDING_AGE = 25;
    // The age to which a hunter can live.
    public static final int MAX_AGE = 180;
    // The likelihood of a hunter breeding.
    public static final double BREEDING_PROBABILITY = 0.04;
    // The maximum number of births.
    public static final int MAX_LITTER_SIZE = 2;
    // The food value of a single rabbit.
    public static final int RABBIT_FOOD_VALUE = 9;
    // The food value of a single fox.
    public static final int FOX_FOOD_VALUE = 18;
    // The food value of a single bear.
    // This is also the number of steps a hunter can go before it has to eat again.
    public static final int BEAR_FOOD_VALUE =26;    
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
}
