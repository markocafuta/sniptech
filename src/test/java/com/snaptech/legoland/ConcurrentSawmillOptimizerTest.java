package com.snaptech.legoland;

import com.snaptech.infrastructure.CachedThreadPoolExecutor;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConcurrentSawmillOptimizerTest {

	private final TreeTrunkCombinator combinator = new AllCombinator();
	private final ProfitCalculator calculator = new WoodcutterProfitCalculator(new WoodToProfitConverter());
	private final Sawmill sawmill = new TreeTrunkSawmill();
	private final SawmillCalculator sawmillCalculator = new SawmillCalculatorImpl(calculator, sawmill);
	private final ExecutorService executor = new CachedThreadPoolExecutor().get();
	private final SawmillOptimizer optimizer = new ConcurrentSawmillOptimizer(combinator, sawmillCalculator, executor);

	@Test
	void optimize1 () {
		SawmillOutput output = optimizer.optimize(new int[]{2,1,3});
		assertEquals(4, output.getProfit());
		Collection<String> order = output.getOrder();
		assertEquals(2, order.size());
		assertTrue(order.contains("[2 3 1]"));
		assertTrue(order.contains("[1 3 2]"));
	}

	@Test
	void optimize2 () {
		SawmillOutput output = optimizer.optimize(new int[]{1,1,2});
		assertEquals(1, output.getProfit());
		Collection<String> order = output.getOrder();
		assertEquals(2, order.size());
		assertTrue(order.contains("[1 2 1]"));
		assertTrue(order.contains("[2 1 1]"));
	}

	@Test
	void optimize3 () {
		SawmillOutput output  = optimizer.optimize(new int[]{1,2});
		assertEquals(2, output.getProfit());
		Collection<String> order = output.getOrder();
		assertEquals(2, order.size());
		assertTrue(order.contains("[1 2]"));
		assertTrue(order.contains("[2 1]"));
	}

	@Test
	void optimize4 () {
		SawmillOutput output  = optimizer.optimize(new int[] {1,4});
		assertEquals(5, output.getProfit());
		Collection<String> order = output.getOrder();
		assertEquals(1, order.size());
		assertTrue(order.contains("[1 4]"));
	}

	@Test
	void optimize5 () {
		SawmillOutput output  = optimizer.optimize(new int[] {1});
		assertEquals(-1, output.getProfit());
		Collection<String> order = output.getOrder();
		assertEquals(1, order.size());
		assertTrue(order.contains("[1]"));
	}
}