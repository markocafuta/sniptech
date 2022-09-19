package com.snaptech.legoland;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
@RequiredArgsConstructor
public class WoodcutterProfitCalculator implements ProfitCalculator {

	private final ProfitConverter converter;

	@Override
	public int calculate (final Collection<Integer> cuttedTreeTrunks) {
		return cuttedTreeTrunks.stream()
			.map(converter::convert)
			.mapToInt(Integer::valueOf)
			.sum();
	}
}
