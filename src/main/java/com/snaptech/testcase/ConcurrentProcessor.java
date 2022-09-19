package com.snaptech.testcase;

import com.snaptech.legoland.SawmillOptimizer;
import com.snaptech.legoland.SawmillOutput;
import com.snaptech.testcase.datastructure.TestCase;
import com.snaptech.testcase.datastructure.TestCaseResult;
import com.snaptech.testcase.result.Writer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RequiredArgsConstructor
public class ConcurrentProcessor implements Processor {

	private final Validator validator;
	private final Writer writer;
	private final SawmillOptimizer optimizer;
	private final ExecutorService executor;


	public TestCaseResult process(final TestCase testCase) {
		validator.validate(testCase);
		return processTestCase(testCase);
	}

	@SneakyThrows
	private TestCaseResult processTestCase(final TestCase testCase) {
		TestCaseResult result = executeTasks(testCase);
		writer.write(result);
		return result;
	}

	@SneakyThrows
	private TestCaseResult executeTasks (final TestCase testCase) {
		TestCaseResult result = new TestCaseResult(testCase.getOrdinal());
		executor
			.invokeAll(getCallables(testCase.getTreeTrunks()))
			.stream()
			.map(ConcurrentProcessor::getFeatureResult)
			.forEach(result::addSowmillOutput);

		return result;
	}

	@SneakyThrows
	private static SawmillOutput getFeatureResult (final Future<SawmillOutput> future) {
		return future.get();
	}


	private Collection<Callable<SawmillOutput>> getCallables(final int[][] treeTrunks) {
		final Collection<Callable<SawmillOutput>> callables = new ArrayList<>();
		for (int i = 0; i < treeTrunks.length; i++)
			callables.add(new SawmillCallable(i, treeTrunks[i]));

		return callables;
	}

	private final class SawmillCallable implements Callable<SawmillOutput> {

		private final int sawmillIndex;
		private final int[] treeTrunks;

		private SawmillCallable(final int sawmillIndex, final int[] treeTrunks) {
			this.sawmillIndex = sawmillIndex;
			this.treeTrunks = treeTrunks;
		}

		@Override
		public SawmillOutput call () {
			SawmillOutput output = optimizer.optimize(treeTrunks);
			output.setSawmillIndex(sawmillIndex);
			return output;
		}
	}
}
