package com.snaptech.infrastructure;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExecutor extends BaseThreadPoolExecutor {

	private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

	@Override
	public ExecutorService get () {
		return EXECUTOR;
	}
}
