package org.example.cs215project;

import java.util.ArrayList;
import java.util.List;

//Adapted from https://rosettacode.org/wiki/Burrows%E2%80%93Wheeler_transform

public class BurrowsWheelerTransform {

    //Time Complexity: O(n + n * log n)
    public static String compress(String input) {
        //checks if the input string contains any escape characters that are used by the algorithm itself
        if (input.contains("→") || input.contains("←")) {
            throw new IllegalArgumentException("String cannot contain → or ←");
        }
        //variable setup, adds escape characters before and after input string
        String ss = "→" + input + "←";
        List<String> table = new ArrayList<>();
        //for each character in the string, it adds everything before that character and after that character to a table, essentially rotating around the escape characters
        for (int i = 0; i < ss.length(); i++) {
            String before = ss.substring(i);
            String after = ss.substring(0, i);
            table.add(before + after);
        }
        //the table of rotations is sorted lexicographically
        table.sort(String::compareTo);
        StringBuilder sb = new StringBuilder();
        //for each rotation in the table, the final character is added to an output string, resulting in every original character being included
        for (String str : table) {
            sb.append(str.charAt(str.length() - 1));
        }
        //the transformed output is then run-length encoded as the transform process does not compress the data, it just makes it easier to be compressed
        return RunLengthEncoding.compress(sb.toString());
    }

    //Time Complexity: O(n^3 * log n)
    public static String decompress(String input) {
        //the input is first decompressed using run-length encoding as it was compressed.
        input = RunLengthEncoding.decompress(input);
        //variable setup
        int len = input.length();
        List<String> table = new ArrayList<>();
        //creates a table of the length of the string with dummy values
        for (int i = 0; i < len; ++i) {
            table.add("");
        }
        //
        for (int j = 0; j < len; j++) {
            for (int i = 0; i < len; i++) {
                //for each character, it rotates around the escape characters and continually adds characters to the table, storing each rotation as an index
                table.set(i, input.charAt(i) + table.get(i));
            }
            //after each character has been rotated and then added to the table, the table is sorted
            table.sort(String::compareTo);
        }
        //for each substring now in the table, it checks if it is the correct one, meaning the correct escape character is at the end, and then returns that substring, which should be the original string
        for (String row : table) {
            if (row.endsWith("←")) {
                return row.substring(1, len - 1);
            }
        }
        //dummy return statement, should never be reached if proper data is inserted
        return "";
    }

}