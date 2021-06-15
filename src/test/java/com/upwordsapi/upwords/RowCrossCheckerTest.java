package com.upwordsapi.upwords;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.quinto.dawg.DAWGSet;
import org.quinto.dawg.ModifiableDAWGSet;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RowCrossCheckerTest {
    
    /**
     * Test for {@link RowCrossChecker.computeCrossChecks(int)}.<P/>
     * Case: The middle row of a 3x3 grid is examined.<P/>
     * Result: The top and bottom rows are examined to create 'words'
     *  according to the dawg and the corresponding characters are returned in a list of lists.
     */
    @Test
    public void testComputeCrossChecks_smallGridMidRow() {
        DAWGSet dawg = new ModifiableDAWGSet();
        dawg.addAll(Arrays.asList("ALG", "BRH", "COI", "AAG", "FFF", "ASP"));
        List<Set<Character>> validChars = new ArrayList<>();
        validChars.add(new HashSet<>(Arrays.asList(Character.valueOf('L'), Character.valueOf('A'))));
        validChars.add(new HashSet<>(Arrays.asList(Character.valueOf('R'))));
        validChars.add(new HashSet<>(Arrays.asList(Character.valueOf('O'))));

        List<List<List<Character>>> grid = new ArrayList<>();
        List<List<Character>> row1 = rowBuilder(Arrays.asList("AAA", "BBB", "CCC"));
        grid.add(row1);
        List<List<Character>> row2 = rowBuilder(Arrays.asList("DDD", "EEE", "FFF"));
        grid.add(row2);
        List<List<Character>> row3 = rowBuilder(Arrays.asList("GGG", "HHH", "III"));
        grid.add(row3);

        RowCrossChecker checker = new RowCrossChecker(grid, dawg);
        List<Set<Character>> crossCheckResults = checker.computeCrossChecks(1);
        assertArrayEquals(validChars.toArray(), crossCheckResults.toArray());
    }

    /**
     * Test for {@link RowCrossChecker.computeCrossChecks(int)}.<P/>
     * Case: The middle row of a 3x3 grid is examined.<P/>
     * Result: The top and middle rows are examined to create 'words'
     *  according to the dawg and the corresponding characters are returned in a list of lists.
     */
    @Test
    public void testComputeCrossChecks_smallGridBottomRow() {
        DAWGSet dawg = new ModifiableDAWGSet();
        dawg.addAll(Arrays.asList("ADM", "BEL", "CFP", "ADP", "FFF", "ASP"));
        List<Set<Character>> validChars = new ArrayList<>();
        validChars.add(new HashSet<>(Arrays.asList(Character.valueOf('M'), Character.valueOf('P'))));
        validChars.add(new HashSet<>(Arrays.asList(Character.valueOf('L'))));
        validChars.add(new HashSet<>(Arrays.asList(Character.valueOf('P'))));

        List<List<List<Character>>> grid = new ArrayList<>();
        List<List<Character>> row1 = rowBuilder(Arrays.asList("AAA", "BBB", "CCC"));
        grid.add(row1);
        List<List<Character>> row2 = rowBuilder(Arrays.asList("DDD", "EEE", "FFF"));
        grid.add(row2);
        List<List<Character>> row3 = rowBuilder(Arrays.asList("GGG", "HHH", "III"));
        grid.add(row3);

        RowCrossChecker checker = new RowCrossChecker(grid, dawg);
        List<Set<Character>> crossCheckResults = checker.computeCrossChecks(2);

        assertArrayEquals(validChars.toArray(), crossCheckResults.toArray());
    }

    /**
     * Test for {@link RowCrossChecker.computeCrossChecks(int)}.<P/>
     * Case: The middle row of a 3x3 grid is examined.<P/>
     * Result: The middle and bottom rows are examined to create 'words'
     *  according to the dawg and the corresponding characters are returned in a list of lists.
     */
    @Test
    public void testComputeCrossChecks_smallGridTopRow() {
        DAWGSet dawg = new ModifiableDAWGSet();
        dawg.addAll(Arrays.asList("KDG", "GEH", "BFI", "ZDG", "FFF", "ASP"));
        List<Set<Character>> validChars = new ArrayList<>();
        validChars.add(new HashSet<>(Arrays.asList(Character.valueOf('K'), Character.valueOf('Z'))));
        validChars.add(new HashSet<>(Arrays.asList(Character.valueOf('G'))));
        validChars.add(new HashSet<>(Arrays.asList(Character.valueOf('B'))));

        List<List<List<Character>>> grid = new ArrayList<>();
        List<List<Character>> row1 = rowBuilder(Arrays.asList("AAA", "BBB", "CCC"));
        grid.add(row1);
        List<List<Character>> row2 = rowBuilder(Arrays.asList("DDD", "EEE", "FFF"));
        grid.add(row2);
        List<List<Character>> row3 = rowBuilder(Arrays.asList("GGG", "HHH", "III"));
        grid.add(row3);

        RowCrossChecker checker = new RowCrossChecker(grid, dawg);
        List<Set<Character>> crossCheckResults = checker.computeCrossChecks(0);

        assertArrayEquals(validChars.toArray(), crossCheckResults.toArray());
    }

    /**
     * Test for {@link RowCrossChecker.computeCrossChecks(int)}.<P/>
     * Case: There are no letters in the grid.<P/>
     * Result: No characters are returned.
     */
    @Test
    public void testComputeCrossChecks_smallGridNoLetters() {
        DAWGSet dawg = new ModifiableDAWGSet();
        dawg.addAll(Arrays.asList("KDG", "GEH", "BFI", "ZDG", "FFF", "ASP"));
        List<Set<Character>> validChars = new ArrayList<>();
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());

        List<List<List<Character>>> grid = new ArrayList<>();
        List<List<Character>> row1 = rowBuilder(Arrays.asList("***", "***", "***"));
        grid.add(row1);
        List<List<Character>> row2 = rowBuilder(Arrays.asList("***", "***", "***"));
        grid.add(row2);
        List<List<Character>> row3 = rowBuilder(Arrays.asList("***", "***", "***"));
        grid.add(row3);

        RowCrossChecker checker = new RowCrossChecker(grid, dawg);
        List<Set<Character>> crossCheckResults = checker.computeCrossChecks(0);

        assertArrayEquals(validChars.toArray(), crossCheckResults.toArray());
    }

    /**
     * Test for {@link RowCrossChecker.computeCrossChecks(int)}.<P/>
     * Case: There are no possible words in any of the columns.<P/>
     * Result: No characters are returned.
     */
    @Test
    public void testComputeCrossChecks_smallGridNoWords() {
        DAWGSet dawg = new ModifiableDAWGSet();
        dawg.addAll(Arrays.asList("ZZZ", "HHH", "BBB", "NNN", "OOO", "VVV"));
        List<Set<Character>> validChars = new ArrayList<>();
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());

        List<List<List<Character>>> grid = new ArrayList<>();
        List<List<Character>> row1 = rowBuilder(Arrays.asList("AAA", "BBB", "CCC"));
        grid.add(row1);
        List<List<Character>> row2 = rowBuilder(Arrays.asList("DDD", "EEE", "FFF"));
        grid.add(row2);
        List<List<Character>> row3 = rowBuilder(Arrays.asList("GGG", "HHH", "III"));
        grid.add(row3);

        RowCrossChecker checker = new RowCrossChecker(grid, dawg);
        List<Set<Character>> crossCheckResults = checker.computeCrossChecks(0);

        assertArrayEquals(validChars.toArray(), crossCheckResults.toArray());
    }

    /**
     * Test for {@link RowCrossChecker.computeCrossChecks(int)}.<P/>
     * Case: There are possible words in a large grid with a full dictionary.<P/>
     * Result: The correct characters are returned according to the dictionary.
     */
    @Test
    public void testComputeCrossChecks_largeGridFullDictionary() {
        DAWGSet dawg = new ModifiableDAWGSet();
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("dictionary.txt");
        Scanner sc = new Scanner(new InputStreamReader(stream));
        while (sc.hasNextLine()) {
            dawg.add(sc.nextLine());
        }

        List<Set<Character>> validChars = new ArrayList<>();
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>(Arrays.asList('A', 'E', 'U', 'O')));
        validChars.add(new HashSet<>(Arrays.asList('L')));
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>(Arrays.asList('R', 'S', 'D', 'T', 'F', 'H', 'X', 'L', 'M', 'N')));
        validChars.add(new HashSet<>());

        List<List<List<Character>>> grid = new ArrayList<>();
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*", "*", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*", "*", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*", "*", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "A", "*", "*", "*", "*", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "P", "*", "*", "*", "*", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "P", "R", "I", "C", "E", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "L", "*", "*", "*", "*", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "E", "T", "L", "*", "*", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*", "*", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*", "*", "*")));

        RowCrossChecker checker = new RowCrossChecker(grid, dawg);
        List<Set<Character>> crossCheckResults = checker.computeCrossChecks(6);

        assertArrayEquals(validChars.toArray(), crossCheckResults.toArray());
    }
    
    /**
     * Test for {@link RowCrossChecker.computeCrossChecks(int)}.<P/>
     * Case: There are no possible words in any of the columns.<P/>
     * Result: No characters are returned.
     */
    @Test
    public void testComputeCrossChecks_largeGridFullDictionaryNoAdjacentTiles() {
        DAWGSet dawg = new ModifiableDAWGSet();
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("dictionary.txt");
        Scanner sc = new Scanner(new InputStreamReader(stream));
        while (sc.hasNextLine()) {
            dawg.add(sc.nextLine());
        }

        List<Set<Character>> validChars = new ArrayList<>();
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());
        validChars.add(new HashSet<>());

        List<List<List<Character>>> grid = new ArrayList<>();
        grid.add(rowBuilder(Arrays.asList("*", "F", "O", "L", "D", "*", "*", "*", "G", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*", "L", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*", "U", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*", "E", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*", "*", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*", "*", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*", "*", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*", "*", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "I", "T", "*", "*", "*")));
        grid.add(rowBuilder(Arrays.asList("*", "*", "*", "*", "*", "*", "*", "*", "*", "*")));

        RowCrossChecker checker = new RowCrossChecker(grid, dawg);
        List<Set<Character>> crossCheckResults = checker.computeCrossChecks(5);

        assertArrayEquals(validChars.toArray(), crossCheckResults.toArray());
    }

    /**
     * Convenience method for building out rows of character columns from lists of strings.
     * @param input List of strings to convert to list of characters.
     * @return List of Lists of characters that represent an upwords grid row.
     */
    private List<List<Character>> rowBuilder(List<String> input) {
        List<List<Character>> row = new ArrayList<>();
        for (String s : input) {
            List<Character> column = Arrays.asList(s.chars().mapToObj(c -> (char)c).toArray(Character[]::new));
            row.add(column);
        }
        return row;
    }
}
