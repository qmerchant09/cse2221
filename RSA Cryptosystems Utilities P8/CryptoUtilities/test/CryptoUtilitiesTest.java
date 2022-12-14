import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Quantez Merchant
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_15291929189863252_144119294564714() {
        NaturalNumber n = new NaturalNumber2("15291929189863252");
        NaturalNumber nExpected = new NaturalNumber2(2);
        NaturalNumber m = new NaturalNumber2("144119294564714");
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_23_6() {
        NaturalNumber n = new NaturalNumber2(23);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(6);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_11615218129813935931() {
        NaturalNumber n = new NaturalNumber2("11615218129813935931");
        NaturalNumber nExpected = new NaturalNumber2("11615218129813935931");
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_115254126985298568563258() {
        NaturalNumber n = new NaturalNumber2("115254126985298568563258");
        NaturalNumber nExpected = new NaturalNumber2(
                "115254126985298568563258");
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_2194192989462_1656263_268145() {
        NaturalNumber n = new NaturalNumber2("2194192989462");
        NaturalNumber nExpected = new NaturalNumber2(5083);
        NaturalNumber p = new NaturalNumber2(1656263);
        NaturalNumber pExpected = new NaturalNumber2(1656263);
        NaturalNumber m = new NaturalNumber2(268145);
        NaturalNumber mExpected = new NaturalNumber2(268145);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_100356_132536463524_27() {
        NaturalNumber n = new NaturalNumber2(100356);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2("132536463524");
        NaturalNumber pExpected = new NaturalNumber2("132536463524");
        NaturalNumber m = new NaturalNumber2(27);
        NaturalNumber mExpected = new NaturalNumber2(27);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */
    @Test
    public void testIsWitnessToCompositeness_3_7() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(7);
        boolean resultExpected = false;
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(resultExpected, result);
    }

    @Test
    public void testIsWitnessToCompositeness_4_8() {
        NaturalNumber w = new NaturalNumber2(4);
        NaturalNumber n = new NaturalNumber2(8);
        boolean resultExpected = true;
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(resultExpected, result);
    }

    @Test
    public void testIsWitnessToCompositeness_3_1021() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(1021);
        boolean resultExpected = false;
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(resultExpected, result);
    }

    @Test
    public void testIsWitnessToCompositeness_3_343234134() {
        NaturalNumber w = new NaturalNumber2(4);
        NaturalNumber n = new NaturalNumber2(343234134);
        boolean resultExpected = true;
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(resultExpected, result);
    }

    /*
     * Tests of isPrime2
     */
    @Test
    public void testIsPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean nExpected = true;
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, result);
    }

    @Test
    public void testIsPrime2_5() {
        NaturalNumber n = new NaturalNumber2(5);
        boolean nExpected = true;
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, result);
    }

    @Test
    public void testIsPrime2_25() {
        NaturalNumber n = new NaturalNumber2(25);
        boolean nExpected = false;
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, result);
    }

    @Test
    public void testIsPrime2_251513() {
        NaturalNumber n = new NaturalNumber2(251519);
        boolean nExpected = true;
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, result);
    }

    /*
     * Tests of generateNextLikelyPrime
     */
    @Test
    public void testGenerateNextLikelyPrime_8() {
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(11);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_251525() {
        NaturalNumber n = new NaturalNumber2(251525);
        NaturalNumber nExpected = new NaturalNumber2(251527);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_123456766534231243542() {
        NaturalNumber n = new NaturalNumber2("123456766534231243542");
        NaturalNumber nExpected = new NaturalNumber2("123456766534231243549");
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_1000() {
        NaturalNumber n = new NaturalNumber2(1000);
        NaturalNumber nExpected = new NaturalNumber2(1009);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

}