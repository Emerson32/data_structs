/**
 * Implements a doubly-linked list with
 * reference to head, tail, and current nodes.
 * 
 * @author Emory Lindsey
 *
 */

public class LinkedList {
	private Node head;
	private Node tail;
	private Node current;
	private int size;
	
	private static final Exception exListEmpty = new Exception("List empty");
	private static final Exception exListNoNextitem = new Exception("No next item -- at tail");
	private static final Exception exListNoPrevitem = new Exception("No prev item -- at head");
	private static final Exception exListitemNotFound = new Exception("List item not found");
	
	
	/**
	 * Node class used to represent each node within a linked list
	 */
	private static class Node
	{
		int value;
		Node prev;
		Node next;
		
		/**
		 * The Node(int, Node, Node) Constructor allows you to
		 * create a new Node object by providing an integer value for the new node 
		 * and each node reference variable.
		 * 
		 * @param val the value of the new node
		 * @param p the new node's reference to the previous node in the list
		 * @param n the new node's reference to the next node in the list
		 */
		public Node(int val, Node p,  Node n)
		{
			this.value = val;
			this.prev = p;
			this.next = n;
		}
		
		/**
		 * The Node(int) Constructor allows you to create
		 * a new Node Object by only providing an integer value for the new node
		 * and each node reference variable is set to null
		 * 
		 * @param val
		 */
		public Node(int val)
		{
			this(val, null, null);
		}
	}
	
	
	/**
	 * The PDLinkedList Constructor creates a new, empty linked-list.
	 * This is achieved by setting all link reference variables to null
	 * and setting the size to zero.
	 */
	public PDLinkedList() 
	{
		head = tail= current = null;
		size = 0;
	}
	
	/**
	 * Searches forward from the current node, not including the
	 * current node, for the provided item value.
	 * 
	 * @param value integer value of the target node
	 * @throws Exception exListitemNotFound: thrown if the tail of the list was 
	 * 					encountered before the target node
	 */
	private void searchForward(int value) throws Exception
	{
		Node tempNode = current;
		
		while(tempNode.next != null)
		{
			if(tempNode.next.value == value)
			{
				current = tempNode.next;
				return;
			}
			tempNode = tempNode.next;
		}
		
		// Node not found
		throw exListitemNotFound;
	}
	
	/**
	 * Searches backward from the current node, not including the
	 * current node, for the provided item value.
	 * 
	 * @param value integer value of the target node
	 * @throws Exception exListitemNotFound: thrown if the head of the list was 
	 * 					encountered before the target node
	 */
	private void searchBackward(int value) throws Exception
	{
		Node tempNode = current;
		
		while(tempNode.prev != null)
		{
			if(tempNode.prev.value == value)
			{
				current = tempNode.prev;
				return;
			}
			tempNode = tempNode.prev;
		}
		
		// Node not found
		throw exListitemNotFound;
	}
	
	private void search(int targetValue) throws Exception
	{
		// First start at the head of the list
		this.getHead();
		
		// Find the first occurrence in the list
		Node tempNode = current;		
		while(tempNode != null)
		{
			if(tempNode.value == targetValue)
			{
				current = tempNode;
				break;
			}
			tempNode = tempNode.next;
		}
		
		if(current.value != targetValue)
		{
			// Node not found
			throw exListitemNotFound;
		}
	}
	
	/**
	 * Returns a boolean value that represents whether or not
	 * the calling list is empty.
	 * 
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	/**
	 * Returns the current size of the list.
	 *
	 * @return integer value representing the size of the list
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * Returns a boolean value representing whether or not
	 * the list contains the provided item.
	 * 
	 * @param item integer value of the target node
	 * @return true if the list contains the item, false otherwise
	 */
	public boolean contains(int item)
	{
		Node tempNode = head;
		
		while(tempNode != null)
		{
			if(tempNode.value == item)
				return true;
			
			tempNode = tempNode.next;
		}
		
		return false;
	}
	
	/**
	 * Places a new node at the end of the list.
	 * 
	 * @param item integer value to be stored in the new node
	 */
	public void append(int item) 
	{
		// Create the new node
		Node newNode = new Node(item);
		
		if(this.isEmpty())
		{
			head = newNode;
			tail = newNode;
			current = newNode;
		}
		
		else
		{
			newNode.prev = tail;					// Step 1
			tail.next = newNode;					// Step 2
			tail = newNode;							// Step 3
			
			current = tail;
		}
		
		size ++;
	}
	
	/**
	 * Places a new node at the beginning of the list.
	 * 
	 * @param item integer value to be stored in the new node
	 */
	public void prepend(int item) 
	{
		// Create the new node
		Node newNode = new Node(item);
		
		if(this.isEmpty())
		{
			head = newNode;
			tail = newNode;
			current = newNode;
		}
		
		else
		{
			newNode.next = head;					// Step 1
			head.prev = newNode;					// Step 2
			head = newNode;							// Step 3
			
			current = head;
		}
		
		size ++;
	}

	/**
	 * Searches for the next node, not including the current node,
	 * that contains the provided integer value.
	 * 
	 * @param item integer value of the target node
	 * @throws Exception exListEmpty: thrown if the list is currently empty 
	 */
	public void findNext(int item) throws Exception
	{
		if(this.isEmpty())
		{
			throw exListEmpty;
		}
		
		searchForward(item);
	}
	
