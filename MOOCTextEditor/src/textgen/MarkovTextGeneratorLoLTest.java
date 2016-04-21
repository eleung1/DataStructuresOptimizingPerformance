package textgen;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class MarkovTextGeneratorLoLTest
{
  private String sourceText;
  private String sourceText2;
  private MarkovTextGeneratorLoL markovTextGeneratorLoL;

  @Before
  public void setup()
  {
    sourceText = "Hello! How are you?";
    sourceText2 = "hi there hi Eric";
    markovTextGeneratorLoL = new MarkovTextGeneratorLoL(new Random(10));
  }

  @Test
  public void testGetTokens()
  {
    List<String> words = markovTextGeneratorLoL.getTokens(sourceText, MarkovTextGeneratorLoL.REGEX_WORD);

    assertNotNull(words);
    assertEquals("Hello", words.get(0));
    assertEquals("How", words.get(1));
    assertEquals("are", words.get(2));
    assertEquals("you", words.get(3));
  }

  @Test
  public void testTrain_NullSourceText_IllegalArgumentException()
  {
    try
    {
      markovTextGeneratorLoL.train(null);
      fail("Check for illegal arguments.");
    }
    catch (IllegalArgumentException e)
    {

    }

  }

  @Test
  public void testTrain_EmptySourceText_GenerateEmptyString()
  {
    markovTextGeneratorLoL.train("");
    String generatedText = markovTextGeneratorLoL.generateText(10);
    assertEquals("", generatedText);
  }

  @Test
  public void testTrain()
  {
    markovTextGeneratorLoL.train(sourceText2);
    System.out.println(markovTextGeneratorLoL.toString());
  }

  @Test
  public void testGenerateText_trained()
  {
    markovTextGeneratorLoL.train(sourceText2);
    String generatedText = markovTextGeneratorLoL.generateText(10);
    assertEquals("hi Eric hi there hi there hi there hi Eric", generatedText);
  }

  @Test
  public void testGenerateText_untrained()
  {
    String generatedText = markovTextGeneratorLoL.generateText(10);
    assertEquals("", generatedText);
  }
  
  @Test
  public void testGenerateText_requestedZeroWords_returnEmptyString()
  {
    markovTextGeneratorLoL.train(sourceText2);
    String generatedText = markovTextGeneratorLoL.generateText(0);
    assertEquals("", generatedText);
  }
}
