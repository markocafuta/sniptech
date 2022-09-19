package com.snaptech.infrastructure;

abstract class BaseThreadPoolExecutor implements ThreadPoolExecutor {

	BaseThreadPoolExecutor () {
		registerShutdownHook();
	}

	private void registerShutdownHook () {
		Thread shutdownHook = new Thread(() -> get().shutdown());
		Runtime.getRuntime().addShutdownHook(shutdownHook);
	}
}
