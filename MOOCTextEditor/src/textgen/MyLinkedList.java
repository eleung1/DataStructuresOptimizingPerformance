package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
	  
	  // Sentinel nodes
	  head = new LLNode<E>(null);
	  tail = new LLNode<E>(null);
	  head.next = tail;
	  tail.prev = head;
	  size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		// TODO: Implement this method
	  add(size, element);
	  return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
	  return getNode(index).data;
	}
	
	/**
	 * Helper method to get node at index.
	 * 
	 * @param index The index of the desired node.
	 * @return The node at the desired index.
	 */
	private LLNode<E> getNode(int index)
	{
	  if ( index < 0 || index > (size -1 ) ) 
    {
      throw new IndexOutOfBoundsException();
    }
	  
	  int count = 0;
    LLNode<E> curr = head.next;
    while ( curr != tail )
    {
      if ( count == index )
      {
        return curr;
      }
      
      curr = curr.next;
      count++;
    }
    
    return null;
	}
	
	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
	  if ( index < 0 || index > size )
	  {
	    throw new IndexOutOfBoundsException();
	  }
	  
	  // Create the new node
	  LLNode<E> newNode = new LLNode<E>(element);
    
	  // index = size means insert at the end of the list
	  if ( index == size )
	  {
	    tail.prev.next = newNode;
	    newNode.prev = tail.prev;
	    tail.prev = newNode;
	    newNode.next = tail;
	  }
	  else if ( index < size)
	  {
	    // insert new node in the specified index and shift the rest of the elements down
	    LLNode<E> existingNode = getNode(index);
	    
	    existingNode.prev.next = newNode;
	    newNode.prev = existingNode.prev;
	    existingNode.prev = newNode;
	    newNode.next = existingNode; 
	  }
	  
	  size++;
	  
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
	  LLNode<E> deadNode = getNode(index);
	  deadNode.prev.next = deadNode.next;
	  deadNode.next.prev = deadNode.prev;
	  size--;
	  
		return deadNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
	  LLNode<E> currNode = getNode(index);
	  E oldData = currNode.data;
	  currNode.data = element;
		return oldData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
