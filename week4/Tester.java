package java_assignments.coursera2.week4;



import edu.duke.FileResource;

import java.util.HashSet;

public class Tester {
    public void testSliceString(){
        VigenereBreaker vigenereBreaker = new VigenereBreaker();
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 0, 3));
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 1, 3));
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 2, 3));
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 0, 4));
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 1, 4));
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 1, 5));
    }

    public void testTryKeyLength(){
        VigenereBreaker vigenereBreaker = new VigenereBreaker();
        String input = new FileResource("src/java_assignments/coursera2/week4/athens_keyflute.txt").asString();
        int[] keys = vigenereBreaker.tryKeyLength(input, 5, 'e');
        for(int key: keys) {
            System.out.println(key);
        }
    }

    public void testBreakVigenere(){
        VigenereBreaker vigenereBreaker = new VigenereBreaker();
        vigenereBreaker.breakVigenere();
    }

    public void testMostCommonCharIn(){
        VigenereBreaker vigenereBreaker = new VigenereBreaker();
        HashSet<String> dictionary = vigenereBreaker.readDictionary(new FileResource("src/java_assignments/coursera2/week4/dictionaries/English"));
        System.out.println(vigenereBreaker.mostCommonCharIn(dictionary));
    }

    public void testReadDictionary() {
        FileResource fr = new FileResource();
        VigenereBreaker vigenereBreaker = new VigenereBreaker();
        HashSet<String> set = vigenereBreaker.readDictionary(fr);
        for (String item :  set)
            System.out.println(item);
    }

    public void testCountWords() {
        VigenereBreaker vigenereBreaker = new VigenereBreaker();
        HashSet<String> dict = vigenereBreaker.readDictionary(new FileResource("src/java_assignments/coursera2/week4/dictionaries/English"));
        System.out.println(vigenereBreaker.countWords(new FileResource().asString(), dict));

    }

    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.testSliceString();
        tester.testTryKeyLength();
        tester.testBreakVigenere();
        tester.testMostCommonCharIn();
        tester.testReadDictionary();
        tester.testCountWords();
    }
}