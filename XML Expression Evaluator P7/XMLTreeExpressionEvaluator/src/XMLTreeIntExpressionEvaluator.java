import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Quantez Merchant
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        //initialize integers
        int total = 0;
        int leftValue = 0;
        int rightValue = 0;
        int num = 0;

        //recursively find the values of the right and left side of the tree
        if (exp.numberOfChildren() > 0) {
            leftValue = evaluate(exp.child(0));
            rightValue = evaluate(exp.child(1));
        } else {
            num = Integer.parseInt(exp.attributeValue("value"));
            total = num;
        }

        //find the sign and do the operation related to that sign
        if (exp.label().equals("plus")) {
            total = leftValue + rightValue;
        } else if (exp.label().equals("minus")) {
            total = leftValue - rightValue;
        } else if (exp.label().equals("divide")) {
            if (rightValue == 0) {
                Reporter.fatalErrorToConsole("Cannot divide by zero.");
            }
            total = leftValue / rightValue;
        } else if (exp.label().equals("times")) {
            total = leftValue * rightValue;
        }
        return total;
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

        //asks the user for a well formed xml file
        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();

        /*
         * returns a value and asks the user for a xml file until the user
         * inputs an empty string
         */
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        //Close simple reader and writer
        in.close();
        out.close();
    }

}
