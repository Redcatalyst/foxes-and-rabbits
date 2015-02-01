package hanze.PIV1E.leertaak2.actor;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import hanze.PIV1E.leertaak2.helper.Randomizer;
import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.location.Location;
import hanze.PIV1E.leertaak2.model.AbstractModel;
import hanze.PIV1E.leertaak2.model.SimulationModel;
import hanze.PIV1E.leertaak2.music.MusicFile;

public class Fire extends Animal
{
    // Characteristics shared by all fires (class variables).
	
	// The sound a fire makes
	private static final MusicFile sound = SimulationModel.rabbit;
    // The age to which a fire can live.
    private static int MAX_AGE = 5;
    // The likelihood of a fire spreading.
    private static double SPREADING_PROBABILITY = 0.12; 
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    
    // The fire's age
    private int age;

    /**
     * Create a new fire.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Fire(Field field, Location location, AbstractModel model)
    {
        super(field, location, model);
        age = 0;
    }
    
    /**
     * This is what the fire does most of the time - it spreads
     * and kills everything in it's way.
     * @param newFires A list to return newly spread fires.
     */
    public void act()
    {
        incrementAge();
        if(isAlive()) {
            spreadFire();            
            // Try to move into a free location.
            Location newLocation = spreadAndKill();
            if(newLocation == null) { 
                // No food inhabitants - try to move to a free location.
                newLocation = getField().randomAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            }
        }

	private Location spreadAndKill() 
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
	                    // Remove the dead rabbit from the field.
	                    return where;
	                }
	            }
	             if(actor instanceof Fox) {
	                Fox fox = (Fox) actor;
	                if(fox.isAlive()) { 
	                    fox.setDead();
	                    // Remove the dead fox from the field.
	                    return where;
	                }
	            } 
	             if(actor instanceof Bear) {
	            	 Bear bear = (Bear) actor;
		                if(bear.isAlive()) { 
		                	bear.setDead();
		                    // Remove the dead fox from the field.
		                    return where;
		           }
		        }
	             if(actor instanceof Hunter) {
	            	 Hunter hunter = (Hunter) actor;
		                if(hunter.isAlive()) { 
		                	hunter.setDead();
		                    // Remove the dead fox from the field.
		                    return where;
		            }
		       } 
	             if(actor instanceof Tourist) {
	            	 Tourist tourist = (Tourist) actor;
		                if(tourist.isAlive()) { 
		                	tourist.setDead();
		                    // Remove the dead fox from the field.
		                    return where;
		                }
		            }  
	        }
	        return null;
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
     * The fire can spread anywhere.
     * @param newFires A list to return newly spread fires.
     */
    private void spreadFire()
    {
        // New fires are spread to adjacent locations.
        // Get a list of adjacent locations.
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
    	for(int i = 0; i < adjacent.size(); i++) {
    		Location loc = adjacent.remove(0);
    		Fire young = new Fire(field, loc, getModel());
            SimulationModel.newActors.add(young);
        }
    }

    /**
     * @return age of the fire
     */
    public int getAge(){
    	return age;
    }   
    
    /**
     * @return the sound of the fire.
     */
	public MusicFile getSound() {
		return sound;
	}        
}

