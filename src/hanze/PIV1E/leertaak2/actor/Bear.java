package hanze.PIV1E.leertaak2.actor;

import hanze.PIV1E.leertaak2.helper.Randomizer;
import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.location.Location;
import hanze.PIV1E.leertaak2.main.Simulator;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a bear.
 * Bears age, move, eat foxes and rabbits and die.
 * @author Tsjeard de Winter
 * @Version 22.01.2015
 */
public class Bear extends Animal
{
    // Characteristics shared by all bears (class variables).
    
    // The age at which a bear can start to breed.
    public static final int BREEDING_AGE = 20;
    // The age to which a bear can live.
    public static final int MAX_AGE = 200;
    // The likelihood of a bear breeding.
    public static final double BREEDING_PROBABILITY = 0.06;
    // The maximum number of births.
    public static final int MAX_LITTER_SIZE = 3;
    // The food value of a single rabbit.
    public static final int RABBIT_FOOD_VALUE = 9;
    // The food value of a single fox.
    // This is also the number of steps a bear can go before it has to eat again.
    public static final int FOX_FOOD_VALUE = 18;
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
}