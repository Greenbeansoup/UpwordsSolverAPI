package com.upwordsapi.upwords;

import static com.upwordsapi.upwords.TestUtils.rowBuilder;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.quinto.dawg.DAWGSet;
import org.quinto.dawg.ModifiableDAWGSet;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardSolverTest {
    
    /**
     * Test for {@link BoardSolver.findLegalCharacters(List, DAWGSet)}.
     * Case: There are 3 possible plays that make words.
     * Result: All 3 plays are returned and nothing more.
     */
    @Test
    public void testfindLegalCharacters_smallBoard() {
        DAWGSet dawg = new ModifiableDAWGSet();
        List<Character> hand = new ArrayList<>();
        // There are more than 3 possible ways to make a word if you only check vertically or horizontally
        dawg.addAll(Arrays.asList("BOH", "DOF", "SBC", "SDG", "ABZ", "ZFI", "ADI"));
        List<List<Set<Character>>> validChars = new ArrayList<>();
        List<Set<Character>> chars1 = new ArrayList<>();
        chars1.add(new HashSet<>(Arrays.asList(Character.valueOf('S'))));
        chars1.add(new HashSet<>());
        chars1.add(new HashSet<>(Arrays.asList(Character.valueOf('Z'))));
        validChars.add(chars1);

        List<Set<Character>> chars2 = new ArrayList<>();
        chars2.add(new HashSet<>());
        chars2.add(new HashSet<>(Arrays.asList(Character.valueOf('O'))));
        chars2.add(new HashSet<>());
        validChars.add(chars2);

        List<Set<Character>> chars3 = new ArrayList<>();
        chars3.add(new HashSet<>());
        chars3.add(new HashSet<>());
        chars3.add(new HashSet<>());
        validChars.add(chars3);

        List<List<List<Character>>> grid = new ArrayList<>();
        List<List<Character>> row1 = rowBuilder(Arrays.asList("AAA", "BBB", "CCC"));
        grid.add(row1);
        List<List<Character>> row2 = rowBuilder(Arrays.asList("DDD", "*", "FFF"));
        grid.add(row2);
        List<List<Character>> row3 = rowBuilder(Arrays.asList("GGG", "HHH", "III")); // Replacing G with I would make
        grid.add(row3);                                                              // ADI which is a word, but
                                                                                     // it would also make IHI which is
                                                                                     // not a word acc. the dawg.

        List<List<Set<Character>>> checkedChars = BoardSolver.findLegalCharacters(grid, dawg);

        assertArrayEquals(validChars.toArray(), checkedChars.toArray());
    }
}
