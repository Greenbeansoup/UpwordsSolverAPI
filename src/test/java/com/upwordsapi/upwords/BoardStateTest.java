package com.upwordsapi.upwords;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class BoardStateTest {
    
    @Test
    void testEqualsAndHash() {

        BoardState bs1 = new BoardState(new ArrayList<>(), new ArrayList<>());
        BoardState bs2 = new BoardState(new ArrayList<>(), new ArrayList<>());

        assertTrue(bs1.equals(bs2));
        assertTrue(bs1.hashCode() == bs2.hashCode());
    }
}
