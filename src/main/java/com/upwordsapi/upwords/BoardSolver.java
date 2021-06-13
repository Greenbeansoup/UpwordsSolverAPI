package com.upwordsapi.upwords;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class BoardSolver {
    public static BoardState solve(BoardState boardState) {
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
}
