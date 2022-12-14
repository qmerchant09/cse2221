import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code NaturalNumber}.
 *
 * @author Quantez Merchant
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
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
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        //initialize natural numbers
        NaturalNumber total = new NaturalNumber2();
        NaturalNumber leftValue = total.newInstance();
        NaturalNumber rightValue = total.newInstance();

        //recursively find the values of the right and left side of the tree
        if (exp.numberOfChildren() > 0) {
            leftValue = evaluate(exp.child(0));
            rightValue = evaluate(exp.child(1));
        } else {
            total.setFromString(exp.attributeValue("value"));
        }

        //find the sign and do the operation related to that sign
        if (exp.label().equals("plus")) {
            leftValue.add(rightValue);
            total.copyFrom(leftValue);
        } else if (exp.label().equals("minus")) {
            if (rightValue.compareTo(leftValue) > 0) {
                Reporter.fatalErrorToConsole(
                        "Natural Number cannot be negative.");
            }
            leftValue.subtract(rightValue);
            total.copyFrom(leftValue);
        } else if (exp.label().equals("divide")) {
            if (rightValue.isZero()) {
                Reporter.fatalErrorToConsole("Cannot divide by zero.");
            }
            leftValue.divide(rightValue);
            total.copyFrom(leftValue);
        } else if (exp.label().equals("times")) {
            leftValue.multiply(rightValue);
            total.copyFrom(leftValue);
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

        in.close();
        out.close();
    }

}
