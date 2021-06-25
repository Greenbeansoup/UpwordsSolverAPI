package com.upwordsapi.upwords;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static com.upwordsapi.upwords.TestUtils.rowBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.upwordsapi.upwords.board.BoardState;
import com.upwordsapi.upwords.solve.BoardSolveType;
import com.upwordsapi.upwords.solve.BoardSolverFactory;
import com.upwordsapi.upwords.solve.IBoardSolver;

import org.junit.jupiter.api.Test;
import org.quinto.dawg.DAWGSet;
import org.quinto.dawg.ModifiableDAWGSet;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class BoardStateControllerTest {

    /**
     * Test for {@link BoardStateController#BoardStateController()}.<P/>
     * Result: A controller is created with a properly instantiated {@link DAWGSet}.
     */
    @Test
    public void boardStateControllerConstructorTest() {
        DAWGSet testDawg = new ModifiableDAWGSet();
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("dictionary.txt");
        Scanner sc = new Scanner(new InputStreamReader(stream));
        while (sc.hasNextLine()) {
            testDawg.add(sc.nextLine());
        }
        BoardStateController controller = new BoardStateController();
        try {
            Field dawgField = BoardStateController.class.getDeclaredField("dawg");
            dawgField.setAccessible(true);
            DAWGSet dawg = (DAWGSet) dawgField.get(controller);
            assertEquals(testDawg, dawg); 
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test for {@link BoardStateController#solve(BoardState)}.
     * Result: The board upwords solver is created from the factory and called.
     */
    @Test
    public void solveTest() {
        IBoardSolver mockSolver = mock(IBoardSolver.class);
        BoardSolverFactory.registerSupplier(BoardSolveType.UPWORDS, () -> mockSolver);
        List<List<List<Character>>> grid = new ArrayList<>();
        List<List<Character>> row1 = rowBuilder(Arrays.asList("AAA", "BBB", "CCC"));
        grid.add(row1);
        List<List<Character>> row2 = rowBuilder(Arrays.asList("DDD", "EEE", "FFF"));
        grid.add(row2);
        List<List<Character>> row3 = rowBuilder(Arrays.asList("GGG", "HHH", "III"));
        grid.add(row3);
        List<Character> hand = Arrays.asList('L', 'R', 'O');
        BoardState mockState = new BoardState(grid, hand);
        when(mockSolver.solve(isA(BoardState.class), isA(DAWGSet.class))).thenReturn(mockState);
        BoardStateController controller = new BoardStateController();
        ResponseEntity<BoardState> mockResponse = ResponseEntity.ok().body(mockState);

        ResponseEntity<BoardState> response = controller.solve(new BoardState(grid, hand));

        assertEquals(mockResponse, response);
    }
}
