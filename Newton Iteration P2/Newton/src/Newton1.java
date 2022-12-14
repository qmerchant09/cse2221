import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Calculates the square root of a number within 0.01% error using Newton
 * iteration. The program will run continuously until the user inputs the letter
 * Y.
 *
 * @author Quantez Merchant
 *
 */
public final class Newton1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double r = x;
        final double epsilonConstant = 0.0001;
        double epsilon = 1;
        double z;

        while ((epsilonConstant * epsilonConstant) < epsilon) {
            r = (x / r + r) / 2;
            z = r * r;
            epsilon = Math.abs(z - x) / x;
        }
        return r;
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
        out.print("Enter a number: ");
        double number = in.nextDouble();
        out.println(sqrt(number));
        out.print("Would you like to calculate a square root? ");
        String answer = in.nextLine();
        while (answer.equalsIgnoreCase("y")) {
            out.print("Enter a number: ");
            number = in.nextDouble();
            out.println(sqrt(number));
            out.print("Would you like to calculate a square root? ");
            answer = in.nextLine();
        }

        in.close();
        out.close();
    }

}
