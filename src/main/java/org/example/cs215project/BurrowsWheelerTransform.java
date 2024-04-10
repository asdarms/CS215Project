package org.example.cs215project;

import java.util.ArrayList;
import java.util.List;

//Adapted from https://rosettacode.org/wiki/Burrows%E2%80%93Wheeler_transform

public class BurrowsWheelerTransform {

    public static String compress(String input) {
        if (input.contains("→") || input.contains("←")) {
            throw new IllegalArgumentException("String cannot contain → or ←");
        }

        String ss = "→" + input + "←";
        List<String> table = new ArrayList<>();
        for (int i = 0; i < ss.length(); i++) {
            String before = ss.substring(i);
            String after = ss.substring(0, i);
            table.add(before + after);
        }
        table.sort(String::compareTo);

        StringBuilder sb = new StringBuilder();
        for (String str : table) {
            sb.append(str.charAt(str.length() - 1));
        }
        return RunLengthEncoding.compress(sb.toString());
    }

    public static String decompress(String input) {
        input = RunLengthEncoding.decompress(input);
        int len = input.length();
        List<String> table = new ArrayList<>();
        for (int i = 0; i < len; ++i) {
            table.add("");
        }
        for (int j = 0; j < len; ++j) {
            for (int i = 0; i < len; ++i) {
                table.set(i, input.charAt(i) + table.get(i));
            }
            table.sort(String::compareTo);
        }
        for (String row : table) {
            if (row.endsWith("←")) {
                return row.substring(1, len - 1);
            }
        }
        return "";
    }

}