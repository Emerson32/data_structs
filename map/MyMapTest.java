import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyMapTest {
	
	MyMap<Integer, String> mm;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mm = new MyMap<>();
	}

	@After
	public void tearDown() throws Exception {
		mm = null;
	}

	@Test
	public void testSize()
	{
		assertEquals(0, mm.size());
		mm.add(0, "0");
		assertEquals(1, mm.size());
		mm.add(1,  "1");
		assertEquals(2, mm.size());
	}

	@Test
	public void testContainsKey()
	{
		mm.add(0, "0");
		mm.add(1, "1");
		
		assertTrue(mm.containsKey(1));
		assertTrue(mm.containsKey(0));
		assertFalse(mm.containsKey(2));
	}
	
	@Test
	public void testGetValue()
	{
		mm.add(0, "0");
		mm.add(1, "1");
		assertEquals("0", mm.getValue(0));
		assertEquals("1", mm.getValue(1));
		assertEquals(null, mm.getValue(2));
	}
	
	@Test
	public void testAdd()
	{
		// add -- key does not already exist in the map
		assertEquals(null, mm.add(0,  "0"));
		assertEquals("0", mm.getValue(0));
		assertEquals(null, mm.add(1,  "1"));
		assertEquals("1", mm.getValue(1));
		
		// add -- key already exists in the map
		assertEquals("0", mm.add(0, "zero"));
		assertEquals("zero", mm.getValue(0));
		assertEquals("1", mm.add(1, "one"));
		assertEquals("one", mm.getValue(1));
	}

	@Test
	public void testRemove()
	{
		// test remove - key exists
		mm.add(0, "0");
		mm.add(1, "1");
		assertEquals("0", mm.remove(0));
		assertFalse(mm.containsKey(0));
		assertEquals("1", mm.remove(1));
		assertFalse(mm.containsKey(1));
		
		// test remove - key does not exist
		assertEquals(null, mm.remove(3));
	}
	
	@Test
	public void testIteration()
	{
		MyMap.MyMapEntry<Integer, String> e;
		
		
		mm.add(0, "0");
		mm.add(1, "1");
		mm.add(2, "2");
		mm.add(3, "3");
		mm.add(4, "4");
		mm.add(5, "5");
		
		e = mm.getFirst();
		while(e != null)
		{
			assertEquals(String.valueOf(e.key), e.value);
			e = mm.getNext();
		}

		// test iteration over a mutated map
		mm.remove(0);
		mm.remove(1);
		mm.remove(5);
		
		e = mm.getFirst();
		while(e != null)
		{
			assertEquals(String.valueOf(e.key), e.value);
			e = mm.getNext();
		}
		
		// test iteration over an empty map
		mm.remove(4);
		mm.remove(3);
		mm.remove(2);
		
		e = mm.getFirst();
		while(e != null)
		{
			assertEquals(String.valueOf(e.key), e.value);
			e = mm.getNext();
		}
	}
}
