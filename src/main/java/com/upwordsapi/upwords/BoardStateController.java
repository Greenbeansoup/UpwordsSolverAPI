package com.upwordsapi.upwords;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BoardStateController {
    private final BoardStateRepository repository;
    private final BoardStateAssembler assembler;

    @GetMapping("/boards/{id}")
    EntityModel<BoardState> one(@PathVariable Long id) {
        BoardState boardState = repository.findById(id).orElseThrow(() -> new BoardNotFoundException(id));

        return assembler.toModel(boardState);
    }
}
