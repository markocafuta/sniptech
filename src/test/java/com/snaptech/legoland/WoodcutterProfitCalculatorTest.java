package com.snaptech.legoland;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WoodcutterProfitCalculatorTest {

	@Test
	void calculate () {
		WoodcutterProfitCalculator calc = new WoodcutterProfitCalculator(new WoodToProfitConverter());

		List<Integer> cuttedTreeTrunks = Arrays.asList(2, 3, 1, 1, 3, 2 , 1, 3);
		int profit = calc.calculate(cuttedTreeTrunks);
		assertEquals(6, profit);
	}
}