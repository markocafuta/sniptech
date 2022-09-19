package com.snaptech.testcase;

import com.snaptech.infrastructure.CachedThreadPoolExecutor;
import com.snaptech.legoland.AllCombinator;
import com.snaptech.legoland.ConcurrentSawmillOptimizer;
import com.snaptech.legoland.ProfitCalculator;
import com.snaptech.legoland.Sawmill;
import com.snaptech.legoland.SawmillCalculator;
import com.snaptech.legoland.SawmillCalculatorImpl;
import com.snaptech.legoland.TreeTrunkCombinator;
import com.snaptech.legoland.TreeTrunkSawmill;
import com.snaptech.legoland.WoodToProfitConverter;
import com.snaptech.legoland.WoodcutterProfitCalculator;
import com.snaptech.testcase.datastructure.TestCase;
import com.snaptech.testcase.datastructure.TestCaseResult;
import com.snaptech.testcase.result.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ProcessorImplTest {

	@Mock private Writer writer;
	private final Validator validator = new ValidatorImpl();

	private final TreeTrunkCombinator combinator = new AllCombinator();
	private final ProfitCalculator calculator = new WoodcutterProfitCalculator(new WoodToProfitConverter());
	private final Sawmill sawmill = new TreeTrunkSawmill();
	private final SawmillCalculator sawmillCalculator = new SawmillCalculatorImpl(calculator, sawmill);
	private final ExecutorService executor = new CachedThreadPoolExecutor().get();
	private final ConcurrentSawmillOptimizer optimizer = new ConcurrentSawmillOptimizer(combinator, sawmillCalculator, executor);
	private Processor processor;

	@BeforeEach
	void init() {
		processor = new ConcurrentProcessor(validator, writer, optimizer, executor);
	}

	@Test
	void process () {
		TestCase testCase = new TestCase(2, 3);
		testCase.addTreeTrunksPerSawmill(new int[] {1,2,1});
		testCase.addTreeTrunksPerSawmill(new int[] {1,2});
		testCase.addTreeTrunksPerSawmill(new int[] {1,4});

		TestCaseResult result = processor.process(testCase);
		assertEquals(2, result.getOrdinal());
		assertEquals(8, result.getMaxProfit());

		Map<Integer, Collection<String>> order = result.getOrder();
		assertEquals(3, order.size());

		assertEquals(2, order.get(0).size());
		assertTrue(order.get(0).contains("[1 2 1]"));
		assertTrue(order.get(0).contains("[2 1 1]"));

		assertEquals(2, order.get(1).size());
		assertTrue(order.get(1).contains("[1 2]"));
		assertTrue(order.get(1).contains("[2 1]"));

		assertEquals(1, order.get(2).size());
		assertTrue(order.get(2).contains("[1 4]"));
	}
}