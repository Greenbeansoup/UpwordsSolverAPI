package com.upwordsapi.upwords;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.upwordsapi.upwords.board.BoardState;
import com.upwordsapi.upwords.solve.BoardSolver;
import com.upwordsapi.upwords.solve.BoardSolverFactory;
import com.upwordsapi.upwords.solve.IBoardSolver;

import org.quinto.dawg.ModifiableDAWGSet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BoardStateController {
    private final ModifiableDAWGSet dawg;
    private final IBoardSolver solver;

    public BoardStateController() {
        dawg = new ModifiableDAWGSet();
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("dictionary.txt");
        Scanner sc = new Scanner(new InputStreamReader(stream));
        while (sc.hasNextLine()) {
            dawg.add(sc.nextLine());
        }
        solver = BoardSolverFactory.getDefaultSolver();
    }

    @CrossOrigin
    @PostMapping("/upwords/solve")
    ResponseEntity<BoardState> solve(@RequestBody BoardState boardState) {
        return ResponseEntity.ok().body(solver.solve(boardState, dawg));
    }
}
