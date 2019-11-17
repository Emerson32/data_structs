import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyPriorityQueueTest {
	MyPriorityQueue<String> pq;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		pq = new MyPriorityQueue<>();
	}

	@After
	public void tearDown() throws Exception {
		pq = null;
	}
	
	private void buildQueue()
	{
		pq.enqueue("abc", -1);
		pq.enqueue("cde", 100);
		pq.enqueue("xyz", 55);
	}

	@Test
	public void testIsEmpty()
	{
		assertTrue(pq.isEmpty());
		buildQueue();
		assertFalse(pq.isEmpty());
	}
	
	@Test
	public void testSize()
	{
		assertEquals(0, pq.size());
		buildQueue();
		assertEquals(3, pq.size());
	}
	
	@Test
	public void testEnqueue()
	{
		pq.enqueue("abc", 0);
		pq.enqueue("xyz", 100);
		pq.enqueue("fg", -1);
		pq.enqueue("cde", 0);
	}
	
	@Test
	public void testDequeue()
	{
		pq.enqueue("abc", -1);
		pq.enqueue("cde", 100);
		pq.enqueue("xyz", 55);
		
		assertEquals("abc", pq.dequeue());
		assertEquals("xyz", pq.dequeue());
		
		pq.enqueue("ttt", 33);
		assertEquals("ttt", pq.dequeue());
		
		pq.enqueue("ttt", 133);
		assertEquals("cde", pq.dequeue());
	}
	
}
