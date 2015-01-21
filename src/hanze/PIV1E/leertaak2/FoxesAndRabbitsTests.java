package hanze.PIV1E.leertaak2;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import hanze.PIV1E.leertaak2.actor.*;
import hanze.PIV1E.leertaak2.location.*;

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
        Field field = new Field(100, 100);
        Rabbit rabbit1 = new Rabbit(false, field, new Location(50, 50));
        Fox fox1 = new Fox(false, field, new Location(40,40));
        int foodLevel = fox1.getFoodLevel();
        
        //simulate 1 step
        rabbit1.act();
        fox1.act();
        
        //test conditions
        assertTrue(rabbit1.getAge() == 1);
        assertTrue(foodLevel == fox1.getFoodLevel() + 1);
        assertTrue(fox1.getAge() == 1);
        
        //teleport the fox next to the rabbit, the fox should eat the rabbit when he acts
        fox1.setLocation(new Location(50,49));
        fox1.act();
        assertFalse(rabbit1.isAlive());
        
        //reset the field and the animals
        field.clear();
        fox1 = null;
        rabbit1 = null;
        
        //create 4 foxes and make 1 unable to move, this way he should die
        fox1 = new Fox(false, field, new Location(0,0));
        Fox fox2 = new Fox(false, field, new Location(1,0));
        Fox fox3 = new Fox(false, field, new Location(0,1));
        Fox fox4 = new Fox(false, field, new Location(1,1));
        fox1.act();
        assertFalse(fox1.isAlive());
        
        //reset the field and the animals
        field.clear();
        fox1 = null;
        fox2 = null;
        fox3 = null;
        fox4 = null;
        
        //make a rabbit and let him live until he should die
        rabbit1 = new Rabbit(false, field, new Location(50, 50));
        for(int step = 0; step <= rabbit1.MAX_AGE ; step++) {
            rabbit1.act();
        }
        assertFalse(rabbit1.isAlive());
        
        
	}

}
