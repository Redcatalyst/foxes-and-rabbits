package hanze.PIV1E.leertaak2.model;

import hanze.PIV1E.leertaak2.actor.*;
import hanze.PIV1E.leertaak2.helper.*;
import hanze.PIV1E.leertaak2.location.*;
import hanze.PIV1E.leertaak2.music.*;
import hanze.PIV1E.leertaak2.view.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * The heart of the application. The SimulationModel contains all the data and
 * uses it simulate a step. When the data inside the model changes all the views
 * will get notified. Every action is given by a controller.
 * @author Frank
 */
public class SimulationModel extends AbstractModel {
	// The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.04;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08; 
    // The probability that a bear will be created in any given grid position.
    private static final double BEAR_CREATION_PROBABILITY = 0.01; 
    // The number of hunters that are hunting in the woods
    private static final int NUMBER_OF_HUNTERS = 20; 
    // The number of Tourist that are in the woods
    private static final int NUMBER_OF_TOURISTS = 1; 
    // The probability that a rabbit is infected when created
    public static final double RABBIT_INFECTED_PROBABILITY = 0.01;
    public static double rabbit_infected_probability = RABBIT_INFECTED_PROBABILITY;
    // The probability a tourist will visit the woods.
    public static final double TOURIST_VISIT_PROBABILITY = 0.001;
    public static double tourist_visit_probability = TOURIST_VISIT_PROBABILITY;
    // A random number generator to control the number of tourists.
    private static final Random rand = Randomizer.getRandom();
    // List of actors in the field.
    public static List<Actor> actors;
    // List of new actors
    public static List<Actor> newActors;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A statistics object computing and storing simulation information
    private FieldStats stats;
    // Plays sound
    private MusicHandler musicHandler = new MusicHandler(this);
    // all the sounds
    public static MusicFile backgroundSound, rabbit, fox, hunter, bear, tourist, fire;
    // Booleans if the settings menus are on or off
    public static boolean sound, settings = false;
    
    /**
     * Creates a SimulatinModel
     * @param depth The width of the field
     * @param width The height of the field
     */
    public SimulationModel(int depth, int width){
    	actors = new ArrayList<Actor>();
        newActors = new ArrayList<Actor>();
        
        field = new Field(depth, width);
        stats = new FieldStats();
        initSound();
    }
    
    /**
     * Initializes all the sounds.
     */
    public void initSound() {
    	//background
    	backgroundSound = new MusicFile("resources/sound/wind.wav", musicHandler, 40, "Background");
        musicHandler.startInfinite(backgroundSound);
        
        rabbit =	new MusicFile("resources/sound/rabbit.wav",	musicHandler, 70, "Rabbit");
        fox =		new MusicFile("resources/sound/fox.wav",	musicHandler, 70, "Fox");
        hunter =	new MusicFile("resources/sound/hunter.wav",	musicHandler, 70, "Hunter");
        bear =		new MusicFile("resources/sound/bear.wav",	musicHandler, 70, "Bear");
        tourist =	new MusicFile("resources/sound/tourist.wav",musicHandler, 70, "Tourist");
        fire =		new MusicFile("resources/sound/fire.wav",	musicHandler, 70, "Fire");
    }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation()
    {
        simulate(4000);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
    	for(int step = 1; step <= numSteps && isViable(field); step++) {
            simulateOneStep();
        }
    }
    
    /**
     * Run the simulation from its current state for a single step. 
     * Iterate over the whole field updating the state of each actor.
     * Every step the views get notified of the changes.
     */
    public void simulateOneStep()
    {
    	step++;
    	actors.addAll(newActors);
    	// Decide if a tourist will visit the (dangerous) woods.
    	if(rand.nextDouble() <= tourist_visit_probability) {
    		Tourist tourist = new Tourist(field, field.getFreeLocation(), this);
        	actors.add(tourist);
    	}
        // Let all Actors act.
    	newActors.clear();
        for(Iterator<Actor> it = actors.iterator(); it.hasNext(); ) {
            Actor actor = it.next();
            actor.act();
            if(!actor.isAlive()) {
                it.remove();
            }
        }
        actors.addAll(newActors);

        stats.reset();
        notifyViews();
        if(Randomizer.getRandom().nextDouble() > 0.75) {
    		playMostPopulatedSound();
    	}
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        actors.clear();
        populate();
        
        notifyViews();
    }
    
