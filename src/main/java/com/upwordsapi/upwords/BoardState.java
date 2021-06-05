package com.upwordsapi.upwords;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "BoardStates")
@EqualsAndHashCode // idk if this is needed
public class BoardState {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private Character[][][] grid;
}
