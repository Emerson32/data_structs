// Emory Lindsey
// CSCI - 3410
// Project1 - List

public class MyList {
	
	private int size;
	private int capacity;
	private double[] listArray;

	/**
	 * Default Constructor for MyList class.
	 * Initializes an empty list with a capacity of two.
	 */
	public MyList()
	{
		capacity = 2;
		size = 0;
		
		listArray = new double[capacity];
	}
	
	/**
	 * Returns the current size of the list.
	 * @return the current number of elements in the list
	 */
	public int getSize()
	{
		return size;
	}
		
	/**
	 * Returns an item at a given index.
	 * @param idx Index of the element to return.
	 * @return The value of the given index
	 */
	public double getItem(int idx)
	{	
		if(idx >= size || idx < 0) 
		{
			throw new IndexOutOfBoundsException();
		}
		return listArray[idx];
	}
	
	/**
	 * Private method for expanding the list when necessary.
	 */
	private void expand()
	{
		double [] tempArray = new double[2 * capacity];
		
		for(int i = 0; i < size; i++)
		{
			tempArray[i] = listArray[i];
		}
		
		// Point to the new array
		listArray = tempArray;	
		
		capacity *= 2;
	}
	
	/**
	 * Appends a value to the end of the list.
	 * @param num The number to append to the end of the list.
	 */
	public void append(double num)
	{
		// If the list does not need to be expanded
		// before appending
		if(size == capacity)
		{		
			expand();
		}
		
		listArray[size] = num;
		size++;
	}
	
	/**
	 * Insert an element within the list using a specific index.
	 * @param idx Where to insert the value
	 * @param num The value to insert
	 */
	public void insert(int idx, double num)
	{
		// Exception thrown if the user tries to insert a value
		// at a negative index
		if(idx < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		
		// Similar to first append operation
		if(idx >= size)
		{
			append(num);
			return;
		}
		
		if(size == capacity)
		{
			expand();
		}
			
		// Start at the rightmost index
		// and shift elements to the right
		for(int i = size; i > idx; i--)
		{
			listArray[i] = listArray[i-1];	
		}
		
		listArray[idx] = num;
		size++;
	}
	
	/**
	 * Removes an element within the list using a specific index.
	 * The removed element is returned after removal for the user's use.
	 * @param idx Which element within the list to remove
	 * @return The value of the element removed
	 */
	public double remove(int idx)
	{
		if(idx < 0 || idx > size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		double removedValue = listArray[idx];
		
		// Begin with the given index and iterate
		// to the end of the list
		for(int i = idx; i < size - 1; i++)
		{
			listArray[i] = listArray[i+1];
//			listArray[i+1] = 0;
		}
		
		size--;
		return removedValue;
	}
}
