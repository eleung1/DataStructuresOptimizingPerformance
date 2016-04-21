package textgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator
{

  // The list of words with their next words
  private List<ListNode> wordList;

  private Map<String, ListNode> wordIndexMap;

  // The starting "word"
  private String starter;

  // The random number generator
  private Random rnGenerator;

  // Regex pattern for a word
  public static final String REGEX_WORD = "[A-Za-z]+";

  public MarkovTextGeneratorLoL(Random generator)
  {
    wordList = new LinkedList<ListNode>();
    starter = "";
    rnGenerator = generator;
    wordIndexMap = new HashMap<String, ListNode>();
  }

  /** Train the generator by adding the sourceText */
  @Override
  public void train(String sourceText)
  {
    // TODO: Implement this method

    if (sourceText == null || sourceText.trim().equals(""))
    {
      return;
    }

    // Get the words in the sourceText
    //List<String> words = getTokens(sourceText, REGEX_WORD);
    String[] wordsArr = sourceText.split(" +"); // Assignment grader includes punctuation
    List<String> words = new ArrayList<String>(Arrays.asList(wordsArr));
    
    // Init starter word
    starter = words.get(0);
    String prevWord = starter;

    // add starter to be next word for the last word in source text
    words.add(starter);

    for (int i = 1; i < words.size(); i++)
    {
      String currWord = words.get(i);

      if (wordIndexMap.containsKey(prevWord))
      {
        // prevWord is already a node is the list
        wordIndexMap.get(prevWord).addNextWord(currWord);
      }
      else
      {
        // prevWord is brand new
        ListNode prevWordNode = new ListNode(prevWord);
        prevWordNode.addNextWord(currWord);
        wordList.add(prevWordNode);
        wordIndexMap.put(prevWord, prevWordNode);
      }

      prevWord = currWord;
    }
  }

  /**
   * Helper method to get tokens from sourceText
   * 
   * @param sourceText
   *          The source text.
   * @param pattern
   *          The regex pattern of items we want to split from the sourceText.
   * @return A list of items that match the given regex pattern. i.e. a list of
   *         words.
   */
  protected List<String> getTokens(String sourceText, String pattern)
  {
    ArrayList<String> tokens = new ArrayList<String>();
    Pattern tokSplitter = Pattern.compile(pattern);
    Matcher m = tokSplitter.matcher(sourceText);

    while (m.find())
    {
      tokens.add(m.group());
    }

    return tokens;
  }

  /**
   * Generate the number of words requested.
   */
  @Override
  public String generateText(int numWords)
  {
    // TODO: Implement this method
    
    if ( wordList == null || wordList.isEmpty() || numWords < 1 )
    {
      // Not trained yet, return empty string
      return "";
    }
    
    String currWord = wordList.get(0).getWord();
    StringBuilder output = new StringBuilder();
    output.append(currWord);
    int wordCount = 1; // Number of generated words

    while (wordCount < numWords)
    {
      String w = wordIndexMap.get(currWord).getRandomNextWord(rnGenerator);
      output.append(" " + w);
      currWord = w;
      wordCount++;
    }

    return output.toString();
  }

  // Can be helpful for debugging
  @Override
  public String toString()
  {
    String toReturn = "";
    for (ListNode n : wordList)
    {
      toReturn += n.toString();
    }
    return toReturn;
  }

  /** Retrain the generator from scratch on the source text */
  @Override
  public void retrain(String sourceText)
  {
    // TODO: Implement this method.
    
    // reinitialize instance vars
    wordList = new LinkedList<ListNode>();
    starter = "";
    wordIndexMap = new HashMap<String, ListNode>();
    
    train(sourceText);
  }

  // TODO: Add any private helper methods you need here.

  /**
   * This is a minimal set of tests. Note that it can be difficult to test
   * methods/classes with randomized behavior.
   * 
   * @param args
   */
  public static void main(String[] args)
  {
    // feed the generator a fixed random value for repeatable behavior
    MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
    String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
    System.out.println(textString);
    gen.train(textString);
    System.out.println(gen);
    System.out.println(gen.generateText(20));
    String textString2 = "You say yes, I say no, " + "You say stop, and I say go, go, go, "
        + "Oh no. You say goodbye and I say hello, hello, hello, "
        + "I don't know why you say goodbye, I say hello, hello, hello, "
        + "I don't know why you say goodbye, I say hello. " + "I say high, you say low, "
        + "You say why, and I say I don't know. " + "Oh no. " + "You say goodbye and I say hello, hello, hello. "
        + "I don't know why you say goodbye, I say hello, hello, hello, "
        + "I don't know why you say goodbye, I say hello. " + "Why, why, why, why, why, why, " + "Do you say goodbye. "
        + "Oh no. " + "You say goodbye and I say hello, hello, hello. "
        + "I don't know why you say goodbye, I say hello, hello, hello, "
        + "I don't know why you say goodbye, I say hello. " + "You say yes, I say no, "
        + "You say stop and I say go, go, go. " + "Oh, oh no. " + "You say goodbye and I say hello, hello, hello. "
        + "I don't know why you say goodbye, I say hello, hello, hello, "
        + "I don't know why you say goodbye, I say hello, hello, hello, "
        + "I don't know why you say goodbye, I say hello, hello, hello,";
    System.out.println(textString2);
    gen.retrain(textString2);
    System.out.println(gen);
    System.out.println(gen.generateText(20));
  }

}

/**
 * Links a word to the next words in the list You should use this class in your
 * implementation.
 */
class ListNode
{
  // The word that is linking to the next words
  private String word;

  // The next words that could follow it
  private List<String> nextWords;

  ListNode(String word)
  {
    this.word = word;
    nextWords = new LinkedList<String>();
  }

  public String getWord()
  {
    return word;
  }

  public void addNextWord(String nextWord)
  {
    nextWords.add(nextWord);
  }

  public String getRandomNextWord(Random generator)
  {
    // TODO: Implement this method
    // The random number generator should be passed from
    // the MarkovTextGeneratorLoL class
    if (nextWords.size() > 0)
    {
      return nextWords.get(generator.nextInt(nextWords.size()));
    }
    else
    {
      return null;
    }
  }

  public String toString()
  {
    String toReturn = word + ": ";
    for (String s : nextWords)
    {
      toReturn += s + "->";
    }
    toReturn += "\n";
    return toReturn;
  }

}
