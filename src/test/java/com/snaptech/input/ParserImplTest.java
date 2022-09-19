package com.snaptech.input;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserImplTest {

	private final Parser parser = new ParserImpl();

	@Test
	void throwError_whenInputContainsNonIntChar () {
		final String input1 = "1 2 3 a";
		assertThrows(IllegalArgumentException.class, () -> parser.parse(input1));

		final String input2 = "$";
		assertThrows(IllegalArgumentException.class, () -> parser.parse(input2));

		final String input3 = "( 1 2 3";
		assertThrows(IllegalArgumentException.class, () -> parser.parse(input3));
	}

	@Test
	void throwError_whenInputContainsOnlySpace () {
		final String input1 = "  ";
		assertThrows(IllegalArgumentException.class, () -> parser.parse(input1));
	}

	@Test
	void throwError_whenInputContainsZero () {
		final String input1 = "0";
		assertThrows(IllegalArgumentException.class, () -> parser.parse(input1));

		final String input2 = "1 0";
		assertThrows(IllegalArgumentException.class, () -> parser.parse(input2));
	}

	@Test
	void throwError_whenInvalidTreeTrunkCount () {
		final String input = "2 1";
		assertThrows(IllegalArgumentException.class, () -> parser.parse(input));
	}

	@Test
	void parse_whenInputContainsOnlyOneInt() {
		final String input = "2";

		int[] result = parser.parse(input);
		assertEquals(1, result.length);
		assertEquals(2, result[0]);
	}


	@Test
	void parse_whenInputContainsMoreThenOneInt() {
		final String input = "3 2   3     6";

		int[] result = parser.parse(input);
		assertEquals(4, result.length);
		assertEquals(3, result[0]);
		assertEquals(2, result[1]);
		assertEquals(3, result[2]);
		assertEquals(6, result[3]);
	}
}