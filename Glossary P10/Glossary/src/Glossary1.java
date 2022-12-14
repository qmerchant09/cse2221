import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Takes a file from the user filled with terms and definitions and creates a
 * glossary.
 *
 * @author Quantez Merchant
 *
 */
public final class Glossary1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary1() {
    }

    /**
     * Creates a map of terms and definitions using data from the input file.
     *
     * @param in
     *            the input file
     * @param terms
     *            map of terms with definitions
     *
     * @updates terms
     * @requires the input file is not empty
     * @ensures terms is filled with data from in
     */
    public static void processFile(SimpleReader in, Map<String, String> terms) {
        //Initialize variables
        String str = in.name();
        String key = in.nextLine();
        String value = in.nextLine();
        terms.add(key, value);

        //Loop through the input file until an empty line is reached
        while (!str.isEmpty()) {
            str = in.nextLine();

            //Check if the definition is on multiple lines
            if (!str.isEmpty()) {
                value = value.concat(" " + str);
                terms.replaceValue(key, value);
            }
        }
    }

    /**
     * Outputs the HTML code required to create the index page of the glossary.
     *
     * @param in
     *            the input file
     * @param file
     *            the output file
     * @param folder
     *            the name of the output folder
     *
     * @requires the input file is not empty
     * @ensures the output file is filled with data from the input file
     */
    private static void outputIndex(SimpleReader in, SimpleWriter file,
            String folder) {

        //Output HTML tags to the output file
        file.println("<html>");
        file.println("<head>");
        file.println("<title> Glossary </title>");
        file.println("</head>");
        file.println("<body>");
        file.println("<h2>");
        file.println("Glossary");
        file.println("</h2>");
        file.println("<hr>");
        file.println("<h3>");
        file.println("Index");
        file.println("</h3>");
        file.println("<ul>");

        //Fills a map with terms and definitions from the input file
        Map<String, String> term = new Map1L<>();
        while (!in.atEOS()) {
            processFile(in, term);
        }

        //Creates a String array that is sorted alphabetically
        String[] termArr = sortAplhabetically(term);

        //Outputs the terms to the output file
        for (String str : termArr) {
            SimpleWriter termPage = new SimpleWriter1L(folder + str + ".html");
            file.println("<li>");

            file.println("<a href= " + str + ".html" + ">" + str + "</a>");
            outputPage(termPage, str, term);

            file.println("</li>");
        }
        file.println("</ul>");
        file.println("</body>");
        file.println("</html>");

        //Close the output file
        file.close();

    }

    /**
     * Outputs the HTML code required to create the term pages of the glossary.
     *
     * @param out
     *            the output file
     * @param str
     *            the name of the term
     * @param term
     *            map of terms with definitions
     *
     * @requires the output file exists
     * @ensures the output file is filled with data from the map
     */
    private static void outputPage(SimpleWriter out, String str,
            Map<String, String> term) {
        final String separatorStr = " ";
        final String separatorStr2 = ",";
        final String separatorStr3 = ".";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);
        generateElements(separatorStr2, separatorSet);
        generateElements(separatorStr3, separatorSet);

        //Output HTML tags to the output file
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + str + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>");
        out.println("<b>");
        out.println("<i>");
        out.println("<font color=\"red\">" + str + "</font>");
        out.println("</i>");
        out.println("</b>");
        out.println("</h2>");
        out.println("<p>");

        //Find the words that link to other pages
        String linkStr = term.value(str);
        int position = 0;
        while (position < linkStr.length()) {
            String token = nextWordOrSeparator(linkStr, position, separatorSet);
            if (term.hasKey(token)) {
                for (Map.Pair<String, String> k : term) {
                    if (k.key().equals(token)) {
                        out.print(" <a href=" + token + ".html>" + token
                                + "</a>");
                    }
                }
            } else {
                out.print(token);
            }

            position += token.length();
        }
        out.println();
        out.println("</p>");
        out.println("<hr>");
        out.println("<p>");
        out.println("Return to <a href= index.html> Index </a>");
        out.println("</p>");
        out.println("</body>");
        out.println("</html>");

        //Close the output file
        out.close();

    }

    /**
     * Creates an array of strings that takes the terms from term, and lists
     * them alphabetically.
     *
     * @param term
     *            map of terms with definitions
     *
     * @return An array of string
     * @requires term.length > 0
     * @ensures the array is filled with terms from the map, and the terms are
     *          alphabetized
     */
    private static String[] sortAplhabetically(Map<String, String> term) {
        //Initialize the array and counter
        String[] termArr = new String[term.size()];
        int i = 0;

        //Loop through each pair in the array and put each key into the array
        for (Map.Pair<String, String> k : term) {
            termArr[i] = k.key();
            i++;
        }

        //Loop through the array and alphabetize it
        for (i = 0; i < termArr.length - 1; i++) {
            for (int j = i + 1; j < termArr.length; j++) {
                if (termArr[i].compareTo(termArr[j]) > 0) {
                    String str = termArr[i];
                    termArr[i] = termArr[j];
                    termArr[j] = str;
                }
            }
        }
        return termArr;
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param strSet
     *            the {@code Set} to be replaced
     * @replaces strSet
     * @ensures strSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> strSet) {
        assert str != null : "Violation of: str is not null";
        assert strSet != null : "Violation of: strSet is not null";

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            strSet.add(ch);
        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        String str = "";
        char[] cArr = text.substring(position, text.length()).toCharArray();
        for (int i = 0; i < cArr.length; i++) {
            char c = cArr[i];
            if (!separators.contains(c)) {
                str = str + c;
            } else if (separators.contains(c) && str.isEmpty()) {
                str = Character.toString(c);
                i = cArr.length;
            } else {
                i = cArr.length;
            }
        }

        return str;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //Ask the user for the input file and output file
        out.print("Enter the name of the input file: ");
        String strIn = in.nextLine();
        SimpleReader inputFile = new SimpleReader1L(strIn);
        out.print("Enter the name of the output folder: ");
        String strOut = in.nextLine();
        strOut = strOut + "/";
        SimpleWriter outputFile = new SimpleWriter1L(strOut + "Index.html");

        //Creates the webpage using data from the input file
        outputIndex(inputFile, outputFile, strOut);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
