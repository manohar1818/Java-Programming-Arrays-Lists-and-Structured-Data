package java_assignments.coursera2.week2;


import edu.duke.FileResource;
import java.util.HashMap;

public class CodonCount{
    private HashMap<String, Integer> codons;
    public CodonCount(){
        codons = new HashMap<String, Integer>();
    }

    //  This method will build a new map of codons mapped to their counts from the string dna
    //  with the reading frame with the position start (a value of 0, 1, or 2).
    public void buildCodonMap(int start, String dna) {
        // we need to call this method several times, so map should be made empty.
        codons.clear();
        // length of each codon should be 3
        for (int index = start; index < dna.length() - 3; index = index + 3) {
            String currCodon = dna.substring(index, index + 3);
            // putting current codon values into hashMap codons
            if (codons.containsKey(currCodon))
                codons.put(currCodon , codons.get(currCodon)+ 1);
            else
                codons.put(currCodon, 1);
        }
    }

    //   This method returns a String, the codon in a reading frame that has the largest count
    public String getMostCommonCodon() {
        int max = 0;
        String mostRepeatedCodon = "";
        for (String currCodon : codons.keySet()) {
            int count = codons.get(currCodon);
            if (count > max) {
                max = count;
                mostRepeatedCodon = currCodon;
            }
        }
        return mostRepeatedCodon;
    }

    // This method prints all the codons in the HashMap along with their counts if their count is between start and end, inclusive.
    public void printCodonCounts(int start, int end) {
        for(String currCodon : codons.keySet()) {
            int count = codons.get(currCodon);
            if (count >= start && count <= end)
                System.out.println(currCodon + " : " + count);
        }
    }

    public void tester(){
        //prompts the user for a file
        FileResource fileResource = new FileResource();
        // converting to string
        String dnaStrand = fileResource.asString();
        // converting them all to uppercase, since case should not matter
        dnaStrand = dnaStrand.toUpperCase();
        System.out.println(dnaStrand);
        for(int i = 0; i < 3; i++) {
            buildCodonMap(i, dnaStrand);
            System.out.println("Reading frame starting with  " + i +" results in " + codons.size() + " unique codons");
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("and the most common codon is  " + mostCommonCodon + " with count " + codons.get(mostCommonCodon));
            System.out.println("Counts of codons between 1 and 5 Inclusive are:");
            printCodonCounts(1, 5);
        }
    }

    public static void main(String[] args) {
        CodonCount codonCount = new CodonCount();
        codonCount.tester();
    }
}