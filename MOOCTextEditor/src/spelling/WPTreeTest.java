package spelling;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class WPTreeTest
{
  
  private WPTree wpTree;
  
  @Before
  public void setup()
  {
    wpTree = new WPTree();
  }
  
  @Test
  public void testFindPath()
  {
    List<String> path = wpTree.findPath("time", "theme");
    
    assertEquals("time", path.get(0));
    assertEquals("tie", path.get(1));
    assertEquals("tee", path.get(2));
    assertEquals("thee", path.get(3));
    assertEquals("theme", path.get(4));
    
    for ( String s: path)
    {
      System.out.print(s+" ");
    }
  }
  
  @Test
  public void testFindPath_ImpossiblePath_emptyList()
  {
    List<String> path = wpTree.findPath("asdf", "Hello");
    
    assertEquals(0, path.size());
    
    path = wpTree.findPath("hello", "asdf");
    
    assertEquals(0, path.size());
  }
}
