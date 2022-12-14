import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * @author Quantez Merchant
 *
 */
public final class RSSReader {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSReader() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head><body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        //Find the index of title,description,link, and the first item tag
        int title = getChildElement(channel, "title");
        int description = getChildElement(channel, "description");
        int link = getChildElement(channel, "link");
        int itemInt = getChildElement(channel, "item");

        //Create a tree with item being the root
        XMLTree item = channel.child(itemInt);

        //HTML tags to be printed to an HTML file
        out.println("<html>");
        out.println("<head>");
        out.println("   <title>" + channel.child(title).child(0).label()
                + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>");
        out.println("<a href =" + channel.child(link).child(0).label() + ">"
                + channel.child(title).child(0).label() + "</a>");
        out.println("</h1>");
        out.println("<p>");
        out.println(channel.child(description).child(0).label());
        out.println("</p>");
        out.println("<table border = \"1\">");
        out.println("<tr>");
        out.println("   <th> Date </th>");
        out.println("   <th> Source </th>");
        out.println("   <th> News </th>");
        out.println("</tr>");

        //Loops through each item tag and prints the contents
        int i = itemInt;
        while (i < channel.numberOfChildren()) {
            item = channel.child(i);
            processItem(item, out);
            i++;
        }

    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        //Outputs the final closing tags to an HTML file
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        //Loops through the xml file and return the index of a tag if it exists
        int i = 0;
        int j = 0;
        while (i < xml.numberOfChildren()) {
            if (xml.child(i).isTag()) {
                if (xml.child(i).label().equalsIgnoreCase(tag)) {
                    j = i;
                    i = xml.numberOfChildren();
                } else {
                    j = -1;
                }
            }
            i++;
        }
        return j;
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        //Finds the index of each tag
        out.println("<tr>");
        int title = getChildElement(item, "title");
        int link = getChildElement(item, "link");
        int date = getChildElement(item, "pubdate");
        int source = getChildElement(item, "source");
        String str = "url";

        //Fills the table with information that exists in the xml document
        if (date >= 0) {
            out.println(
                    "   <td>" + item.child(date).child(0).label() + "</td>");
        } else {
            out.println("   <td> No date available </td>");
        }
        if (source >= 0) {
            String sourceStr = item.child(source).attributeValue(str);
            out.println("   <td>" + "<a href =" + sourceStr + "." + ">"
                    + item.child(source).child(0).label() + "</a>" + "</td>");
        } else {
            out.println("   <td> No source available </td>");
        }

        if (item.child(title).numberOfChildren() == 0) {
            if (link >= 0) {
                out.println("   <td>" + "<a href ="
                        + item.child(link).child(0).label() + ">"
                        + item.child(link).child(0).label() + "</td>");
            }
        } else if (title >= 0 && link >= 0) {
            out.println("   <td>" + "<a href ="
                    + item.child(link).child(0).label() + ">"
                    + item.child(title).child(0).label() + "</a>" + "</td>");
        } else if (title >= 0) {
            out.println(
                    "   <td>" + item.child(title).child(0).label() + "</td>");
        } else if (link >= 0) {
            out.println("   <td>" + "<a href ="
                    + item.child(link).child(0).label() + ">" + "</td>");
        } else {
            out.println("   <td> No title available </td>");
        }

        out.println("</tr>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Asks the user for an rss 2.0 feed and an html file, creates an xml
         * tree from the rss feed
         */
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();
        XMLTree xml = new XMLTree1(url);
        XMLTree channel = xml.child(0);
        out.print("Enter the URL of the output file: ");
        String file = in.nextLine();

        /*
         * Creates the user specified html document and outputs the xml data
         * into the file
         */
        SimpleWriter outputFile = new SimpleWriter1L(file);
        outputHeader(channel, outputFile);
        outputFooter(outputFile);

        in.close();
        out.close();
        outputFile.close();
    }

}
