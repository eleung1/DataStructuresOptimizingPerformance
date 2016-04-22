package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary
{

  private LinkedList<String> dict;

  // TODO: Add a constructor
  public DictionaryLL()
  {
    dict = new LinkedList<String>();
  }
  
  /**
   * Add this word to the dictionary. Convert it to lowercase first for the
   * assignment requirements.
   * 
   * @param word
   *          The word to add
   * @return true if the word was added to the dictionary (it wasn't already
   *         there).
   */
  public boolean addWord(String word)
  {
    // TODO: Implement this method
    boolean isSuccess = false;
    
    if ( word != null && !word.trim().equals("") )
    {
      String wordLowerCase = word.toLowerCase();
      
      if ( !dict.contains(wordLowerCase) )
      {
        dict.add(wordLowerCase);
        isSuccess = true;
      }
    }
    return isSuccess;
  }

  /** Return the number of words in the dictionary */
  public int size()
  {
    // TODO: Implement this method
    return dict.size();
  }

  /** Is this a word according to this dictionary? */
  public boolean isWord(String s)
  {
    // TODO: Implement this method
    boolean isWord = false;
    
    if ( s != null && !s.trim().equals("") )
    {
      isWord = dict.contains(s.toLowerCase());
    }
    
    return isWord;
  }

}
