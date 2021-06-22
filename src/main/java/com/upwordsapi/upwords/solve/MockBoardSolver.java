package com.upwordsapi.upwords.solve;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Set;

import com.upwordsapi.upwords.board.BoardState;

import org.quinto.dawg.DAWGSet;

public class MockBoardSolver implements IBoardSolver {

    @Override
    public BoardState solve(BoardState boardState, DAWGSet dawg) {
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

    @Override
    public List<List<Set<Character>>> findLegalCharacters(List<List<List<Character>>> grid, Set<Character> hand, DAWGSet dawg) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
