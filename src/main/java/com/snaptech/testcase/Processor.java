package com.snaptech.testcase;

import com.snaptech.testcase.datastructure.TestCase;
import com.snaptech.testcase.datastructure.TestCaseResult;

public interface Processor {

	TestCaseResult process(TestCase testCase);
}
