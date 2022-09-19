package com.snaptech.testcase;

import com.snaptech.testcase.datastructure.TestCase;
import com.snaptech.testcase.datastructure.TestCaseResult;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public class MapperImpl implements Mapper {

	private int ordinal = 1;
	private TestCase testCase;

	private final Processor processor;

	@Override
	public void map (final int[] tokens) {
		if (isSawmillCountToken(tokens))
			createTestCase(tokens[0]);
		else
			updateTestCase(tokens);
	}

	private static boolean isSawmillCountToken (final int[] tokens) {
		return tokens.length == 1;
	}

	private void createTestCase (final int sawmillCount) {
		if (notFirstTestCase())
			ordinal = processTestCaseAndGetNextOrdinal();

		testCase = new TestCase(ordinal, sawmillCount);
	}

	private boolean notFirstTestCase () {
		return testCase != null;
	}

	private int processTestCaseAndGetNextOrdinal () {
		TestCaseResult result = processor.process(testCase);
		return result.getOrdinal() + 1;
	}

	private void updateTestCase (final int[] tokens) {
		int[] treeTrunks = getTreeTrunksPerSawmill(tokens);
		testCase.addTreeTrunksPerSawmill(treeTrunks);
	}

	private static int[] getTreeTrunksPerSawmill (final int[] tokens) {
		return Arrays.copyOfRange(tokens, 1, tokens.length);
	}

}
