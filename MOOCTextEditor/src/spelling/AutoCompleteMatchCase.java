package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete
 * ADT
 * 
 * @author Eric Leung
 *
 */
public class AutoCompleteMatchCase implements Dictionary, AutoComplete
{
  private DictionaryHashSetMatchCase dict;
  private TrieNode root;
  private int size;

  public AutoCompleteMatchCase()
  {
    root = new TrieNode();
    dict = new DictionaryHashSetMatchCase();
  }

  /**
   * Insert a word into the trie. For the basic part of the assignment (part 2),
   * you should ignore the word's case. That is, you should convert the string
   * to all lower case as you insert it.
   */
  public boolean addWord(String word)
  {
    // TODO: Implement this method.
    dict.addWord(word);
    String wordLowerCase = word.toLowerCase();
    char[] chars = wordLowerCase.toCharArray();

    boolean isNewWord = false;
    TrieNode currNode = root;
    int i = 0;
    while (i < chars.length)
    {
      TrieNode nextNode = currNode.insert(chars[i]);

      if (nextNode != null)
      {
        // char not in Trie
        currNode = nextNode;
        isNewWord = true;
      }
      else
      {
        // char already in Trie
        currNode = currNode.getChild(chars[i]);
      }
      i++;
    }

    // This is a brand new word, set endsWord = true
    if (!currNode.endsWord())
    {
      currNode.setEndsWord(true);
      size++;
    }
   
    return isNewWord;
  }

  /**
   * Return the number of words in the dictionary. This is NOT necessarily the
   * same as the number of TrieNodes in the trie.
   */
  public int size()
  {
    // TODO: Implement this method
    return size;
  }

  /** Returns whether the string is a word in the trie */
  @Override
  public boolean isWord(String s)
  {
    // TODO: Implement this method
    String wordLowerCase = s.toLowerCase();
    char[] chars = wordLowerCase.toCharArray();

    TrieNode currNode = root;
    int i = 0;
    while (i < chars.length)
    {
      TrieNode nextNode = currNode.getChild(chars[i]);

      if (nextNode == null)
      {
        return false;
      }
      else
      {
        currNode = nextNode;
      }
      i++;
    }

    return currNode.endsWord();
  }

  /**
   * * Returns up to the n "best" predictions, including the word itself, in
   * terms of length If this string is not in the trie, it returns null.
   * 
   * @param text
   *          The text to use at the word stem
   * @param n
   *          The maximum number of predictions desired.
   * @return A list containing the up to n best predictions
   */
  @Override
  public List<String> predictCompletions(String prefix, int numCompletions)
  {
    // TODO: Implement this method
    // This method should implement the following algorithm:
    // 1. Find the stem in the trie. If the stem does not appear in the trie,
    // return an
    // empty list
    // 2. Once the stem is found, perform a breadth first search to generate
    // completions
    // using the following algorithm:
    // Create a queue (LinkedList) and add the node that completes the stem to
    // the back
    // of the list.
    // Create a list of completions to return (initially empty)
    // While the queue is not empty and you don't have enough completions:
    // remove the first Node from the queue
    // If it is a word, add it to the completions list
    // Add all of its child nodes to the back of the queue
    // Return the list of completions
    
    String prefixLowerCase = prefix.toLowerCase();
    char[] chars = prefixLowerCase.toCharArray();
    
    TrieNode currNode = root;
    int i = 0;
       
    while ( i < chars.length )
    {
      TrieNode nextNode = currNode.getChild(chars[i]);
      
      if ( nextNode == null )
      {
        // Stem not in trie, return empty list
        return new LinkedList<String>();
      }
      
      currNode = nextNode;
      i++;
    }
    
    // if we have reached this point, stem is in the trie (it is the currNode)
    TrieNode stem = currNode;
    LinkedList<String> predictions = new LinkedList<String>();
    LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    
    // Add stem to our queue of nodes to be explored
    queue.addLast(stem);
    int count = 0; 
    while ( !queue.isEmpty() && count < numCompletions )
    {
      TrieNode curr = queue.removeFirst();
      if ( curr.endsWord() )
      {
        if ( isWordInDict(prefix, curr.getText() ) )
        {
          // If the node is a word, add it to our predictions list.
          predictions.add(curr.getText());
          count++;
        }
      }
      
      // Add all of curr's child nodes to the queue
      queue.addAll(curr.getValidNextNodes());
    }
    
    return predictions;
  }

  /**
   * Helper method to determine if the given word is in the dictionary, case sensitive.
   * 
   * @return
   */
  private boolean isWordInDict(String prefix, String word)
  {
    boolean rc = false;
    
    // The raw word with the given capitalization from user
    String rawCase = prefix + word.substring(prefix.length());
    
    if ( dict.isWord(rawCase) )
    {
      rc = true;
    }
    
    return rc;
  }
  
  // For debugging
  public void printTree()
  {
    printNode(root);
  }

  /** Do a pre-order traversal from this node down */
  public void printNode(TrieNode curr)
  {
    if (curr == null)
      return;

    System.out.println(curr.getText());

    TrieNode next = null;
    for (Character c : curr.getValidNextCharacters())
    {
      next = curr.getChild(c);
      printNode(next);
    }
  }

}