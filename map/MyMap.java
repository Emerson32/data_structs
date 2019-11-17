import java.util.ArrayList;

/**
  * An implementation of the Map ADT as taught in Data Structures.
  * 
  * @author Emory Lindsey
  */ 

public class MyMap<K, V>{

	/**
	 * List used to store Map entries
	 */
	private ArrayList<MyMapEntry<K, V>> mapList;

	/**
	 * Current index within the list
	 */
	private int actingIndex;
	
	
	/**
	 * MapEntry class used to represent each key-value pair
	 * within the map.
	 * 
	 * @author Emory Lindsey
	 */
	public static class MyMapEntry<K, V>{
		K key;
		V value;
	}
	
	/**
	 * Creates a new, empty, map.
	 */
	public MyMap()
	{
		mapList = new ArrayList<>();				// Initialize a new Map
		actingIndex = 0;
	}
	
	/**
	 * Private method for searching the map for the provided key.
	 * If the key provided matches a key within the list, findItem(K k) returns 
	 * the corresponding key-value pair and returns null otherwise.
	 * 
	 * @param k The key of the desired item
	 * @return the corresponding map entry, or null if item not found
	 */
	public MyMapEntry<K, V> findItem(K k)
	{
		MyMapEntry<K, V> itemFound = null;
		for(int i = 0; i <= mapList.size() - 1; i++)
		{
			if(mapList.get(i).key == k)
			{
				actingIndex = i;					// Index of the item in current use
				itemFound = mapList.get(i);
				break;
			}
		}
		return itemFound;
	}
	
	/**
	 * Returns the current size of the map.
	 * 
	 * @return integer value representing the size of the map
	 */
	public int size()
	{
		return mapList.size();
	}
	
	/**
	 * Traverses through the map to determine if the provided key is in the map.
	 * 
	 * @param k The key used for the search
	 * @return true if the key is in the map, false otherwise
	 */
	public boolean containsKey(K k)
	{
		boolean result = true;
		
		if(mapList.size() == 0 || null == findItem(k))
		{
			result = false;
		}
		return result;
	}
	
	/**
	 * Searches for a map entry based on the provided key.
	 * 
	 * @param k The key of the target item
	 * @return the corresponding entry value if the map entry exists, null otherwise
	 */
	public V getValue(K k)
	{
		MyMapEntry<K, V> itemFound = findItem(k);
		
		if(null != itemFound)
		{
			return itemFound.value;
		}
		return null;
	}
	
	/**
	 * Adds a new entry to the map based on the provided key-value pair.
	 * If the list already contains the provided key then the 
	 * corresponding value is overridden, otherwise
	 * the new key-value pair is added to the list.
	 *  
	 * @param key Key of the new item
	 * @param value Value of the new item
	 * @return the original entry's value if the key already existed, null otherwise
	 */
	public V add(K k, V val)
	{
		MyMapEntry<K, V> foundItem = findItem(k);
		V changedValue = null;
		
		if(null == foundItem)
		{
			// Create in memory, the new item
			MyMapEntry<K, V> newItem= new MyMapEntry<K, V>();
			newItem.key = k;
			newItem.value = val;
			
			// Add the new item to the list
			mapList.add(newItem);
		}
		else
		{
			changedValue = foundItem.value;
			mapList.get(actingIndex).value = val;
		}
		return changedValue;
	}
	
	/**
	 * Removes an entry from the map based on the provided key.
	 * 
	 * @param k the key of the item to be removed
	 * @return the value of the removed entry if the entry was present, otherwise null
	*/
	
	public V remove(K k)
	{
		MyMapEntry<K, V> targetItem = findItem(k);
		V removedValue = null;
		
		if(null != targetItem)
		{
			removedValue = targetItem.value;
			mapList.remove(targetItem);
		}
		return removedValue;
	}
	
	/**
	 * Retrieves the first entry in the map.
	 * 
	 * @return the first map entry if the map is not empty, null otherwise
	 */
	public MyMapEntry<K,V> getFirst()
	{
		if(mapList.size() == 0)
		{
			return null;
		}
		
		actingIndex = 0;
		return mapList.get(actingIndex);
	}
	
	/**
	 * Retrieves the next entry within the map.
	 * 
	 * @return the next entry if it is within the map, null otherwise
	 */
	public MyMapEntry<K, V> getNext()
	{
		if((actingIndex + 1) < mapList.size())
		{
			actingIndex++;
			return mapList.get(actingIndex);
		}
		return null;
	}
}