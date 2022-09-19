package com.snaptech.testcase.result;

import com.snaptech.testcase.datastructure.TestCaseResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class FormatterImpl implements Formatter{

	@Override
	public String format (final TestCaseResult result) {
		Map<Integer, Collection<String>> order = result.getOrder();
		return "Case: " + result.getOrdinal() + System.lineSeparator() +
			"Max profit: " + result.getMaxProfit() + System.lineSeparator() +
			"Order: " + formatOrder(order) + System.lineSeparator();
	}


	private static String formatOrder (final Map<Integer, Collection<String>> order) {
		Collection<String> result = new ArrayList<>();
		for (final Collection<String> orderPerSawmill : order.values())
			result.add(String.join("", orderPerSawmill));

		return String.join(",", result);
	}
}
