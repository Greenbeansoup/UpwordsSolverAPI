package com.upwordsapi.upwords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upwordsapi.upwords.board.BoardState;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UpwordsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void getJsonRep() throws JsonProcessingException {
		List<List<List<Character>>> grid = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			List<List<Character>> row = new ArrayList<>();
			grid.add(row);
			for (int j = 0; j < 3; j++) {
				List<Character> column = new ArrayList<>();
				row.add(column);
				for (int k = 0; k < 3; k++) {
					column.add('A');
				}
			}
		}

		BoardState boardState = new BoardState(grid, Arrays.asList(new Character[]{'C','D','F'}));

		ObjectMapper mapper = new ObjectMapper();
		String jsonState = mapper.writer().writeValueAsString(boardState);
		String jsonData = mapper.writer().writeValueAsString(grid);
		JsonNode node = mapper.readTree(jsonData);
		List<List<List<Character>>> schmee = mapper.readValue(jsonData, List.class);
		String schlee = "idk man";
	}
}
