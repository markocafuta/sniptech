package com.snaptech.legoland;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WoodcutterProfitCalculatorTest {

	@Test
	void calculate () {
		ProfitCalculator calc = new WoodcutterProfitCalculator(new WoodToProfitConverter());

		List<Integer> cuttedTreeTrunks = Arrays.asList(2, 3, 1, 1, 3, 2 , 1, 3);
		int profit = calc.calculate(cuttedTreeTrunks);
		assertEquals(6, profit);

		cuttedTreeTrunks = Arrays.asList(1,2,1,2);
		profit = calc.calculate(cuttedTreeTrunks);
		assertEquals(4, profit);

		cuttedTreeTrunks = Arrays.asList(2,2,1,1);
		profit = calc.calculate(cuttedTreeTrunks);
		assertEquals(4, profit);

		cuttedTreeTrunks = Arrays.asList(1,1,1,1);
		profit = calc.calculate(cuttedTreeTrunks);
		assertEquals(-4, profit);
	}
}