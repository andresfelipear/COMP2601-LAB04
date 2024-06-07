import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Dictionary
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class Dictionary
{
    final List<String> words;
    final File file;

    private static final String FILE_NAME;

    static
    {
        FILE_NAME = "words.txt";
    }

    public Dictionary()
    {
        words = new ArrayList<String>();
        file = new File(FILE_NAME);

        readFile(file);
    }

    public String getWords(final String word, final int number, final Wordable w)
    {
        return w.createString(word, number);
    }

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

    public List<String> getWords()
    {
        return words;
    }

    public String getWordByIndex(int index)
    {
        return words.get(index);
    }
}
