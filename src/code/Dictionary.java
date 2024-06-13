import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Dictionary
 *
 * @author Andres Arevalo, Marius Guerra & Raaz
 * @version 1.0
 */
public class Dictionary
{
    final List<String> words;
    final File file;

    private static final String FILE_NAME;
    private static final int VALID_WORD_LENGTH;

    static
    {
        FILE_NAME = "words.txt";
        VALID_WORD_LENGTH = 5;
    }

    /**
     * Constructs a Dictionary object and reads the words from the file.
     */
    public Dictionary()
    {
        words = new ArrayList<String>();
        file = new File(FILE_NAME);

        readFile(file);
    }

    /**
     * Uses a Wordable functional interface to create a string based on the word and number provided.
     *
     * @param word the word to be used in the Wordable function
     * @param number the number to be used in the Wordable function
     * @param w the Wordable functional interface
     * @return the string created by the Wordable function
     */
    public String getWords(final String word, final int number, final Wordable w)
    {
        return w.createString(word, number);
    }

    /**
     * Reads the words from the given file and adds them to the words list.
     *
     * @param file the file to read the words from
     */
    private void readFile(final File file)
    {
        try(final Scanner scanner = new Scanner(file))
        {
            String line;

            while(scanner.hasNextLine())
            {
                line = scanner.nextLine();
                if(!line.isEmpty())
                {
                    words.add(line);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("File not found" + e.getMessage());
        }
    }

    /**
     * Reverses the given string.
     *
     * @param s the string to reverse
     * @return the reversed string
     */
    public static String reverseString(final String s)
    {
        return new StringBuilder(s).reverse().toString();
    }

    /**
     * Compares two words alphabetically.
     *
     * @param word1 the first word to compare
     * @param word2 the second word to compare
     * @return a negative integer, zero, or a positive integer as the first word is less than, equal to, or greater than the second word
     */
    public static int alphabeticalOrder(final String word1, final String word2)
    {
        return word1.compareTo(word2);
    }

    /**
     * Checks if the length of the word is greater than five.
     *
     * @param word the word to check
     * @return true if the word length is greater than five, false otherwise
     */
    public static boolean isLengthAboveFive(final String word)
    {
        return word.length() > VALID_WORD_LENGTH;
    }

    /**
     * Gets the list of words.
     *
     * @return the list of words
     */
    public List<String> getWords()
    {
        return words;
    }

    /**
     * Gets a word from the list by its index.
     *
     * @param index the index of the word to retrieve
     * @return the word at the specified index
     */
    public String getWordByIndex(int index)
    {
        return words.get(index);
    }
}
