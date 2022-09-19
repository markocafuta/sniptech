package com.snaptech.legoland;

import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class SawmillCalculatorImpl implements SawmillCalculator {

	private final ProfitCalculator calculator;
	private final Sawmill sawmill;

	@Override
	public int cutAndCalculateProfit (final int[] treeTrunks) {
		Collection<Integer> cutted = sawmill.cut(treeTrunks);
		return calculator.calculate(cutted);
	}
}
