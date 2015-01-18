import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SimulatorTest {
	

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {        
        ArrayList<Animal> animals = new ArrayList<Animal>();
        Field field = new Field(100, 100);
        Rabbit rabbit1 = new Rabbit(false, field, new Location(50, 50));
        Fox fox1 = new Fox(false, field, new Location(40,40));
        field.place(rabbit1, 50, 50);
        field.place(rabbit1, 40, 40);
	}

}
