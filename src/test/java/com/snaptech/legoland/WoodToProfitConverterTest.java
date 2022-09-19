package com.snaptech.legoland;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WoodToProfitConverterTest {

	private WoodToProfitConverter converter;

	@BeforeEach
	void init() {
		converter = new WoodToProfitConverter();
	}

	@Test
	void throwError_whenValueIsNotConvertable () {
		assertThrows(IllegalArgumentException.class, () -> converter.convert(0));
	}

	@Test
	void convert () {
		assertEquals(-1, converter.convert(1));
		assertEquals(3, converter.convert(2));
		assertEquals(1, converter.convert(3));
	}
}