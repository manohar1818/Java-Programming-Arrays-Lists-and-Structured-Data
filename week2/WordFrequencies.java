package java_assignments.coursera2.week2;


import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {
    // used to store unique words in a file
    private ArrayList<String> myWords;
    // used to store frequency of unique words in a file
    private ArrayList<Integer> myFreqs;
    public WordFrequencies() {
        //initialize the private variables.
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    // it selects a file and then iterates over every word in the file,
    // putting the unique words found into myWords.
    // For each word in the kth position of myWords, it puts the count of how many times
    // that word occurs from the selected file into the kth position of myFreqs,
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        // Selecting a file
        FileResource file = new FileResource("src/java_assignments/coursera2/week2/testwordfreqs.txt");
        for(String currWord : file.words()) {
            // To eliminate case sensitive problems
            currWord = currWord.toLowerCase();
            if(!myWords.contains(currWord)) {
                // adds elements to the arraylist
                myWords.add(currWord);
                myFreqs.add(1);
            } else {
                // Used to find the index of words which are already in myWords
                int i = myWords.indexOf(currWord);
                // Used to find value in myFreqs using index
                int frequency = myFreqs.get(i);
                // Modify the myFreqs value
                myFreqs.set(i, frequency + 1);
                // .get is used to get the value based on index
                // .set is used to set the value based on index
            }
        }
    }


    // This method returns an int that is the index location of the largest value in myFreqs.
    public int findIndexOfMax() {
        // reqindex is the required index
        int reqindex = 0;
        int maxFreq = myFreqs.get(reqindex);
        for (int i = 0; i < myFreqs.size(); i++){
            int currFreq = myFreqs.get(i);
            // if current frequency is greater than max frequency then update the max frequency, index
            if (currFreq > maxFreq) {
                maxFreq = currFreq;
                reqindex = i;
            }
        }
        return reqindex;
    }

    public void tester() {
        findUnique();
        System.out.println("Number of unique words:" + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            String currWord = myWords.get(i);
            int frequency = myFreqs.get(i);
            System.out.println(frequency + " " + currWord);
        }

        // used to get the index of max frequency word
        int reqIndex = findIndexOfMax();
        // max frequency word
        String word = myWords.get(reqIndex);
        // frequency of word
        int frequency = myFreqs.get(reqIndex);
        System.out.println("The word that occurs most often and its count are: " + word + " "+ frequency);
    }

    public static void main(String[] args) {
        WordFrequencies wordFrequencies = new WordFrequencies();
        wordFrequencies.tester();
    }

}