package org.example.cs215project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

//Adapted from https://github.com/tonyking97/java-lzw-compression/tree/master

public class LempelZivWelch {

    //Time Complexity: O(n)
    public static String compress(String input) {
        //variable setup
        HashMap<String, Integer> dictionary = new LinkedHashMap<>(); //dictionary of phrases
        String[] data = (input + "").split(""); //input data as character array
        String out = ""; //output string
        ArrayList<String> temp_out = new ArrayList<>(); //character array to hold output
        String currentChar; //current character being processed
        String phrase = data[0]; //starting phrase (first character) that is looked for in the input and compressed
        int code = 256; //code to be used in the dictionary
        //iterates through input char array looking for increasingly larger phrases to be compressed
        for (int i = 1; i < data.length; i++) {
            //sets currentchar to the current character being analyzed
            currentChar = data[i];
            //checks if phrase + currentchar is in the dictionary, and if it is, adds it to the phrase
            if (dictionary.get(phrase + currentChar) != null) {
                phrase += currentChar;
            } else {
                //if the current phrase is greater than 1 character, adds the corresponding unicode char to its dictionary code to the output
                if (phrase.length() > 1) {
                    temp_out.add(Character.toString((char) dictionary.get(phrase).intValue()));
                } else { //if the current phrase is just 1 character, adds the character to the output
                    temp_out.add(Character.toString((char) Character.codePointAt(phrase, 0)));
                }
                //adds phrase + currentchar to dictionary
                dictionary.put(phrase + currentChar, code);
                //increases code value so each dictionary value is unique
                code++;
                //sets the next phrase to the current character
                phrase = currentChar;
            }
        }
        //if the final phrase is greater than 1 character, adds the corresponding unicode char to its dictionary code to the output
        if (phrase.length() > 1) {
            temp_out.add(Character.toString((char) dictionary.get(phrase).intValue()));
        } else { //if phrase is 1 character, adds it to the output
            temp_out.add(Character.toString((char) Character.codePointAt(phrase, 0)));
        }
        //adds each character in the output array to the output string
        for (String outchar : temp_out) {
            out += outchar;
        }
        return out;
    }

    //Time Complexity: O(n)
    public static String decompress(String input) {
        //variable setup
        HashMap<Integer, String> dictionary = new LinkedHashMap<>(); //dictionary of phrases
        String[] data = (input + "").split(""); //input string as a character array
        String currentChar = data[0]; //current character, starts as first char
        String oldPhrase = currentChar; //old phrase, starts as first char
        String out = currentChar; //output string
        int code = 256; //code to be used in the dictionary
        String phrase = ""; //current phrase, starts empty
        //iterates through input character array
        for (int i = 1; i < data.length; i++) {
            int currCode = Character.codePointAt(data[i], 0); //the unicode value of the current char being looked at
            //if code is less than 256, meaning its probably a normal character and not a multi-char phrase, it is set as the current phrase
            if (currCode < 256) {
                phrase = data[i];
            } else { //if the code is above 256, meaning its probably a multichar phrase, then it is looked for in the dictionary
                if (dictionary.get(currCode) != null) {
                    //if found in the dictionary, the current phrase is set to the corresponding dictionary value to the current code
                    phrase = dictionary.get(currCode);
                } else {
                    //if not found in the dictionary, the current phrase is set the old phrase + the current character
                    phrase = oldPhrase + currentChar;
                }
            }
            //adds the current phrase to the output
            out += phrase;
            //sets currentchar to the first char in the current phrase
            currentChar = phrase.substring(0, 1);
            //adds the current phrase to the dictonary with index value as current code
            dictionary.put(code, oldPhrase + currentChar);
            //increases code by 1 to avoid duplicate dictionary entries
            code++;
            //old phrase is set to the current phrase
            oldPhrase = phrase;
        }
        return out;
    }
}
