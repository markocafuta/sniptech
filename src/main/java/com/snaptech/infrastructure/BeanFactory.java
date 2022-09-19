package com.snaptech.infrastructure;

import com.snaptech.input.Parser;
import com.snaptech.input.ParserImpl;
import com.snaptech.legoland.AllCombinator;
import com.snaptech.legoland.ConcurrentSawmillOptimizer;
import com.snaptech.legoland.ProfitCalculator;
import com.snaptech.legoland.ProfitConverter;
import com.snaptech.legoland.Sawmill;
import com.snaptech.legoland.SawmillCalculator;
import com.snaptech.legoland.SawmillCalculatorImpl;
import com.snaptech.legoland.SawmillOptimizer;
import com.snaptech.legoland.TreeTrunkCombinator;
import com.snaptech.legoland.TreeTrunkSawmill;
import com.snaptech.legoland.WoodToProfitConverter;
import com.snaptech.legoland.WoodcutterProfitCalculator;
import com.snaptech.output.Printer;
import com.snaptech.output.SystemOutPrinter;
import com.snaptech.testcase.ConcurrentProcessor;
import com.snaptech.testcase.Mapper;
import com.snaptech.testcase.MapperImpl;
import com.snaptech.testcase.Processor;
import com.snaptech.testcase.Validator;
import com.snaptech.testcase.ValidatorImpl;
import com.snaptech.testcase.result.Formatter;
import com.snaptech.testcase.result.FormatterImpl;
import com.snaptech.testcase.result.Writer;
import com.snaptech.testcase.result.WriterImpl;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {

	public static BeanFactory instance = new BeanFactory();

	private final Map<Class<?>, Object> beans = new HashMap<>();

	private BeanFactory () {
		beans.put(ThreadPoolExecutor.class, new CachedThreadPoolExecutor());
		beans.put(Sawmill.class, new TreeTrunkSawmill());
		beans.put(ProfitConverter.class, new WoodToProfitConverter());
		beans.put(Printer.class, new SystemOutPrinter());
		beans.put(Parser.class, new ParserImpl());
		beans.put(Validator.class, new ValidatorImpl());
		beans.put(Formatter.class, new FormatterImpl());
		beans.put(TreeTrunkCombinator.class, new AllCombinator());
		beans.put(ProfitCalculator.class, new WoodcutterProfitCalculator(getBean(ProfitConverter.class)));
		beans.put(SawmillCalculator.class, new SawmillCalculatorImpl(getBean(ProfitCalculator.class), getBean(Sawmill.class)));
		beans.put(SawmillOptimizer.class, new ConcurrentSawmillOptimizer(
			getBean(TreeTrunkCombinator.class),
			getBean(SawmillCalculator.class),
			getBean(ThreadPoolExecutor.class).get())
		);
		beans.put(Writer.class, new WriterImpl(getBean(Formatter.class), getBean(Printer.class)));
		beans.put(Processor.class, new ConcurrentProcessor(
			getBean(Validator.class),
			getBean(Writer.class),
			getBean(SawmillOptimizer.class),
			getBean(ThreadPoolExecutor.class).get())
		);
		beans.put(Mapper.class, new MapperImpl(getBean(Processor.class)));
	}

	public <T> T getBean(final Class<T> beanType) {
		return (T) beans.get(beanType);
	}
}
