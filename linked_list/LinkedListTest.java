import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LinkedListTest {

	PDLinkedList list;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		list = new PDLinkedList();
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}

	@Test
	public void testIsEmpty() throws Exception
	{
		assertTrue(list.isEmpty());
		
		// add two elements
		list.append(5);
		list.add(7);
		
		assertFalse(list.isEmpty());
		
		list.remove();
		list.remove(5);
		
		list.insert(0);
		
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void testSize() throws Exception
	{
		assertEquals(0, list.size());
		
		// Adding elements to the list and checking the size
		list.append(5);
		assertEquals(1, list.size());
		
		list.insert(7);
		assertEquals(2, list.size());
		
		list.add(8);
		assertEquals(3, list.size());
		
		// now remove those elements and check the size changes
		list.remove(); // remove current, which should be 8
		assertEquals(2, list.size());
		list.remove(5);
		assertEquals(1, list.size());
		list.remove();
		assertEquals(0, list.size());	
	}
	
	@Test
	public void testContains() throws Exception
	{
		assertFalse(list.contains(5));
		
		// Add some elements to the list and check
		list.append(5);
		list.insert(7);
		
		// Add 8 to the middle 
		list.add(8);
		
		assertTrue(list.contains(5));
		assertTrue(list.contains(7));
		assertTrue(list.contains(8));

		// now remove those elements and check the size changes
		list.remove(); // remove current, which should be 8
		list.remove(5);
		list.remove();
		assertFalse(list.contains(5));
		assertFalse(list.contains(7));
		assertFalse(list.contains(8));
	}

	@Test
	public void testAppend() throws Exception
	{
		list.append(5);
		list.append(7);
		list.append(8);
		
		assertEquals(5, list.getHead());
		assertEquals(7, list.getNext());
		assertEquals(8, list.getNext());
	}
	
	@Test
	public void testPrepend() throws Exception
	{
		list.prepend(5);
		list.prepend(7);
		list.prepend(8);
		
		assertEquals(8, list.get());
		assertEquals(7, list.getNext());
		assertEquals(5, list.getNext());
	}
	
	@Test
	public void testFindNextPrevious() throws Exception
	{
		
		list.append(5);
		list.append(7);
		list.append(8);
		list.getHead(); // set current to head
		// first trying to find something not in the list
		// current pointer should not move
		
		try {
			list.findNext(9);
			fail("Why exception wasn’t thrown before this point?");
		} 
		catch (Exception ex) {
			assertEquals("List item not found", ex.getMessage());
		}
		// find next two items that should be in the list
		list.findNext(7);
		list.findNext(8);
		assertEquals(8, list.get());
		
		try {
			// finding same again should turn out no result
			list.findNext(8);
			fail("next 8 does not exist -- why not throwing exception?");
		} 
		catch (Exception ex) {
			assertEquals("List item not found", ex.getMessage());
		}
		
		// find an item not in the list going backward
		try {
			list.findPrev(9);
			fail("Why exception wasn’t thrown before this point?");
		} 
		catch (Exception ex) {
			assertEquals("List item not found", ex.getMessage());
		}
		
		// find previous two items that should be in the list
		list.findPrev(7);
		list.findPrev(5);
		assertEquals(5, list.get());
		try {
			// finding same again should turn on no result
			list.findPrev(5);
			fail("prev 5 does not exist -- why not throwing exception?");
		} 
		catch (Exception ex) {
			assertEquals("List item not found", ex.getMessage());
		}
	}
	
	@Test
	public void testGet() throws Exception
	{
		try
		{
			list.get();
		}
		catch(Exception ex)
		{
			assertEquals("List empty", ex.getMessage());
		}
		
		list.append(5);
		list.add(6);
		list.insert(7);
		
		// Sets the current to head
		list.getHead();
		assertEquals(5, list.get());
		
		list.getTail();
		assertEquals(6, list.get());
	}
	
	@Test
	public void testGetHeadTail() throws Exception
	{
		try
		{
			list.getHead();
		}
		catch(Exception ex)
		{
			assertEquals("List empty", ex.getMessage());
		}
		
		try
		{
			list.getTail();
		}
		catch(Exception ex)
		{
			assertEquals("List empty", ex.getMessage());
		}
		
		list.append(5);
		list.add(6);
		list.insert(7);
		
		assertEquals(5, list.getHead());
		assertEquals(6, list.getTail());
	}
	
	@Test
	public void testGetPrevNext() throws Exception
	{
		try
		{
			list.getPrev();
		}
		catch(Exception ex)
		{
			assertEquals("List empty", ex.getMessage());
			if (!"List empty".equals(ex.getMessage()))
            	System.out.println("ERROR - wrong exception thrown! (Empty - getPrev())");
		}
		
		try
		{
			list.getNext();
		}
		catch(Exception exc)
		{
			assertEquals("List empty", exc.getMessage());
			if (!"List empty".equals(exc.getMessage()))
            	System.out.println("ERROR - wrong exception thrown! (Empty - getNext())");
		}
		
		// list: 5, 7, 6
		list.append(5);
		list.add(6);
		list.insert(7);
		
		assertEquals(5, list.getPrev());
		try
		{
			list.getPrev();
		}
		catch(Exception exce)
		{
			assertEquals("No prev item -- at head", exce.getMessage());
            if (!"No prev item -- at head".equals(exce.getMessage()))
            	System.out.println("ERROR - wrong exception thrown! (No prev item)");
		}
		
		assertEquals(7, list.getNext());
		assertEquals(6, list.getNext());
		
		try
		{
			list.getNext();
		}
		catch(Exception excep)
		{
			assertEquals("No next item -- at tail",excep.getMessage());
			if (!"No next item -- at tail".equals(excep.getMessage()))
            	System.out.println("ERROR - wrong exception thrown! (No next item)");
		}
		
		assertEquals(7, list.getPrev());
	}
	
	@Test
	public void testInsertAdd() throws Exception
	{
		// list: 5,6,7,8
		
		list.insert(5);
		list.add(6);
		list.add(8);
		list.insert(7);
		
		assertEquals(5, list.getHead());
		assertEquals(8, list.getTail());
		assertEquals(7, list.getPrev());
		list.getHead();
		assertEquals(6, list.getNext());
		assertEquals(7, list.getNext());
		assertEquals(6, list.getPrev());
		
	}
	
	@Test
	public void testRemoves() throws Exception
	{
		// Try to remove an item from empty list with remove()
		try 
		{
			list.remove();
		}
		catch(Exception ex)
		{
			assertEquals("List empty", ex.getMessage());
		}
		
		// Try to remove an item from empty list with remove(item)
		try
		{
			list.remove(7);
		}
		catch(Exception ex)
		{
			assertEquals("List empty", ex.getMessage());
		}
		
		// list: 5, 6, 7, 8
		list.insert(5);
		list.add(6);
		list.add(8);
		list.insert(7);
		
		// Current should be 8 after this
		int removed = list.remove();
		assertEquals(7, removed);
		assertEquals(8, list.get());
		
		// Remove tail and current should be new tail
		removed = list.remove();
		assertEquals(8, removed);
		assertEquals(6, list.get());
		list.append(7);
		list.append(8);
		
		// Current set to head
		list.getHead();
		removed = list.remove();
		assertEquals(5, removed);
		assertEquals(6, list.get());
		
		list.prepend(5);
		list.getTail();
		list.remove(8);
		list.remove(5);
		assertEquals(6, list.getHead());
		assertEquals(7, list.getTail());
	}
}
