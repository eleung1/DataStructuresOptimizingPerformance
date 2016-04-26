/**
 * 
 */
package spelling;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

/**
 * A class that implements the Dictionary interface with a HashSet
 */
public class DictionaryHashSetMatchCase implements Dictionary
{

  private HashSet<String> words;

  public DictionaryHashSetMatchCase()
  {
    words = new HashSet<String>();
  }

  /**
   * Add this word to the dictionary.
   * 
   * @param word
   *          The word to add
   * @return true if the word was added to the dictionary (it wasn't already
   *         there).
   */
  @Override
  public boolean addWord(String word)
  {
    return words.add(word);
  }

  /** Return the number of words in the dictionary */
  @Override
  public int size()
  {
    return words.size();
  }

  /**
   * Is this a word according to this dictionary?
   * 
   * Given: s = "Cat" 
   * Check: Cat, cat
   * 
   * Given: s = "cat"
   * Check: cat
   * 
   * Given: s = "CAT"
   * Check: Cat, cat
   */
  @Override
  public boolean isWord(String s)
  {
    // return words.contains(s.toLowerCase());
    
    if ( words.contains(s) )
    {
      return true;
    }
    else
    {
      char[] chars = s.toCharArray();
      
      if ( isAllCaps(chars) )
      {
        // all chars are uppercase (i.e. CAT), check if it dict contains "Cat" or "cat"
        String s1 = String.valueOf(chars).toLowerCase();
        String s2 = Character.toUpperCase(chars[0]) + String.valueOf(chars, 1, chars.length - 1).toLowerCase();
        return words.contains(s1) || words.contains(s2);
      }
      else if ( Character.isUpperCase(chars[0]) )
      {
        // first char is uppercase, check if a lower case version exists in dict
        String s2 = Character.toLowerCase(chars[0]) + String.valueOf(chars, 1, chars.length - 1);
        return words.contains(s2);
      }
      
    }
    
    return false;
  }
  
  private boolean isAllCaps(char[] chars)
  {
    boolean rc = false;
    int count = 0;
    for ( char c: chars)
    {
      if ( Character.isUpperCase(c) )
      {
        count++;
      }
    }
    
    if ( count == chars.length )
    {
      rc = true;
    }
    
    return rc;
  }
}
