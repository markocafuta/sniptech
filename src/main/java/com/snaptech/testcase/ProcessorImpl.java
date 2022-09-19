package com.snaptech.testcase;

import com.snaptech.testcase.datastructure.TestCase;
import com.snaptech.testcase.datastructure.TestCaseResult;
import com.snaptech.testcase.result.Writer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProcessorImpl implements Processor {

	private final Validator validator;
	private final Writer printer;

	public TestCaseResult process(final TestCase testCase) {
		validator.validate(testCase);
		return processTestCase(testCase);
	}

	private TestCaseResult processTestCase(final TestCase testCase) {
		int[][] trunks = testCase.getTreeTrunks();
		println("Ordinal: " + testCase.getOrdinal());
		println("Sawmill count: " + trunks.length);
		for (int i = 0; i < trunks.length; i++) {
			println("");
			for (int j = 0; j < trunks[i].length; j++)
				System.out.print(trunks[i][j] + " ");
		}
		println("");
		TestCaseResult result = new TestCaseResult(testCase.getOrdinal(), 5);
		printer.write(result);
		return result;
	}

	private void println (String s) {
		System.out.println(s);
	}

}
