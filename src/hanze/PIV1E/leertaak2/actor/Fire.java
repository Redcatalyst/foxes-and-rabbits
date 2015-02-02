package hanze.PIV1E.leertaak2.actor;

import java.util.Iterator;
import java.util.List;

import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.location.Location;
import hanze.PIV1E.leertaak2.model.AbstractModel;
import hanze.PIV1E.leertaak2.model.SimulationModel;
import hanze.PIV1E.leertaak2.music.MusicFile;

public class Fire implements Actor
{
	// Whether the human is alive or not.
    private boolean alive;
    // The human's field.
    private Field field;
    // The human's position in the field.
    private Location location;
    // The human's model
    private AbstractModel model;
    // The human's name
    private String name;
	// The sound a fire makes
	private static final MusicFile sound = SimulationModel.fire;
    // The age to which a fire can live.
    private static int MAX_AGE = 1;
    // The maximal amount of fires.
    private static int MAX_FIRES = 3000;
    // A shared random number generator to control breeding.
    // private static final Random rand = Randomizer.getRandom();
    
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
    	alive = true;
        this.field = field;
        this.model = model;
        setLocation(location);
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
	    if(isAlive() && this.getCount() < MAX_FIRES) {
	    	spreadFire();
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
     * The fire can spread anywhere.
     * 
     */
    private void spreadFire()
    {
        // New fires are spread to adjacent locations.
        // Get a list of adjacent locations.
    		KillAdjecent();
	        Field field = getField();
	        List<Location> adjacent = field.adjacentLocations(getLocation());
	    	for(int i = 0; i < adjacent.size(); i++) {
	    		Location loc = adjacent.remove(0);
	    		Fire young = new Fire(field, loc, getModel());
	            SimulationModel.newActors.add(young);
	        }
    }
    
    /**
     * The fire kills everything around itself because of the hear
     */
	private void KillAdjecent() 
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
	                }
	            }
	             if(actor instanceof Fox) {
	                Fox fox = (Fox) actor;
	                	if(fox.isAlive()) { 
	                		fox.setDead();
	                	}
	             } 
	             if(actor instanceof Bear) {
	            	 Bear bear = (Bear) actor;
		                if(bear.isAlive()) { 
		                	bear.setDead();
		                }
	             }
	             if(actor instanceof Hunter) {
	            	 Hunter hunter = (Hunter) actor;
		                if(hunter.isAlive()) { 
		                	hunter.setDead();
		                }
	             } 
	             if(actor instanceof Tourist) {
	            	 Tourist tourist = (Tourist) actor;
		                if(tourist.isAlive()) { 
		                	tourist.setDead();
		                }
		         }  
	             if(actor instanceof Fire) {
	            	 Fire fire = (Fire) actor;
		                if(fire.isAlive()) { 
		                	fire.setDead();
		                }
		         }
	        }
	}

    /**
     * Get the age of the fire
     * @return age of the fire
     */
    public int getAge(){
    	return age;
    }   
    
    /**
     * Get the MusicFile that belongs to the fire
     * @return the sound of the fire.
     */
	public MusicFile getSound() {
		return sound;
	}
	
	 /**
     * Check whether the human is alive or not.
     * @return true if the human is still alive.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the human is no longer alive.
     * It is removed from the field.
     */
     public void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Return the human's location.
     * @return The human's location.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the human at the new location in the given field.
     * @param newLocation The human's new location.
     */
    public void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the human's field.
     * @return The human's field.
     */
    protected Field getField()
    {
        return field;
    }
    
    /**
     * Return the name of the fire 
     * @return the human's name.
     */
    public String getName(){
    	return name;
    }
    
    /**
     * Get the model that belongs to the fire
     * @return model that belongs to the fire
     */
    public AbstractModel getModel(){
    	return model;
    }
    
    /**
     * Used to get the current count of rabbits on the field.
     * @return getPopulationCount the current count of the rabbits in the field
     */
    protected int getCount()
    {
    	return getModel().getStats().getPopulationCount(getModel().getField(), getClass());
    }
}

