package com.snaptech.testcase.datastructure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class TestCaseResult {
	private final int ordinal;
	private final int maxProfit;
	private final Map<Integer, Collection<String>> order = new HashMap<>();
}
