package org.example.cs215project;

import java.util.Collections;

// Adapted from https://www.baeldung.com/java-rle-compression

public class RunLengthEncoding {

    public static String compress(String input) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (i + 1 < chars.length && c == chars[i + 1]) {
                count++;
            } else {
                result.append(count).append(c);
                count = 1;
            }
        }
        return result.toString();
    }

    public static String decompress(String input) {
        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();

        int count = 0;
        for (char c : chars) {
            if (Character.isDigit(c)) {
                count = 10 * count + Character.getNumericValue(c);
            } else {
                result.append(String.join("", Collections.nCopies(count, String.valueOf(c))));
                count = 0;
            }
        }
        return result.toString();
    }

}
