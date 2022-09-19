package com.snaptech.testcase.result;

import com.snaptech.output.Printer;
import com.snaptech.testcase.datastructure.TestCaseResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WriterImpl implements Writer {

	private final Formatter formatter;
	private final Printer printer;

	@Override
	public void write (final TestCaseResult result) {
		final String resultText = formatter.format(result);
		printer.print(resultText);
	}
}
