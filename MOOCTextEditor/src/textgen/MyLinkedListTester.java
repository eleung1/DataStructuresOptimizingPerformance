/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester
{

  private static final int LONG_LIST_LENGTH = 10;

  MyLinkedList<String> shortList;
  MyLinkedList<Integer> emptyList;
  MyLinkedList<Integer> longerList;
  MyLinkedList<Integer> list1;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception
  {
    // Feel free to use these lists, or add your own
    shortList = new MyLinkedList<String>();
    shortList.add("A");
    shortList.add("B");
    emptyList = new MyLinkedList<Integer>();
    longerList = new MyLinkedList<Integer>();
    for (int i = 0; i < LONG_LIST_LENGTH; i++)
    {
      longerList.add(i);
    }
    list1 = new MyLinkedList<Integer>();
    list1.add(65);
    list1.add(21);
    list1.add(42);

  }

  /**
   * Test if the get method is working correctly.
   */
  /*
   * You should not need to add much to this method. We provide it as an example
   * of a thorough test.
   */
  @Test
  public void testGet()
  {
    // test empty list, get should throw an exception
    try
    {
      emptyList.get(0);
      fail("Check out of bounds");
    }
    catch (IndexOutOfBoundsException e)
    {

    }

    // test short list, first contents, then out of bounds
    assertEquals("Check first", "A", shortList.get(0));
    assertEquals("Check second", "B", shortList.get(1));

    try
    {
      shortList.get(-1);
      fail("Check out of bounds");
    }
    catch (IndexOutOfBoundsException e)
    {

    }
    try
    {
      shortList.get(2);
      fail("Check out of bounds");
    }
    catch (IndexOutOfBoundsException e)
    {

    }
    // test longer list contents
    for (int i = 0; i < LONG_LIST_LENGTH; i++)
    {
      assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
    }

    // test off the end of the longer array
    try
    {
      longerList.get(-1);
      fail("Check out of bounds");
    }
    catch (IndexOutOfBoundsException e)
    {

    }
    try
    {
      longerList.get(LONG_LIST_LENGTH);
      fail("Check out of bounds");
    }
    catch (IndexOutOfBoundsException e)
    {
    }

  }

  /**
   * Test removing an element from the list. We've included the example from the
   * concept challenge. You will want to add more tests.
   */
  @Test
  public void testRemove()
  {
    int a = list1.remove(0);
    assertEquals("Remove: check a is correct ", 65, a);
    assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
    assertEquals("Remove: check size is correct ", 2, list1.size());

    // TODO: Add more tests here
    try
    {
      emptyList.remove(0);
      fail("Check out of bounds");
    }
    catch ( IndexOutOfBoundsException e )
    {
      
    }
    
    Integer fifthNodeValue = longerList.remove(4);
    assertEquals((Integer)4, fifthNodeValue);
    assertEquals((Integer)5, longerList.get(4));
    assertEquals(9, longerList.size);
  }

  /**
   * Test adding an element into the end of the list, specifically public
   * boolean add(E element)
   */
  @Test
  public void testAddEnd()
  {
    // TODO: implement this test

    // Existing end node before we add the new node
    LLNode<String> oldEndNode = shortList.tail.prev;

    boolean rc = shortList.add("Z");

    // Successful add operation should return true
    assertTrue(rc);

    // Last element should have value "Z"
    assertEquals("Z", shortList.tail.prev.data);

    // Old end node should be the prev node of new end node
    assertEquals(oldEndNode, shortList.tail.prev.prev);

    // Old end node's next node should be the new end node
    assertEquals(shortList.tail.prev, oldEndNode.next);
  }

  /** Test the size of the list */
  @Test
  public void testSize()
  {
    // TODO: implement this test
    assertEquals(0, emptyList.size());
    emptyList.add(1);
    assertEquals(1, emptyList.size());
    
    assertEquals(2, shortList.size());
    shortList.remove(1);
    assertEquals(1, shortList.size());
    
    shortList.add("C");
    shortList.add("D");
    assertEquals(3, shortList.size());
    
    shortList.set(2, "E");
    assertEquals(3, shortList.size());
  }

  /**
   * Test adding an element into the list at a specified index, specifically:
   * public void add(int index, E element)
   */
  @Test
  public void testAddAtIndex()
  {
    // TODO: implement this test

    // Adding at an out-of-bound index should throw IndexOutOfBoundException
    try
    {
      emptyList.add(1, 100);
      fail("Check out of bounds");
    }
    catch(IndexOutOfBoundsException e)
    {
      
    }
    
    // Adding to the first of an emptyList
    emptyList.add(0, 999);
    LLNode<Integer> first = emptyList.head.next;
    assertEquals((Integer)999, first.data); 
    
    // Adding to the middle of an existing list
    // original list: 0 1 2 3 4 5 6 7 8 9
    Integer fifthNodeValue = longerList.get(4);
    assertEquals((Integer)4, fifthNodeValue);
    longerList.add(4, 999);
    // new list: 0 1 2 3 999 4 5 6 7 8 9
    assertEquals((Integer)999, longerList.get(4));
    assertEquals((Integer)3, longerList.get(3));
    assertEquals((Integer)4, longerList.get(5));
  }

  /** Test setting an element in the list */
  @Test
  public void testSet()
  {
    // TODO: implement this test
    try
    {
      emptyList.set(0, 999);
      fail("Check out of bounds");
    }
    catch ( IndexOutOfBoundsException e)
    {
      
    }
    
    String oldValueA = shortList.set(0, "AAA");
    assertEquals("A", oldValueA);
    assertEquals("AAA", shortList.get(0));
    
    String oldValueB = shortList.set(1, "BBB");
    assertEquals("B", oldValueB);
    assertEquals("BBB", shortList.get(1));
  }

  // TODO: Optionally add more test methods.

}
