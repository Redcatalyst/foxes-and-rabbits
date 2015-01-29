package hanze.PIV1E.leertaak2.actor;

import hanze.PIV1E.leertaak2.location.*;
import hanze.PIV1E.leertaak2.model.*;


/**
 * A class representing shared characteristics of animals.
 * 
 * @author Tsjeard de Winter en Rick van der Poel
 * @version 2015.01.29
 */
public abstract class Animal implements Actor
{
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // The animal's model
    private AbstractModel model;
    // The animal's name
    private String name;
    // Whether the animal is infected or not.
    protected boolean infected = false;
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location, AbstractModel model)
    {
        alive = true;
        this.field = field;
        this.model = model;
        setLocation(location);
    }

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
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
     * Return the animal's location.
     * @return The animal's location.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
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
     * Return the animal's field.
     * @return The animal's field.
     */
    protected Field getField()
    {
        return field;
    }
    
    /**
     * Return the animals name
     * @return name
     */
    public String getName()
    {
    	return name;
    }
    
    /**
     * Return the model that this animal is linked to
     * @return model
     */
    public AbstractModel getModel(){
    	return model;
    }
    
    /**
     * Check whether the animal is infected or not
     */
    public boolean checkForInfection()
    {
    	return infected;
    }
    
}
