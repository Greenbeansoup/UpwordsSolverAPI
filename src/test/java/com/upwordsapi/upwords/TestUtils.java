package com.upwordsapi.upwords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    /**
     * Convenience method for building out rows of character columns from lists of strings.
     * @param input List of strings to convert to list of characters.
     * @return List of Lists of characters that represent an upwords grid row.
     */
    public static List<List<Character>> rowBuilder(List<String> input) {
        List<List<Character>> row = new ArrayList<>();
        for (String s : input) {
            List<Character> column = Arrays.asList(s.chars().mapToObj(c -> (char)c).toArray(Character[]::new));
            row.add(column);
        }
        return row;
    }
}
