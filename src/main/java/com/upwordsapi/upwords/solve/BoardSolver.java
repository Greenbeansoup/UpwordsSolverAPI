package com.upwordsapi.upwords.solve;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.upwordsapi.upwords.board.BoardState;

import org.quinto.dawg.DAWGSet;

public class BoardSolver implements IBoardSolver {
    @Override
    public BoardState solve(BoardState boardState, DAWGSet dawg) {
        List<List<List<Character>>> grid = boardState.getGrid();
        Set<Character> hand = new HashSet<Character>(boardState.getHand());

        List<List<Set<Character>>> legalPlays = findLegalCharacters(grid, hand, dawg);
        // TODO: Use the legal plays to solve the board.

        return boardState;
    }

    @Override
    public List<List<Set<Character>>> findLegalCharacters(List<List<List<Character>>> grid, Set<Character> hand, DAWGSet dawg) {
        int N = grid.size(); // Board must be square for this to work.

        RowCrossChecker checker = new RowCrossChecker(grid, hand, dawg);
        List<List<Set<Character>>> playableChars = new ArrayList<>();
        for (int i = 0; i < N; i++) { // iterate over number of rows
            playableChars.add(checker.computeCrossChecks(i));
        }

        checker = new RowCrossChecker(transpose(grid), hand, dawg); // transpose the board to cross check columns
        List<List<Set<Character>>> playableCharsByColumn = new ArrayList<>();
        for (int i = 0; i < N; i++) { // iterate over number of columns
            playableCharsByColumn.add(checker.computeCrossChecks(i));
        }

        // Here I'm looping over the transposed results set and converting it back to the original coordinates to find where they intersect,
        // leaving me with a single 2D list of sets.
        for (int i = 0; i < N; i++) {
            List<Set<Character>> column = playableCharsByColumn.get(i);
            for (int j = 0; j < N; j++) {                               
                Set<Character> set = playableChars.get(j).get(i);
                set.retainAll(column.get(j)); // Intersecting the two sets and putting the result in the set contained in playableChars
            }
        }

        return playableChars;
    }

    /**
     * Method to transpose a 2D list.
     * @param <T> The type of the inner list.
     * @param grid The 2D list to transpose.
     * @return A clockwise transposition of the original input list.
     */
    private static <T> List<List<T>> transpose(List<List<T>> grid) {
        List<List<T>> transposed = new ArrayList<List<T>>();
        for (int i = 0; i < grid.get(0).size(); i++) {
            List<T> col = new ArrayList<T>();
            for (List<T> row : grid) {
                col.add(row.get(i));
            }
            transposed.add(col);
        }
        return transposed;
    }
}
