package com.upwordsapi.upwords.board;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class BoardState {
    List<List<List<Character>>> grid;

    List<Character> hand;
}
