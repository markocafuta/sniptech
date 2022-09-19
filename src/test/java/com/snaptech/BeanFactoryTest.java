package com.snaptech;

import com.snaptech.infrastructure.BeanFactory;
import com.snaptech.input.Parser;
import com.snaptech.input.Reader;
import com.snaptech.testcase.Mapper;
import com.snaptech.testcase.Processor;
import com.snaptech.testcase.Validator;
import com.snaptech.testcase.result.Formatter;
import com.snaptech.testcase.result.Writer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BeanFactoryTest {


	@Test
	void returnNull_ifBeanDoesNotExist() {
		Reader reader = BeanFactory.instance.getBean(Reader.class);
		assertNull(reader);
	}

	@Test
	void getBean () {
		Mapper mapper = BeanFactory.instance.getBean(Mapper.class);
		assertNotNull(mapper);

		Parser parser = BeanFactory.instance.getBean(Parser.class);
		assertNotNull(parser);

		Processor processor = BeanFactory.instance.getBean(Processor.class);
		assertNotNull(processor);

		Validator validator = BeanFactory.instance.getBean(Validator.class);
		assertNotNull(validator);

		Formatter formatter = BeanFactory.instance.getBean(Formatter.class);
		assertNotNull(formatter);

		Writer writer = BeanFactory.instance.getBean(Writer.class);
		assertNotNull(writer);
	}
}