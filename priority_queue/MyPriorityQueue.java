import java.util.ArrayList;

/**
 *  An unbounded priority queue based on array list. 
 *  Each item in the priority queue is associated with a priority.
 *  Priorities are represented by integers * with smaller integers for higher priorities. 
 *  
 *  The enqueue operation will ensure items are inserted into the queue according to their priorities.
 *  Items with higher priorities will be placed in front of items with lower priorities. 
 *  
 *  With the enqueue operation does the prioritization of the items in the list, 
 *  the dequeue operation becomes super simple.
 *   
 * @author Emory Lindsey
 *
 */
public class MyPriorityQueue<T> {
	private ArrayList<PQEntry<T>> pqList;
		
	/**
	 * Internal class for priority queue entries.
	 * 
	 * @author Emory Lindsey
	 * 
	 */
	private static class PQEntry<T>
	{
		private int priority;
		private T item;
		
		/**
		 * Constructor.
		 * @param aItem Data item
		 * @param apri Priority of the data item
		 */
		public PQEntry(T aItem, int apri)
		{
			priority = apri;
			item = aItem;
		}

		@Override
		public String toString()
		{
			return "(" + priority + "," + item.toString() + ")";
		}
	}
	
	/**
	 * Constructor for an empty priority queue.
	 */
	public MyPriorityQueue()
	{
		// Create a new, empty pqList
		pqList = new ArrayList<>();
	}
	
	/**
	 * Returns true if the priority queue is empty.
	 * 
	 * @return <code>true</code> if the priority queue contains no item.
	 */
	public boolean isEmpty()
	{
		return pqList.size() == 0;
	}
	
	/**
	 * Returns the number of items in the priority queue.
	 * @return the number of items in the priority queue.
	 */
	public int size()
	{
		return pqList.size();
	}
	
	/**
	 * Enqueues the specified item into the priority queue according to its priority.
	 * @param item 	the item to be enqueued
	 * @param priority	the priority associated with the item
	 */
	public void enqueue(T item, int priority)
	{
		// First create the new item to be added
		PQEntry<T> queueEntry = new PQEntry<>(item, priority);
		
		if(pqList.isEmpty())
		{
			pqList.add(queueEntry);
			return;
		}
		
		// If the priority of the new item is already "higher" than the first
		// item on the queue, then simply make the new item the head of the queue.
		
		if(priority < pqList.get(0).priority)
		{
			pqList.add(0, queueEntry);
			return;
		}
		

		for(int i = pqList.size() - 1; i >= 0; i--)
		{
			if(priority < pqList.get(i).priority)
			{
				continue;
			}
			
			if(priority >= pqList.get(i).priority)
			{
				pqList.add(i + 1, queueEntry);
				break;
			}
		}
	}

	
	/**
	 * Dequeues an item from the priority queue.
	 * The item should be the one with the highest priority among all items
	 * in the priority queue.
	 * 
	 * @return an item with highest priority.
	 */

	public T dequeue()
	{
		T itemRemoved = pqList.get(0).item;
		
		pqList.remove(0);
		return itemRemoved;
	}
	
	@Override
	public String toString()
	{
		return pqList.toString();
	}
}
