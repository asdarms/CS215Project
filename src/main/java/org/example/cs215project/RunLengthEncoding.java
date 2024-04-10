package org.example.cs215project;

import java.util.Collections;

//Adapted from https://www.baeldung.com/java-rle-compression

public class RunLengthEncoding {

    //Time Complexity: O(n)
    public static String compress(String input) {
        //checks if string contains the escape characters
        if (input.contains("‹") || input.contains("›")) {
            throw new IllegalArgumentException("String cannot contain ‹ or ›");
        }
        //variable setup
        StringBuilder result = new StringBuilder();
        int count = 1;
        char[] chars = input.toCharArray();
        //for each character in the string, it attempts to count repeated characters and replaces them with a count and then the character if there are more than one
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (i + 1 < chars.length && c == chars[i + 1]) {
                count++;
            } else if (count > 1) {
                //these characters surround the count of the upcoming character so the decompression algorithm knows the difference between actual digits and digits used for counting
                result.append("‹").append(count).append("›").append(c);
                count = 1;
            } else {
                result.append(c);
                count = 1;
            }
        }
        return result.toString();
    }

    //Time Complexity: O(n)
    public static String decompress(String input) {
        //variable setup
        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        int count = 1;
        //for each character in the string, it looks for escape characters and then adds that character that many times to the output string
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '‹') {
                StringBuilder number = new StringBuilder();
                i++;
                while (chars[i] != '›') {
                    number.append(chars[i]);
                    i++;
                }
                count = Integer.parseInt(number.toString());
            } else {
                //this line adds a character n times depending on the value of count
                result.append(String.join("", Collections.nCopies(count, String.valueOf(chars[i]))));
                count = 1;
            }
        }
        return result.toString();
    }

}
