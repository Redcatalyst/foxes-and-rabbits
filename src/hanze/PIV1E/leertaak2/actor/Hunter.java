package hanze.PIV1E.leertaak2.actor;

import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.location.Location;
import hanze.PIV1E.leertaak2.model.AbstractModel;
import hanze.PIV1E.leertaak2.model.SimulationModel;
import hanze.PIV1E.leertaak2.music.MusicFile;

import java.util.Iterator;
import java.util.List;

/**
 * A simple model of a hunter.
 * hunters age, move, eat foxes, rabbits and bears and die.
 * @author Tsjeard de Winter en Rick van der Poel
 * @version 2015.01.29
 */
public class Hunter extends Human 
{
	
	// The sound a hunter makes
	private static final MusicFile sound = SimulationModel.hunter;
	// Max population for a Rabbit
	public static final int RABBIT_MAX_POPULATION = 1400;
	public static int rabbit_max_population = RABBIT_MAX_POPULATION;
	// Max population for a Fox
	public static final int FOX_MAX_POPULATION = 300;
	public static int fox_max_population = FOX_MAX_POPULATION;
	// Max population for a Bear
	public static final int BEAR_MAX_POPULATION = 300;
	public static int bear_max_population = BEAR_MAX_POPULATION;

    /**
     * Create a hunter. A hunter stays in the game to hunt the over populated species. 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param model The model that is used
     */
    public Hunter(Field field, Location location, AbstractModel model)
    {
        super(field, location, model);
    } 
    
    /**
     * This is what the hunter does most of the time: it hunts for bears,
     * rabbits and foxes. In the process it might die by a bear of old age.
     * @param field The field currently occupied.
     */
    public void act()
    {
        if(isAlive()) {   
            // Move towards a source of food if found.
            Location newLocation = findHunt();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
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
     * Look for rabbits, foxes and bears adjacent to the current location.
     * Only the first live rabbit, fox or bear is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findHunt()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object actor = field.getObjectAt(where);
            if(actor instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) actor;
                if(rabbit.isAlive() && rabbit.getCount() > rabbit_max_population) { 
                    rabbit.setDead();
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
            if(actor instanceof Fox) {
                Fox fox = (Fox) actor;
                if(fox.isAlive() && fox.getCount() > fox_max_population) { 
                    fox.setDead();
                    // Remove the dead fox from the field.
                    return where;
                }
            }  
            if(actor instanceof Bear) {
                Bear bear = (Bear) actor;
                if(bear.isAlive() && bear.getCount() > bear_max_population) { 
                    bear.setDead();
                    // Remove the dead bear from the field.
                    return where;
                }
            }
        }
        return null;
    }
    
    public MusicFile getSound() {
		return sound;
	}
}
