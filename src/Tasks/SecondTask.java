package Tasks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class SecondTask {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("D:\\Java Projects\\Lab6-Lambda\\src\\Tasks\\Words.txt");
        BufferedReader bf = new BufferedReader(fileReader);

        String[] words = new String[5];
        int i = 0;
        try {
            String line;
            while ((line = bf.readLine()) != null) {
                words[i] = line;
                i++;
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int fiveLettersWord = 0;
        int palindromes = 0;
        int wordsStartingWithW = 0;
        for (String word : words) {
            if (word.length() == 5) {
                fiveLettersWord++;
            }
            if (word.charAt(0) == 'w' | word.charAt(0) == 'W') {
                wordsStartingWithW++;
            }
            if (isPalindromes(word)) {
                palindromes++;
            }
        }
        System.out.println("5 Letters words = " + fiveLettersWord);
        System.out.println("Palindrome words = " + palindromes);
        System.out.println("Words starting with w = " + wordsStartingWithW);
    }

    public static boolean isPalindromes(String word) {
        boolean key = true;
        char[] letters = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            letters[i] = word.charAt(i);
        }

        int s = word.length() -1;
        for (int i = 0; i < word.length(); i++) {
            if (i > s) {
                break;
            }
            if (letters[i] != letters[s]) {
                key = false;
            }
            s--;
        }
        return key;
    }
}

