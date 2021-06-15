package com.upwordsapi.upwords;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.quinto.dawg.DAWGSet;

public class BoardSolver {
    public static BoardState solve(BoardState boardState, DAWGSet dawg) {
        List<List<List<Character>>> grid = boardState.getGrid();
        Deque<Character> hand = new ArrayDeque<Character>(boardState.getHand());

        

        for (List<List<Character>> row : grid) {
            if (hand.size() == 0) break;
            for (List<Character> col : row) {
                if (hand.size() == 0) break;
                col.add(hand.poll());
            }
        }

        return boardState;
    }

    /**
     * Method to transpose a 2D list.
     * @param <T> The type of the inner list.
     * @param table The 2D list to transpose.
     * @return The same 2D list but transposed.
     */
    private static <T> List<List<T>> transpose(List<List<T>> table) {
        List<List<T>> ret = new ArrayList<List<T>>();
        for (int i = 0; i < table.get(0).size(); i++) {
            List<T> col = new ArrayList<T>();
            for (List<T> row : table) {
                col.add(row.get(i));
            }
            ret.add(col);
        }
        return ret;
    }
}
