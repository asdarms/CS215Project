package org.example.cs215project;

import java.util.Collections;

//Adapted from https://www.baeldung.com/java-rle-compression

public class RunLengthEncoding {

    public static String compress(String input) {
        if (input.contains("‹") || input.contains("›")) {
            throw new IllegalArgumentException("String cannot contain ‹ or ›");
        }
        StringBuilder result = new StringBuilder();
        int count = 1;
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (i + 1 < chars.length && c == chars[i + 1]) {
                count++;
            } else if (count > 1) {
                result.append("‹").append(count).append("›").append(c);
                count = 1;
            } else {
                result.append(c);
                count = 1;
            }
        }
        return result.toString();
    }

    public static String decompress(String input) {
        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();

        int count = 1;
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
                result.append(String.join("", Collections.nCopies(count, String.valueOf(chars[i]))));
                count = 1;
            }
        }
        return result.toString();
    }

}
