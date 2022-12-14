import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Calculates the square root of a number within error of the users choice using
 * Newton iteration. The program will run continuously until the user inputs the
 * letter Y.
 *
 * @author Quantez Merchant
 *
 */
public final class Newton3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%. If
     * x is zero, the program will return 0.
     *
     * @param x
     *            positive number to compute square root of
     * @param y
     *            positive number to determine the error
     * @return estimate of square root
     */
    private static double sqrt(double x, double y) {
        double r = x;
        double epsilonConstant = y;
        double epsilon = y + 1;
        double z;
        if (x == 0) {
            return r;
        }
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
        out.print("Enter the value of epsilon: ");
        double numberError = in.nextDouble();
        out.println(sqrt(number, numberError));
        out.print("Would you like to calculate another square root? ");
        String answer = in.nextLine();
        while (answer.equalsIgnoreCase("y")) {
            out.print("Enter a number: ");
            number = in.nextDouble();
            out.print("Enter the value of epsilon: ");
            numberError = in.nextDouble();
            out.println(sqrt(number, numberError));
            out.print("Would you like to calculate another square root? ");
            answer = in.nextLine();
        }
        in.close();
        out.close();
    }

}
