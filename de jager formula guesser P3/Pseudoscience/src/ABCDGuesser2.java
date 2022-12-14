import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Calculates a user input value within 1% relative error using the de Jager
 * formula and outputs the calculated value, the exponents used to calculate the
 * number, and the relative error of the number.
 *
 * @author Quantez Merchant
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.print("Enter a positive real number: ");
        String stringDouble = in.nextLine();
        boolean checkDouble = FormatChecker.canParseDouble(stringDouble);
        double positiveDouble = 0;
        if (checkDouble) {
            positiveDouble = Double.parseDouble(stringDouble);
        } else {
            while (!checkDouble) {
                out.print("Error, enter a positive real number: ");
                stringDouble = in.nextLine();
                checkDouble = FormatChecker.canParseDouble(stringDouble);
            }
            positiveDouble = Double.parseDouble(stringDouble);
        }
        if (positiveDouble < 0) {
            while (positiveDouble < 0) {
                out.print("Error, enter a positive real number: ");
                stringDouble = in.nextLine();
                checkDouble = FormatChecker.canParseDouble(stringDouble);
                if (checkDouble) {
                    positiveDouble = Double.parseDouble(stringDouble);
                }

            }
        }

        return positiveDouble;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        out.print("Enter a positive real number not equal to one: ");
        String stringDouble = in.nextLine();
        boolean checkDouble = FormatChecker.canParseDouble(stringDouble);
        double positiveDouble = 0;
        if (checkDouble) {
            positiveDouble = Double.parseDouble(stringDouble);
        } else {
            while (!checkDouble) {
                out.print(
                        "Error, enter a positive real number not equal to one: ");
                stringDouble = in.nextLine();
                checkDouble = FormatChecker.canParseDouble(stringDouble);
            }
            positiveDouble = Double.parseDouble(stringDouble);
        }
        if (positiveDouble < 0 || positiveDouble == 1.0) {
            while (positiveDouble < 0 || positiveDouble == 1.0) {
                out.print(
                        "Error, enter a positive real number not equal to one: ");
                stringDouble = in.nextLine();
                checkDouble = FormatChecker.canParseDouble(stringDouble);
                if (checkDouble) {
                    positiveDouble = Double.parseDouble(stringDouble);
                }

            }
        }

        return positiveDouble;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param mu
     *            A positive real number
     * @param w
     *            A positive real number greater than 1
     * @param x
     *            A positive real number greater than 1
     * @param y
     *            A positive real number greater than 1
     * @param z
     *            A positive real number greater than 1
     *
     */
    private static void calculateMu(double mu, double w, double x, double y,
            double z) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        final double[] exponent = { -5, -4, -3, -2, -1, -1.0 / 2.0, -1.0 / 3.0,
                -1.0 / 4.0, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1, 2, 3, 4, 5 };
        final double percent = 100;
        double muApproximation = 0;
        double total1 = 0;
        double total2 = 0;
        double total3 = 0;
        double total4 = 0;
        double product = 0;
        String exponentValues = "";
        for (int i = 0; i < exponent.length; i++) {
            total1 = Math.pow(w, exponent[i]);
            for (int j = 0; j < exponent.length; j++) {
                total2 = Math.pow(x, exponent[j]);
                for (int k = 0; k < exponent.length; k++) {
                    total3 = Math.pow(y, exponent[k]);
                    for (int l = 0; l < exponent.length; l++) {
                        total4 = Math.pow(z, exponent[l]);
                        product = total1 * total2 * total3 * total4;
                        if (Math.abs(mu - product) < Math
                                .abs(mu - muApproximation)) {
                            muApproximation = product;
                            exponentValues = "" + exponent[i] + ", "
                                    + exponent[j] + ", " + exponent[k] + ", "
                                    + exponent[l];
                        }
                    }
                }
            }
        }
        double relativeError = Math
                .abs(((mu - muApproximation) / mu) * percent);
        out.println("Mu approximation: " + muApproximation);
        out.println("Exponent Values: " + exponentValues);
        out.print("Relative Error: ");
        out.print(relativeError, 2, false);
        out.print("%");
        in.close();
        out.close();
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
        double mu = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);
        calculateMu(mu, w, x, y, z);

    }
}
