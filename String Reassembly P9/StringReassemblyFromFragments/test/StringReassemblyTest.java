import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    public void testCombination1() {
        String s1 = "CATTTTAGCTGTT";
        String s2 = "AGCTGTTTTCGTT";
        int overlap = StringReassembly.overlap(s1, s2);
        String s3 = StringReassembly.combination(s1, s2, overlap);
        String s3Expected = "CATTTTAGCTGTTTTCGTT";
        assertEquals(s3Expected, s3);
    }

    @Test
    public void testCombination2() {
        String s1 = "CATTTTAGCTGTT";
        String s2 = "CTGTTTTCGTTA";
        int overlap = StringReassembly.overlap(s1, s2);
        String s3 = StringReassembly.combination(s1, s2, overlap);
        String s3Expected = "CATTTTAGCTGTTTTCGTTA";
        assertEquals(s3Expected, s3);
    }

    @Test
    public void testCombination3() {
        String s1 = "Go Bucks";
        String s2 = "Bucks -- Beat";
        int overlap = StringReassembly.overlap(s1, s2);
        String s3 = StringReassembly.combination(s1, s2, overlap);
        String s3Expected = "Go Bucks -- Beat";
        assertEquals(s3Expected, s3);
    }

    @Test
    public void testCombination4() {
        String s1 = "e Providence, we mutually pledge to each other our ~lives, our fo";
        String s2 = "er our ~lives, our fortunes and our sacred honor.~";
        int overlap = StringReassembly.overlap(s1, s2);
        String s3 = StringReassembly.combination(s1, s2, overlap);
        String s3Expected = "e Providence, we mutually pledge to each other our ~lives, our fortunes and our sacred honor.~";
        assertEquals(s3Expected, s3);
    }

    @Test
    public void testAddToSetAvoidingSubstrings1() {
        Set<String> st = new Set1L<>();
        st.add("Hotdog");
        String s2 = "dog";
        StringReassembly.addToSetAvoidingSubstrings(st, s2);
        String s3 = "Hotdog";
        String s3Expected = st.removeAny();
        assertEquals(s3Expected, s3);
    }

    @Test
    public void testAddToSetAvoidingSubstrings2() {
        Set<String> st = new Set1L<>();
        st.add("dog");
        String s2 = "Hotdog";
        StringReassembly.addToSetAvoidingSubstrings(st, s2);
        String s3 = "Hotdog";
        String s3Expected = st.removeAny();
        assertEquals(s3Expected, s3);
    }

    @Test
    public void testAddToSetAvoidingSubstrings3() {
        Set<String> st = new Set1L<>();
        st.add("Go Bucks");
        String s2 = "o Bucks";
        StringReassembly.addToSetAvoidingSubstrings(st, s2);
        String s3 = "Go Bucks";
        String s3Expected = st.removeAny();
        assertEquals(s3Expected, s3);
    }

    @Test
    public void testAddToSetAvoidingSubstrings4() {
        Set<String> st = new Set1L<>();
        st.add("E GETTYSBURG ADDRESS:~~~Four score and se");
        String s2 = "THE GETTYSBURG ADDRESS:~~~Four";
        StringReassembly.addToSetAvoidingSubstrings(st, s2);
        String s3 = "E GETTYSBURG ADDRESS:~~~Four score and se";
        String s3Expected = st.removeAny();
        assertEquals(s3Expected, s3);
    }

    @Test
    public void testLinesFromInput1() {
        Set<String> st = new Set1L<>();
        SimpleReader in = new SimpleReader1L("data/cheer-8-2.txt");
        st = StringReassembly.linesFromInput(in);
        assertEquals(st.toString(),
                "{Bucks -- Beat,Go Bucks,Beat Mich,Michigan~,o Bucks -- B}");
        in.close();
    }

    @Test
    public void testLinesFromInput2() {
        Set<String> st = new Set1L<>();
        SimpleReader in = new SimpleReader1L("data/gettysburg-30-6.txt");
        st = StringReassembly.linesFromInput(in);
        assertEquals(st.toString(),
                "{ testing whether that natio, to the ~proposition that all men are c,r any nation so ~concei,have consecrated,ey who fought here ~have thus far s,poor power to add or detract. The world,ong remember what we say here, but, gave the last full measure o,d here to the unfinished work which th,ated to the great task remaining be, have died in vain, that this ~,reat task remaining before us--that from ,. It is for us the living rather t,r God shall have a new birth of freedom, an,ome to dedicate a portion of ~that fi,ived and so dedicated can long endure. We,they did here. It is for us the living rat,ltogether fitting and ~proper that we sh,~Four score and seven years ago our fathers,government of the people, by the people, fo,eld of that war. We have come to dedicate a,truggled here have consecrat,tion that all men are created equal. No,ed ~it far above our poor power to a,e ~proposition that all men are cr, But in a larger sense, we cannot ~d,have a new birth of freedom, and that ~gover,ever ~forget what they did here. It is f,ed to the ~proposition that all men ar, ~proper that we should do th, ~a great civil war, testing whether,o our fathers brought forth on thi,n, living and dead who struggled ,d dead we take increased devotion to t,are engaged in ~a great ci,ve thus far so nobly advanced. It i,hat from these ~honored dead we take i, to add or detract. The world ,o the great task remaining before u,these dead shall not have died in v,-that from these ~honored dead we,h on this ~continent a new nation, c,ave come to dedicate a portion of,er that we should do this. But in a larger s,ed equal. Now we are engaged,ote nor long remember what we sa,ve consecrated ~it far above our poor p,e take increased devotion to that cause ,e brave men, living and dead who struggl,e, by the people, for the people shall ~not,evotion to that cause for whic, birth of freedom, and that ~government of t,d as a final resting-place for those who her,ught forth on this ~continent ,e ~dedicated to the great task rema,hall have a new birth of freedom, and tha,e living rather to be ~dedicated here t,THE GETTYSBURG ADDRESS:~~~Four score and ,rget what they did here. It is for u,hallow this ground. ~The brave me,~battlefield of that war. We h, shall not have died in vain, tha,se dead shall not have died in vain, th,on to that cause for which ~they gave the,war. We have come to dedicate a p,ense, we cannot ~dedicate, we cannot ,inent a new nation, conceived in lib,for us the living rather to be ~ded,per that we should do this. But in a l,t they did here. It is for us the livi,ting whether that nation or any natio,did here. It is for us the living rather ,ly ~resolve that these dead shall not ,go our fathers brought fort,thers brought forth on this ~contine,ether that nation or any nation s, who here gave their ~lives that that nat,is ~continent a new nation, conceived ,d or detract. The world will ~little note no,at we here highly ~resolve that these dead, a final resting-place for those who here g,roposition that all men are created equa,ork which they who fought here ~have ,ther for us to be here ~dedicated to ,ple shall ~not perish from the earth.~,r sense, we cannot ~dedicate, we can,dd or detract. The world will ~litt,efield of that war. We ha,le note nor long rem, cannot consecrate, we cannot hallow thi, ~honored dead we take increased de,living rather to be ~dedicated here to,even years ago our fathers brought for,on so ~conceived and so dedicated can lo,ake increased devotion to that cause for wh,re ~have thus far so nobly advanced. ,dicate a portion of ~that field as a fina,t nation might live. It is altogether, a great ~battlefield of th,dicated to the great task remaining, the last full measure of devotion--that we ,ed can long endure. We are m,d. It is rather for us to be here ~dedic,ated to the ~proposit,ht forth on this ~continent a new nation,,n long endure. We are met on a great ~bat, in vain, that this ~nation under God s,r so nobly advanced. It is rather,ting and ~proper that we should, it can never ~forget what they did here. I,rated ~it far above our poor power ,that this ~nation under God shall ha,at field as a final resting-place for,d work which they who fought here ~h,re highly ~resolve that these dead , vain, that this ~nation under God shall h,note nor long remem,icated to the great task remaining ,forget what they did here. It is for,w nation, conceived in liberty and dedicat, that nation might live. It is altogethe,e their ~lives that that nation might liv,aged in ~a great civil war, te,ho fought here ~have thus far so nobly a,f ~that field as a final r,is. But in a larger sen,ich ~they gave the last full measu,e of devotion--that we here highly ~resolve,consecrate, we cannot hallow this ground,people shall ~not perish from the e,~little note nor long r,mber what we say here, but it can never ~,ion under God shall have a}");
        in.close();
    }

    @Test
    public void testPrintWithLineSeparators1() {
        SimpleWriter out = new SimpleWriter1L(
                "data/testPrintWithLineSeparatorsResults.txt");
        SimpleReader in = new SimpleReader1L(
                "data/testPrintWithLineSeparators.txt");
        SimpleReader expected = new SimpleReader1L(
                "data/testPrintWithLineSeparatorsExpected.txt");
        SimpleReader results = new SimpleReader1L(
                "data/testPrintWithLineSeparatorsResults.txt");
        String str = in.nextLine();
        StringReassembly.printWithLineSeparators(str, out);
        String eLine1 = expected.nextLine();
        String eLine2 = expected.nextLine();
        String rLine1 = results.nextLine();
        String rLine2 = results.nextLine();
        assertEquals(eLine1, rLine1);
        assertEquals(eLine2, rLine2);
        out.close();
        in.close();
        expected.close();
        results.close();
    }

    @Test
    public void testPrintWithLineSeparators2() {
        SimpleWriter out = new SimpleWriter1L(
                "data/testPrintWithLineSeparatorsResults2.txt");
        SimpleReader in = new SimpleReader1L(
                "data/testPrintWithLineSeparators2.txt");
        SimpleReader expected = new SimpleReader1L(
                "data/testPrintWithLineSeparatorsExpected2.txt");
        SimpleReader results = new SimpleReader1L(
                "data/testPrintWithLineSeparatorsResults2.txt");
        String str = in.nextLine();
        StringReassembly.printWithLineSeparators(str, out);
        String eLine1 = expected.nextLine();
        String eLine2 = expected.nextLine();
        String eLine3 = expected.nextLine();
        String eLine4 = expected.nextLine();
        String eLine5 = expected.nextLine();
        String eLine6 = expected.nextLine();
        String eLine7 = expected.nextLine();
        String eLine8 = expected.nextLine();
        String eLine9 = expected.nextLine();
        String eLine10 = expected.nextLine();
        String rLine1 = results.nextLine();
        String rLine2 = results.nextLine();
        String rLine3 = results.nextLine();
        String rLine4 = results.nextLine();
        String rLine5 = results.nextLine();
        String rLine6 = results.nextLine();
        String rLine7 = results.nextLine();
        String rLine8 = results.nextLine();
        String rLine9 = results.nextLine();
        String rLine10 = results.nextLine();
        assertEquals(eLine1, rLine1);
        assertEquals(eLine2, rLine2);
        assertEquals(eLine3, rLine3);
        assertEquals(eLine4, rLine4);
        assertEquals(eLine5, rLine5);
        assertEquals(eLine6, rLine6);
        assertEquals(eLine7, rLine7);
        assertEquals(eLine8, rLine8);
        assertEquals(eLine9, rLine9);
        assertEquals(eLine10, rLine10);
        out.close();
        in.close();
        expected.close();
        results.close();
    }

}
