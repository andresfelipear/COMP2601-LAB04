import java.util.function.BiConsumer;
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
        final String action;
        final int numberOfRepetition;
        final Dictionary dictionary;

        if(args.length < 2)
        {
            System.out.println("Please provide two commandline argument: (String, int)");
            return;
        }

        action = args[0];
        numberOfRepetition = Integer.parseInt(args[1]);
        dictionary = new Dictionary();

        BiFunction<String, Integer, String> consumer = (word, repetitions) ->{
            final StringBuilder str;
            str = new StringBuilder();

            for(int i=0; i < repetitions; i++)
            {
                str.append(word);
            }
            return str.toString();
        };

        Wordable wordyConcat = (word, number) ->
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
            return null;
        };

        Wordable wordyRepeat = (word, number) ->
        {
            if(REPEAT_WORDS.equalsIgnoreCase(word))
            {
                final StringBuilder result;
                result = new StringBuilder();

                for(final String w : dictionary.getWords())
                {
                    result.append(consumer.apply(w, number));
                }
                return result.toString();
            }
            return null;
        };

        Wordable wordyNth = (word, number) ->
        {
            if(WORD_IN_INDEX.equalsIgnoreCase(word))
            {
                return dictionary.getWordByIndex(number);
            }
            return null;
        };

        Wordable wordyReverse = (word, number) ->
        {
            if(REVERSE_WORDS.equalsIgnoreCase(word))
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

        String result = dictionary.getWords(CONCAT_WORDS, 0, wordyConcat);
        System.out.println(result);

        String result2 = dictionary.getWords(REPEAT_WORDS, 2, wordyRepeat);
        System.out.println(result2);

        String result3 = dictionary.getWords(WORD_IN_INDEX, 7, wordyNth);
        System.out.println(result3);

        String result4 = dictionary.getWords(REVERSE_WORDS, 0, wordyReverse);
        System.out.println(result4);


    }
}
