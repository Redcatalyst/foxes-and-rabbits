package hanze.PIV1E.leertaak2.actor;

import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.location.Location;
import hanze.PIV1E.leertaak2.model.AbstractModel;

/**
 * A class representing shared characteristics of humans.
 * 
 * @author Tsjeard de Winter en Rick van der Poel
 * @version 22.01.2015
 */
public abstract class Human implements Actor 
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
    
    /**
     * Create a new human at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Human(Field field, Location location, AbstractModel model)
    {
        alive = true;
        this.field = field;
        this.model = model;
        setLocation(location);
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
     * Return the human's name.
     * @return the human's name.
     */
    public String getName(){
    	return name;
    }
    
    public AbstractModel getModel(){
    	return model;
    }
}
