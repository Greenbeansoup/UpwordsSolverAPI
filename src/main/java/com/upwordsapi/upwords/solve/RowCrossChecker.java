package com.upwordsapi.upwords.solve;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.quinto.dawg.DAWGSet;

public class RowCrossChecker {
    private List<List<List<Character>>> grid;
    private DAWGSet dawg;
    private Set<Character> hand;

    public RowCrossChecker(List<List<List<Character>>> grid, Set<Character> hand, DAWGSet dawg) {
        this.grid = grid;
        this.dawg = dawg;
        this.hand = hand;
    }

    /**
     * Computes the valid characters that can be placed in a given row by iterating over the
     * individual grid locations and looking at the contiguous letters
     * above and below it. (This is referred to as a cross-check).
     * @param rowInd The row for which to run the cross-check.
     * @return A list containing sets of valid characters for each of the corresponding spaces in the indicated row.
     */
    public List<Set<Character>> computeCrossChecks(int rowInd) {
        List<List<Character>> row = grid.get(rowInd);
        List<Set<Character>> legalCharacters = new ArrayList<>();
        for (int i = 0; i < row.size(); i++) { // iterate over columns
            StringBuilder prefix = new StringBuilder();
            StringBuilder suffix = new StringBuilder();
            Set<Character> columnCharacters = new HashSet<>();
            legalCharacters.add(columnCharacters);
            for (int j = 0; j < rowInd; j++) { // iterate over rows up to the row in question
                int stackSize = grid.get(j).get(i).size();
                Character topChar = grid.get(j).get(i).get(stackSize - 1);
                if (Character.isAlphabetic(topChar)) prefix.append(topChar);
                else if (!prefix.isEmpty()) prefix.setLength(0);
            }
            for (int j = rowInd + 1; j < grid.size(); j++) { // iterate over rows after row in question
                int stackSize = grid.get(j).get(i).size();
                Character topChar = grid.get(j).get(i).get(stackSize - 1);
                if (Character.isAlphabetic(topChar)) suffix.append(topChar);
                else break;
            }
            if (hand.size() > 0) {
                for (Character c : hand) {
                    String word = prefix.toString() + c + suffix.toString();
                    if (row.get(i).get(row.get(i).size() - 1) != c && dawg.contains(word)) {
                        columnCharacters.add(c);
                    }
                }
            } 
            else {
                for (int j = 0; j < 26; j++) { // iterate over all letters of the alphabet
                    Character tileCharacter = (char) ('A' + j);
                    String word = prefix.toString() + Character.toString((char) ('A' + j)) + suffix.toString();
                    if (row.get(i).get(row.get(i).size() - 1) != tileCharacter && dawg.contains(word)) { // checking to see if the potential word exists in the dawg and also making sure the top character is not just the existing character
                        columnCharacters.add((char) ('A' + j));
                    }
                }
            }
        }

        return legalCharacters;
    }
}
