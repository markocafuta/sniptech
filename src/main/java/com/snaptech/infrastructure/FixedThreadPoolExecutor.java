package com.snaptech.infrastructure;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExecutor extends BaseThreadPoolExecutor {

	private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	@Override
	public ExecutorService get () {
		return EXECUTOR;
	}
}
