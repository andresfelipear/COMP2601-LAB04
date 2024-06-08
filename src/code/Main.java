import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Main
 *
 * @author Andres Arevalo & Raz
 * @version 1.0
 */
public class Main
{

    private static final String CONCAT_WORDS = "concat";
    private static final String REPEAT_WORDS = "repeat";
    private static final String WORD_IN_INDEX = "nth";
    private static final String REVERSE_WORDS = "reverse";

    public static void main(String[] args)
    {
        final String                                action;
        final int                                   numberCommandLine;
        final Dictionary                            dictionary;
        final List<String>                          words;
        final String[]                              wordsArray;
        final BiFunction<String, Integer, String>   repeatWords;
        final Wordable                              wordy;

        if(args.length < 2)
        {
            System.out.println("Please provide two commandline argument: (String, int)");
            return;
        }

        action              = args[0];
        numberCommandLine   = Integer.parseInt(args[1]);
        dictionary          = new Dictionary();
        words               = dictionary.getWords();
        wordsArray          = words.toArray(new String[0]);

        repeatWords = (word, repetitions) ->{
            final StringBuilder str;
            str = new StringBuilder();

            for(int i=0; i < repetitions; i++)
            {
                str.append(word);
            }
            return str.toString();
        };

        wordy = (word, number) ->
        {
            if(CONCAT_WORDS.equalsIgnoreCase(word))
            {
                final StringBuilder result;
                result = new StringBuilder();

                for(final String w : dictionary.getWords())
                {
                    result.append(w);
                }
                return result.toString();
            }
            else if(REPEAT_WORDS.equalsIgnoreCase(word))
            {
                final StringBuilder result;
                result = new StringBuilder();

                for(final String w : dictionary.getWords())
                {
                    result.append(repeatWords.apply(w, number));
                }
                return result.toString();
            }
            else if(WORD_IN_INDEX.equalsIgnoreCase(word))
            {
                return dictionary.getWordByIndex(number);
            }
            else if(REVERSE_WORDS.equalsIgnoreCase(word))
        {
            final StringBuilder str;
            StringBuilder reverseWord;

            str = new StringBuilder();

            for(final String w : dictionary.getWords())
            {
                reverseWord = new StringBuilder(w);
                str.append(reverseWord.reverse());
            }
            return str.toString();
        }
            return null;
        };

        System.out.println("e)Wordy result: ");
        String result = dictionary.getWords(action, numberCommandLine, wordy);
        System.out.println(result);

        System.out.println("\ne)List of words: ");
        words.forEach(System.out::println);

        System.out.println("\nf)Reverse each word and print it.");
        words.forEach( str -> System.out.println(Dictionary.reverseString(str)));

        Arrays.sort(wordsArray, Dictionary::alphabeticalOrder);
        System.out.println("\nSorted words: " + Arrays.toString(wordsArray));

        System.out.println("\nPrint words longer than five characters");
        words.stream().filter(Dictionary::isLengthAboveFive).forEach(System.out::println);
        System.out.println("\nPrint words longer than five characters. Option 2");
        words.forEach( word -> {
            if(Dictionary.isLengthAboveFive(word))
            {
                System.out.println(word);
            }
        });
    }
}
