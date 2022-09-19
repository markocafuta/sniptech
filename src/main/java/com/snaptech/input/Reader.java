package com.snaptech.input;

import com.snaptech.BeanFactory;
import com.snaptech.output.Printer;
import com.snaptech.testcase.Mapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class Reader {

	private static final String EXIT = "0";
	private static final String INVALID_INPUT = "Invalid input! Try again.";


	private final Parser parser;
	private final Mapper mapper;
	private final Printer printer;


	public Reader() {
		parser = BeanFactory.instance.getBean(Parser.class);
		mapper = BeanFactory.instance.getBean(Mapper.class);
		printer = BeanFactory.instance.getBean(Printer.class);
	}

	public void read () {
		try (Scanner scanner = new Scanner(System.in)) {
			read(scanner);
		} catch (final Exception e) {
			log.error("Error while reading from input!", e);
		}
	}

	private void read (final Scanner scanner) {
		String line = "";
		while (notExit(line))
			line = nextLine(scanner);
	}

	private static boolean notExit (final String inputLine) {
		return !shouldExit(inputLine);
	}

	private static boolean shouldExit (final String inputLine) {
		return EXIT.equals(inputLine);
	}

	private String nextLine (final Scanner scanner) {
		String line = scanner.nextLine().trim();
		if (shouldParse(line))
			parse(line);

		return line;
	}

	private static boolean shouldParse (final String line) {
		return notEmpty(line) && notExit(line);
	}

	private static boolean notEmpty (final String input) {
		return !input
			.isEmpty();
	}

	private void parse (final String line) {
		try {
			parseAndMap(line);
		} catch (final IllegalArgumentException iae) {
			println(iae.getMessage());
			println(INVALID_INPUT);
		} catch (final Exception e) {
			log.error("Error while reading from input!", e);
		}
	}

	private void parseAndMap (final String line) {
			int[] tokens = parser.parse(line);
			mapper.map(tokens);
	}

	private void println(final String msg) {
		printer.println(msg);
	}
}