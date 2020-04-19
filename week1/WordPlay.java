package java_assignments.coursera2.week1;


public class WordPlay {
    //This method returns true if ch is a vowel (one of 'a', 'e', 'i', 'o', or 'u' or the uppercase versions) and false otherwise
    public boolean isVowel(char ch) {
        // converts ch to lower case
        ch = Character.toLowerCase(ch);
        if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u')
            return true;
        else
            return false;
    }

    //This method should return a String that is the string phrase with all the vowels (uppercase or lowercase) replaced by ch.
    public String replaceVowels(String phrase, char ch) {
        for(int i = 0; i < phrase.length(); i++) {
            if(isVowel(phrase.charAt(i))){
                // string replaceAll(String , String)
                // So it converts char to string using Character.toString()
                phrase = phrase.replaceAll(Character.toString(phrase.charAt(i)), Character.toString(ch));
            }
        }
        return phrase;
    }

    //This method should return a String that is the string phrase but with the Char ch
    //‘*’ if it is in an odd number location in the string
    //‘+’ if it is in an even number location in the string
    public String emphasize(String phrase, char ch) {
        // we need to replace characters in phrase, so any Upper or lower case won't matter
        ch = Character.toLowerCase(ch);
        StringBuilder s = new StringBuilder(phrase);
        for(int i = 0; i < s.length(); i++) {
            // for comparing purpose we are convert characters in s to lowercase
            char c = Character.toLowerCase(s.charAt(i));
            if(c == ch) {
                // if index is even number(i.e at odd place) then replace with *
                if ((i % 2) == 0)
                    s.setCharAt(i, '*');
                // if index is odd number(i.e at even place) then replace with +
                else
                    s.setCharAt(i, '+');
            }
        }
        String result = s.toString();
        return result;
    }


    public void test(){
        System.out.println(isVowel('a'));
        System.out.println(replaceVowels("Manohar", '*'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }

    public static void main(String[] args) {
        System.out.println("Testing all methods ");
        WordPlay wordPlay = new WordPlay();
        wordPlay.test();

    }

}