    /**
     * Randomly populate the field with actors.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location, this);
                    actors.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location, this);
                    rabbit.setInfection(rand.nextDouble() <= rabbit_infected_probability);
                    actors.add(rabbit);
                }
                else if(rand.nextDouble() <= BEAR_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Bear bear = new Bear(true, field, location, this);
                    actors.add(bear);
                }
                // else leave the location empty.
            }
        }
        // Add the hunters to the field
        for(int i = 0; i < NUMBER_OF_HUNTERS; i++){
        	Hunter hunter = new Hunter(field, field.getFreeLocation(), this);
        	actors.add(hunter);
        }
        
        // Add the tourists to the field
        for(int i = 0; i < NUMBER_OF_TOURISTS; i++){
            Tourist tourist = new Tourist(field, field.getFreeLocation(), this);
            actors.add(tourist);
        }

    }
    
    /**
     * Plays the sound of the actor which has the biggest population.
     */
    private void playMostPopulatedSound() {
	    Actor biggest = null;
	    for(Actor actor : actors) {
	    	if(biggest == null) {
	    		biggest = actor;
	    	} else if(stats.getPopulationCount(field, actor.getClass()) > stats.getPopulationCount(field, biggest.getClass())) {
	    		biggest = actor;
	    	}
	    }
	    if(biggest != null) {
	    	musicHandler.start(biggest.getSound());
	    }
    }
    
    /**
     * Manually adds a rabbit to the field at a random location.
     */
    public void addRabbit() {
    	newActors.add(new Rabbit(false, field, field.getFreeLocation(), this));
    }
    
    /**
     * Manually adds a fox to the field at a random location.
     */
    public void addFox() {
    	newActors.add(new Fox(false, field, field.getFreeLocation(), this));
    }
    
    /**
     * Manually adds a bear to the field at a random location.
     */
    public void addBear() {
    	newActors.add(new Bear(false, field, field.getFreeLocation(), this));
    }
    
    /**
     * Manually adds a hunter to the field at a random location.
     */
    public void addHunter() {
    	newActors.add(new Hunter(field, field.getFreeLocation(), this));
    }
    
    /**
     * Manually adds a tourist to the field at a random location.
     */
    public void addTourist() {
    	newActors.add(new Tourist(field, field.getFreeLocation(), this));
    }
    
    /**
     * Determine whether the simulation should continue to run.
     * @param field The field that should be checked.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        return stats.isViable(field);
    }
    
    /**
	 * Gives the field the simulation works in
	 * @return the field.
	 */
    public Field getField(){
    	return field;
    }
    
    /**
	 * Gives the current step of the simulation.
	 * @return the step.
	 */
	public int getStep(){
		return step;
	}
	
	/**
	 * Gives the fieldstats the simulation works in
	 * @return the fieldstats.
	 */
	public FieldStats getStats(){
		return stats;
	}
	
	/**
	 * Gives the music handler the SimulationModel is working with.
	 * @return the musichandler
	 */
	public MusicHandler getMusicHandler() {
		return musicHandler;
	}
	
	/**
	 * Opens a settings window.
	 */
	public void setSettings() {
		if(settings != true){
			new SimulationSettings(this);
		}
	}
	
	/**
	 * Listens to all the events created by the settings window.
	 */
	public class SettingsListener implements WindowListener {

		/**
		 * When a settings window closes a new one can be opened.
		 */
		public void windowClosed(WindowEvent e) {
			SimulationModel.settings = false;			
		}
		
		public void windowOpened(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
		
	}
	
	/**
	 * Listens to all the events created by the sound window.
	 */
	public class SoundListener implements WindowListener {

		/**
		 * When a sound window closes a new one can be opened.
		 */
		public void windowClosed(WindowEvent e) {
			SimulationModel.sound = false;			
		}
		
		public void windowOpened(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
		
	}
}
