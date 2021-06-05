package com.upwordsapi.upwords;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class BoardStateAssembler implements RepresentationModelAssembler<BoardState, EntityModel<BoardState>>{
    @Override
    public EntityModel<BoardState> toModel(BoardState boardState) {
        return EntityModel.of(boardState, linkTo(methodOn(BoardStateController.class).one(boardState.getId())).withSelfRel());
    }
}