	/**
	 * Searches for a previous node, not including the current node,
	 * that contains the provided integer value.
	 * 
	 * @param item integer value of the target node
	 * @throws Exception exListEmpty: thrown if the list is currently empty 
	 */
	public void findPrev(int item) throws Exception
	{
		if(this.isEmpty())
		{
			throw exListEmpty;
		}
		
		searchBackward(item);
	}
	
	/**
	 * Returns the integer value stored within the current node.
	 * 
	 * @return the integer value of the current node
	 * @throws Exception  exListEmpty: thrown if the list is currently empty
	 */
	public int get() throws Exception
	{
		if(this.isEmpty())
		{
			throw exListEmpty;
		}
		return current.value;
	}
	
	/**
	 * Retrieves the head of the list and returns its integer value.
	 * 
	 * @return the integer of the head node
	 * @throws Exception exListEmpty: thrown if the list is currently empty
	 */
	public int getHead() throws Exception
	{
		if(this.isEmpty())
		{
			throw exListEmpty;
		}
		
		current = head;
		return current.value;
	}
	
	/**
	 * Retrieves the tail of the list and returns its integer value.
	 * 
	 * @return the integer value of the tail node
	 * @throws Exception exListEmpty: thrown if the list is currently empty
	 */
	public int getTail() throws Exception
	{
		if(this.isEmpty())
		{
			throw exListEmpty;
		}
		
		current = tail;
		return current.value;
	}
	
	/**
	 * Retrieves the next node in the list and returns its integer value.
	 * 
	 * @return the integer value of the next node
	 * @throws Exception exListEmpty: thrown if the list is currently empty
	 * @throws Exception exListNoNextitem: thrown if the current node is the tail node
	 */
	public int getNext() throws Exception
	{
		if(this.isEmpty())
		{
			throw exListEmpty;
		}
		
		if(current.next == null)
		{
			throw exListNoNextitem;
		}
		
		current = current.next;
		return current.value;
	}
	
	/**
	 * Retrieves the previous node in the list and returns its integer value.
	 * 
	 * @return the integer value of the previous node
	 * @throws Exception exListEmpty: thrown if the list is currently empty
	 * @throws Exception exListNoPrevitem: thrown if the current node is the head node
	 */
	public int getPrev() throws Exception
	{
		if(this.isEmpty())
		{
			throw exListEmpty;
		}
		
		if(current.prev == null)
		{
			throw exListNoPrevitem;
		}
		
		current = current.prev;
		return current.value;
	}
	
	/**
	 * Inserts a new node with the given integer value, before the current node.
	 * 
	 * @param item the integer value of the new node
	 */
	public void insert(int item)
	{
		// Create the new node
		Node newNode = new Node(item);
		
		if(this.isEmpty())
		{	
			head = newNode;
			current = newNode;
			tail = newNode;
		}
		
		else if(current == head)
		{
			// Insert new node at the head of the list
			newNode.next = current;
			current.prev = newNode;
			
			// Make the new node the head and adjust the current pointer
			head = newNode;
			current = head;
		}
		
		else
		{
			// Tie the new node to the current node and the predecessor node
			newNode.next = current;
			newNode.prev = current.prev;
			
			// Tie the current node and the predecessor node to the new node
			current.prev.next = newNode;
			current.prev = newNode;
			
			current = newNode;
		}
		
		size++;
	}
	
	/**
	 * Inserts a new node with the given integer value, after the current node.
	 * 
	 * @param item the integer value of the new node
	 */
	public void add(int item)
	{	
		if(this.isEmpty() || current == tail)
		{
			append(item);
		}
		
		else
		{
			Node newNode = new Node(item);
			
			// Tie the new node to the predecessor node and the successor node
			newNode.prev = current;
			newNode.next = current.next;
			
			// Tie the current node and the predecessor node to the new node
			current.next.prev = newNode;
			current.next = newNode;
			
			current = newNode;
			
			size++;
		}
	}
	
	/**
	 * The remove() method removes the current node from the list and returns its integer value.
	 * 
	 * @return the integer value of the removed node
	 * @throws Exception exListEmpty: thrown if the list is currently empty
	 */
	public int remove() throws Exception
	{
		if(this.isEmpty())
		{
			throw exListEmpty;
		}
		
		// Value to be returned after the removal process
		int removedValue = current.value;
		
		if(this.size == 1)
		{
			head = null;
			current = null;
			tail = null;
		}
		
		// If the current node is the head
		else if(current == head)
		{
			current = current.next;
			
			current.prev = null;
			head = current;
		}
		
		// If the current node is the tail
		else if(current == tail)
		{
			current = current.prev;
			
			current.next = null;
			tail = current;
		}
		
		// If the current node is in the middle of the list
		else
		{
			current.prev.next = current.next;
			current.next.prev = current.prev;
			
			current = current.next;
		}	
		size --;
		
		return removedValue;
	}
	
	/**
	 * The remove(int) method removes an entry with the specified integer value.
	 * 
	 * @param item the integer value of the node to be removed
	 * @throws Exception exListEmpty: thrown if the list is currently empty
	 * @throws Exception exListitemNotFound: thrown if the specified node does not exist
	 */
	public void remove(int item) throws Exception
	{
		if(this.isEmpty())
		{
			throw exListEmpty;
		}
		search(item);
		
		this.remove();
	}
}
