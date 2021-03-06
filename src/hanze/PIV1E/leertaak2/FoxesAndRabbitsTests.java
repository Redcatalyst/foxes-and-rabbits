package hanze.PIV1E.leertaak2;
import static org.junit.Assert.*;
import hanze.PIV1E.leertaak2.actor.*;
import hanze.PIV1E.leertaak2.location.*;
import hanze.PIV1E.leertaak2.model.SimulationModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FoxesAndRabbitsTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//create the objects needed to simulate
		SimulationModel simulation = new SimulationModel(100, 100);
        Field field = new Field(100, 100);
        Rabbit rabbit1 = new Rabbit(false, field, new Location(50, 50), simulation);
        Fox fox1 = new Fox(false, field, new Location(40,40), simulation);
        Bear bear1 = new Bear(false, field, new Location(60,60), simulation);
        int foodLevelFox = fox1.getFoodLevel();
        int foodLevelBear = bear1.getFoodLevel();

        
        //simulate 1 step
        rabbit1.act();
        fox1.act();
        bear1.act();
        
        //test if animals age
        assertTrue(rabbit1.getAge() == 1);
        assertTrue(foodLevelFox == fox1.getFoodLevel() + 1);
        assertTrue(fox1.getAge() == 1);
        assertTrue(foodLevelBear == bear1.getFoodLevel() + 1);
        assertTrue(bear1.getAge() == 1);
        
        //teleport the fox next to the rabbit, the fox should eat the rabbit when he acts
        fox1.setLocation(new Location(50,49));
        fox1.act();
        assertFalse(rabbit1.isAlive());
        
        //reset the field and the animals
        field.clear();
        fox1 = null;
        rabbit1 = null;
        
        //create 4 foxes and make 1 unable to move, this way he should die
        fox1 = new Fox(false, field, new Location(0,0), simulation);
        new Fox(false, field, new Location(1,0), simulation);
        new Fox(false, field, new Location(0,1), simulation);
        new Fox(false, field, new Location(1,1), simulation);
        fox1.act();
        assertFalse(fox1.isAlive());
        
        //reset the field and the animals
        field.clear();

        //create 4 bears and make 1 unable to move, this way he should die
        bear1 = new Bear(false, field, new Location(0,0), simulation);
        new Bear(false, field, new Location(1,0), simulation);
        new Bear(false, field, new Location(0,1), simulation);
        new Bear(false, field, new Location(1,1), simulation);
        bear1.act();
        assertFalse(bear1.isAlive());        
        
        //reset the field and the animals
        field.clear();
    
        /* make a rabbit and let him live until he should die
        rabbit1 = new Rabbit(false, field, new Location(50, 50));
        for(int step = 0; step <= rabbit1.MAX_AGE ; step++) {
           rabbit1.act();
        }
        assertFalse(rabbit1.isAlive());
        */
        
        // Check if a fox eats a rabbit
        rabbit1 = new Rabbit(false, field, new Location(0,0), simulation);
        fox1 = new Fox(false, field, new Location(0,1), simulation);
        fox1.act();
        assertFalse(rabbit1.isAlive());
        
        //reset the field and the animals
        field.clear();
        rabbit1 = null;
        fox1 = null;
        
        // Check if a bear eats a rabbit
        rabbit1 = new Rabbit(false, field, new Location(0,0), simulation);
        bear1 = new Bear(false, field, new Location(0,1), simulation);
        bear1.act();
        assertFalse(rabbit1.isAlive());
        
        //reset the field and the animals
        field.clear();
        rabbit1 = null;
        bear1 = null;
        
        // Check if a bear eats a fox
        fox1 = new Fox(false, field, new Location(0,0), simulation);
        bear1 = new Bear(false, field, new Location(0,1), simulation);
        bear1.act();
        assertFalse(fox1.isAlive());
        
        //reset the field and the animals
        field.clear();
        fox1 = null;
        bear1 = null;
        
        // Check if a hunter hunts for a rabbit
        rabbit1 = new Rabbit(false, field, new Location(0,0), simulation);
        Hunter hunter1 = new Hunter(field, new Location(0,1), simulation);
        hunter1.act();
        assertFalse(rabbit1.isAlive());
        
        //reset the field and the animals
        field.clear();
        rabbit1 = null;
        hunter1 = null;
        
        // Check if a hunter hunts for a fox
        fox1 = new Fox(false, field, new Location(0,0), simulation);
        hunter1 = new Hunter(field, new Location(0,1), simulation);
        hunter1.act();
        assertFalse(fox1.isAlive());
        
        //reset the field and the animals
        field.clear();
        fox1 = null;
        hunter1 = null;
        
        // Check if a hunter hunts for a bear
        bear1 = new Bear(false, field, new Location(0,0), simulation);
        hunter1 = new Hunter(field, new Location(0,1), simulation);
        hunter1.act();
        assertFalse(bear1.isAlive());
        
        //reset the field and the animals
        field.clear();
        bear1 = null;
        hunter1 = null;
        
        
        
	}

}
