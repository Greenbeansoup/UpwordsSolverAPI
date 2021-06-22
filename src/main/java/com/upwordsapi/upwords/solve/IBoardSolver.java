package com.upwordsapi.upwords.solve;

import java.util.List;
import java.util.Set;

import com.upwordsapi.upwords.board.BoardState;

import org.quinto.dawg.DAWGSet;

public interface IBoardSolver {
    /**
     * Finds the best play possible in a given upwords board state.
     * @param boardState The board to search.
     * @param dawg The dictionary of valid words.
     * @return A new board containing the best possible move.
     */
    public BoardState solve(BoardState boardState, DAWGSet dawg);
    
    /**
     * Finds all possible plays in the grid by running cross-checks along each row and column.
     * @param grid The grid to investigate.
     * @param hand The playable tiles in the hand.
     * @param dawg The dictionary.
     * @return A 2D list containing sets representing all possible plays for each corresponding position in grid.
     */
    public List<List<Set<Character>>> findLegalCharacters(List<List<List<Character>>> grid, Set<Character> hand, DAWGSet dawg);
}
