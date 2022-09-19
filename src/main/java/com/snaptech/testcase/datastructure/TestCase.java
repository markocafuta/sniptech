package com.snaptech.testcase.datastructure;

import lombok.Getter;

public class TestCase {

	private int sawmillIndex;

	@Getter
	private final int ordinal;

	@Getter
	private int[][] treeTrunks;

	public TestCase(final int ordinal, final int sawmillCount) {
		this.ordinal = ordinal;
		treeTrunks = new int[sawmillCount][];
	}

	public void addTreeTrunksPerSawmill (final int[] sawmillTreeTrunks) {
		if (sawmillIndex >= treeTrunks.length)
			return;

		treeTrunks[sawmillIndex] = sawmillTreeTrunks;
		sawmillIndex++;
	}

}
