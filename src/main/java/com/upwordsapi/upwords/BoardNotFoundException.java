package com.upwordsapi.upwords;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(Long id) {
        super("Could not find board " + id);
    }
}
