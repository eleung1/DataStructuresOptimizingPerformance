package spelling;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class NearbyWordsTest
{
  private NearbyWords nearbyWords;
  private DictionaryBST smallDict;
  
  @Before
  public void setup()
  {
    smallDict = new DictionaryBST();
    smallDict.addWord("hi");
    nearbyWords = new NearbyWords(smallDict);
  }
  
  public void testInsertions()
  {
    
  }
  
  @Test
  public void testDeletion()
  {
    String s = "hi";
    List<String> currentList = new ArrayList<String>();
    nearbyWords.deletions(s, currentList, false);
    for ( String word : currentList)
    {
      System.out.println(word);
    }
  }
}
