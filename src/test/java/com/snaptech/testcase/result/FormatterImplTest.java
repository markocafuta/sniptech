package com.snaptech.testcase.result;

import com.snaptech.testcase.datastructure.TestCaseResult;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatterImplTest {

	@Test
	void format () {
		TestCaseResult tcResult = new TestCaseResult(1);
		Map<Integer, Collection<String>> order = tcResult.getOrder();

		Collection<String> order1 = new LinkedHashSet<>();
		order1.add("[1,2,3]");
		order1.add("[1,2,3]");
		order1.add("[3,2,1]");
		order1.add("[2,1,3]");
		order1.add("[2,3,1]");
		order.put(0, order1);

		Collection<String> order2 = new LinkedHashSet<>();
		order2.add("[4,5,6]");
		order2.add("[6,4,5]");
		order2.add("[4,6,5]");
		order2.add("[5,4,5]");
		order.put(1, order2);

		Collection<String> order3 = new LinkedHashSet<>();
		order3.add("[7,8]");
		order3.add("[8,7]");
		order.put(2, order3);

		String expected = "Case: 1" + System.lineSeparator() +
			"Max profit: 0"  + System.lineSeparator() +
			"Order: [1,2,3][3,2,1][2,1,3][2,3,1],[4,5,6][6,4,5][4,6,5][5,4,5],[7,8][8,7]" + System.lineSeparator();

		Formatter formatter = new FormatterImpl();
		String result = formatter.format(tcResult);
		assertEquals(expected, result);
	}
}