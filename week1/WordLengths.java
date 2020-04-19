package java_assignments.coursera2.week1;

import edu.duke.FileResource;

public class WordLengths {
    public void countWordLengths(FileResource resource, int [] counts) {
        for(String word: resource.words()){
            int length = word.length();
            if(!Character.isLetter(word.charAt(0)))
                length--;
            if(!Character.isLetter(word.charAt(word.length() - 1)))
                length--;
            if(length < counts.length)
                counts[length]++;
            else
                counts[counts.length - 1]++;
        }
    }

    public int indexOfMax(int[] values) {
        int index = 0;
        int maxCount = values[0];
        for(int i = 1; i < values.length; i++){
            if(values[i] > maxCount){
                maxCount = values[i];
                index = i;
            }
        }
        return index;
    }

    public void testCountWordLengths(){
        FileResource fileResource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fileResource,counts);
        for(int i = 0; i < counts.length; i++) {
            if(counts[i] == 0) continue;
            System.out.println(counts[i] + " words of length " + i);
        }
        System.out.println(indexOfMax(counts));
    }

    public static void main(String[] args) {
        WordLengths wordLengths = new WordLengths();
        wordLengths.testCountWordLengths();
    }
}