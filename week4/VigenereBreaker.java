package java_assignments.coursera2.week4;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            stringBuilder.append(message.charAt(i));
        }
        return stringBuilder.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker caesarCracker = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            key[i] = caesarCracker.getKey(slice);
        }
        return key;
    }

    public void breakVigenere () {
        String input = new FileResource("src/java_assignments/coursera2/week4/messages/secretmessage1.txt").asString();
        HashMap<String, HashSet<String>> languagesDictionary = readAllDictionaries();
        String deciphered = breakForAllLangs(input, languagesDictionary);
        System.out.println(deciphered);
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> set = new HashSet<>();
        for (String line : fr.lines())
            set.add(line.toLowerCase());
        return set;
    }

    public HashMap<String, HashSet<String>> readAllDictionaries(){
        HashMap<String, HashSet<String>> languagesDictionary = new HashMap<>();
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for(String language: languages) {
            FileResource fileResource = new FileResource("src/java_assignments/coursera2/week4/dictionaries/" + language);
            HashSet<String> dictionary = readDictionary(fileResource);
            languagesDictionary.put(language, dictionary);
        }
        return languagesDictionary;
    }

    public int countWords(String message, HashSet<String> dict) {
        int valid = 0;
        message = message.toLowerCase();
        String[] words = message.split("\\W");
        for (String word : words)
            if(dict.contains(word))
                valid++;
        return valid;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        Map<Character, Integer> count = new HashMap<>();
        for(String word: dictionary) {
            for(int i = 0; i < word.length(); i++) {
                if(Character.isAlphabetic(word.charAt(i))) {
                    char ch = Character.toLowerCase(word.charAt(i));
                    if(count.containsKey(ch)) {
                        count.put(ch, count.get(ch) + 1);
                    } else {
                        count.put(ch, 1);
                    }
                }
            }
        }
        int freq = 0;
        char most = 'e';
        for(char ch: count.keySet()) {
            if(freq < count.get(ch)) {
                freq = count.get(ch);
                most = ch;
            }
        }
        return most;
    }

    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int freq = 0;
        String mostUsedLanguage = new String("");
        for (String language : languages.keySet()) {
            int cnt = countWords(encrypted, languages.get(language));
            if(cnt > freq) {
                mostUsedLanguage = language;
                freq = cnt;
            }
        }
        String deciphered = breakForLanguage(encrypted, languages.get(mostUsedLanguage));
        return deciphered;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int wordsValid = 0;
        String deciphered = new String("");
        for(int i = 1; i <= 100; i++){
            char ch = mostCommonCharIn(dictionary);
            int[] keys = tryKeyLength(encrypted, i, ch);
            VigenereCipher vigenereCipher = new VigenereCipher(keys);
            String currDeciphered = vigenereCipher.decrypt(encrypted);
            int currWordsValid = countWords(currDeciphered, dictionary);
            if(currWordsValid > wordsValid){
                wordsValid = currWordsValid;
                deciphered = currDeciphered;
            }
        }
        return deciphered;
    }
}