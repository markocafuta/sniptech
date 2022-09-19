package com.snaptech.legoland;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

@RequiredArgsConstructor
public class ConcurrentSawmillOptimizer implements SawmillOptimizer {

	private final TreeTrunkCombinator combinator;
	private final SawmillCalculator calculator;
	private final ExecutorService executor;

	@Override
	public SawmillOutput optimize (final int[] treeTrunks) {
		SawmillOutput sawmillOutput = new ConcurrentSawmillOutput();
		List<int[]> combinations = combinator.combine(treeTrunks);
		optimize(sawmillOutput, combinations);
		return sawmillOutput;
	}

	@SneakyThrows
	private void optimize (final SawmillOutput sawmillOutput, final Collection<int[]> combinations) {
		CountDownLatch latch = new CountDownLatch(combinations.size());
		combinations.forEach(combination -> executeTask(combination, sawmillOutput, latch));
		latch.await();
	}

	private void executeTask (
		final int[] combination,
		final SawmillOutput sawmillOutput,
		final CountDownLatch latch
	) {
		executor.submit(() -> cutAndCalculateProfit(combination, sawmillOutput, latch));
	}

	private void cutAndCalculateProfit(final int[] treeTrunks, final SawmillOutput sawmillOutput, final CountDownLatch latch) {
		int profit = calculator.cutAndCalculateProfit(treeTrunks);
		sawmillOutput.setOrder(profit, Arrays.toString(treeTrunks).replace(", ", " "));
		latch.countDown();
	}
}
