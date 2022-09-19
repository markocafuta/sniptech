package com.snaptech.output;

public class SystemOutPrinter implements Printer {

	@Override
	public void println (final String line) {
		System.out.println(line);
	}

	@Override
	public void print (final String text) {
		System.out.print(text);
	}
}
