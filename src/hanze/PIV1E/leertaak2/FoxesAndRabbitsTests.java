package hanze.PIV1E.leertaak2;
import static org.junit.Assert.*;
import hanze.PIV1E.leertaak2.animals.Fox;
import hanze.PIV1E.leertaak2.animals.Rabbit;
import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.location.Location;

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
        Field field = new Field(100, 100);
        Rabbit rabbit1 = new Rabbit(false, field, new Location(50, 50));
        Fox fox1 = new Fox(false, field, new Location(40,40));
        field.place(rabbit1, 50, 50);
        field.place(fox1, 40, 40);
	}

}
