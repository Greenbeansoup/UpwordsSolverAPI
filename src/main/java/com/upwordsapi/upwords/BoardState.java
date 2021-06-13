package com.upwordsapi.upwords;

import java.util.List;

import javax.persistence.ElementCollection;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class BoardState {
    List<List<List<Character>>> grid;

    @ElementCollection
    List<Character> hand;
}
