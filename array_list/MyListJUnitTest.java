// Emory Lindsey
// CSCI - 3410
// Project1 - List

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyListJUnitTest {
	
	MyList list;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Beginning of Tests...");
		System.out.println();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("End of Tests... ");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("\n\tSetting up...");
		list = new MyList();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Tearing Down...");
		list = null;
	}
	
	@Test
	public void testInitSize() {
		System.out.println("\t\tBeginning testInitSize...\n");
		int size = list.getSize();
		
		assertEquals(0, size);
	}
	
	@Test
	public void getSizeTest()
	{
		System.out.println("\t\tBeginning getSizeTest...\n");
		list.append(24);
		list.append(26);
		list.append(57.8);
		list.insert(0, -28);
		list.insert(5, 2.7);
		
		assertEquals(5, list.getSize());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getItemAtNegativeIndexTest()
	{
		System.out.println("\t\tBeginning getItemAtNegativeIndex...\n");
		list.append(24);
		list.append(64);
		
		list.getItem(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getItemBeyondSizeTest()
	{
		System.out.println("\t\tBeginning getItemBeyondSize...\n");
		// Append one item to the list effectively storing that value
		// at index 0, then attempt to get an item from index 1
		list.append(23);
		list.getItem(1);
	}

	@Test
	public void appendTwoToListTest()
	{
		System.out.println("\t\tBeginning appendTwoToList...\n");
		
		double item_1 = new java.util.Random().nextDouble() % 100;
		double item_2 = new java.util.Random().nextDouble() % 100;
		
		list.append(item_1);
		list.append(item_2);
		
		assertEquals(item_1, list.getItem(0), 0);
		assertEquals(item_2, list.getItem(1), 0);
		
		assertEquals(2, list.getSize());
	}
	
	@Test
	public void appendFiveToListTest()
	{
		System.out.println("\t\tBeginning appendFiveToList...\n");
		double item_1 = 0;
		double item_2 = 1;
		double item_3 = 2;
		double item_4 = 3;
		double item_5 = 4;
		
		list.append(item_1);
		list.append(item_2);
		list.append(item_3);
		list.append(item_4);
		list.append(item_5);
		
		for(int i = 0; i < list.getSize(); i++)
		{
			assertEquals(i , list.getItem(i), 0);
		}
		assertEquals(5, list.getSize());
	}
	
	@Test
	public void insertTwoToEndOfListTest()
	{
		System.out.println("\t\tBeginning insertTwoToEndOfList...\n");
		
		double item_1 = 24;
		double item_2 = 2;
		
		list.insert(0, item_1);
		list.insert(1, item_2);
		
		assertEquals(item_1, list.getItem(0), 0);
		assertEquals(item_2, list.getItem(1), 0);
	}
	
	@Test
	public void insertFiveToEndOfListTest()
	{
		System.out.println("\t\tBeginning insertFiveToEndOfList...\n");
		
		double item_1 = 1;
		double item_2 = 24;
		double item_3 = 72.5;
		double item_4 = 37.8;
		double item_5 = 0;
		
		list.insert(0, item_1);		
		list.insert(1, item_2);
		list.insert(2, item_3);
		list.insert(3, item_4);
		list.insert(4, item_5);

		assertEquals(item_1, list.getItem(0), 0);
		assertEquals(item_2, list.getItem(1), 0);
		assertEquals(item_3, list.getItem(2), 0);
		assertEquals(item_4, list.getItem(3), 0);	
		assertEquals(item_5, list.getItem(4), 0);
	}
	
	@Test
	public void insertThreeToListTest()
	{
		System.out.println("\t\tBeginning insertThreeToList...\n");
		double num1 = 22.8;
		double num2 = 34.5;
		double num3 = 4;
		
		
		// Fill the list with the first two numbers
		list.insert(0, num1);
		list.insert(1, num2);
		
		// Now insert the third number in index 1
		list.insert(1, num3);
		
		assertEquals(num1, list.getItem(0), 0);
		assertEquals(num3, list.getItem(1), 0);
		assertEquals(num2, list.getItem(2), 0);
	}
	
	@Test
	public void insertFiveToListTest()
	{
		System.out.println("\t\tBeginning insertFiveToList...\n");
		double num1 = 22.8;
		double num2 = 34.5;
		double num3 = 4;
		double num4 = 64;
		double num5 = 72;
		
		list.insert(0, num1);
		assertEquals(num1, list.getItem(0), 0);
		
		list.insert(1, num2);
		assertEquals(num2, list.getItem(1), 0);
		
		// Grow the array the first time
		list.insert(3, num3);
		assertEquals(num3, list.getItem(2), 0);
		
		// Check that the list has properly shifted
		// num3 to the right and inserted num4 in its previous position
		list.insert(2, num4);
		assertEquals(num4, list.getItem(2), 0);
		assertEquals(num3, list.getItem(3), 0);
		
		list.insert(4, num5);
		assertEquals(num5, list.getItem(4), 0);
		
		assertEquals(5, list.getSize());
	}
	
	@Test
	public void removeOneFromListTest()
	{
		System.out.println("\t\tBeginning removeOneFromList...\n");
		
		// First initialize some values in the list
		double item1 = 74;
		double item2 = 45;
		double item3 = 2.04;

		list.append(item1);
		list.append(item2);
		list.append(item3);
		
		//Remove Item 2
		list.remove(1);
		
		assertEquals(74, list.getItem(0), 0);
		assertEquals(2.04, list.getItem(1), 0);
	}
	
	@Test
	public void removeTwoFromListTest()
	{
		System.out.println("\t\tBeginning removeTwoFromList...\n");
		
		// Initialize some values
		double item_1 = 34;
		double item_2 = 06;
		double item_3 = 60.0;
		double item_4 = 23.0;
		double item_5 = 0;
		double item_6 = 25;
		
		list.append(item_1);
		list.append(item_2);
		list.append(item_3);
		list.append(item_4);
		list.append(item_5);
		list.append(item_6);
		
		list.remove(0);
		list.remove(1);
		
		assertEquals(6, list.getItem(0), 0);
		assertEquals(23, list.getItem(1), 0);
		assertEquals(0, list.getItem(2), 0);
		assertEquals(25, list.getItem(3), 0);
	}
}

