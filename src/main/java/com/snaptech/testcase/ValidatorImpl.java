package com.snaptech.testcase;

import com.snaptech.testcase.datastructure.TestCase;

public class ValidatorImpl implements Validator {

	public void validate (final TestCase testCase) {
		if (testCase == null)
			return;

		int[][] treeTrunks = testCase.getTreeTrunks();
		for (final int[] river : treeTrunks)
			validate(river);
	}

	private static void validate (final int[] sawmillTreeTrunks) {
		if (isNullOrEmpty(sawmillTreeTrunks))
			throwIllegalArgumentException();

		for (final int treeTrunk : sawmillTreeTrunks)
			validate(treeTrunk);
	}

	private static boolean isNullOrEmpty (final int[] val) {
		return val == null || val.length <= 0;
	}

	private static void throwIllegalArgumentException () {
		throw new IllegalArgumentException("Test case not valid!");
	}

	private static void validate (final int treeTrunk) {
		if (treeTrunk <= 0)
			throwIllegalArgumentException();
	}
}
