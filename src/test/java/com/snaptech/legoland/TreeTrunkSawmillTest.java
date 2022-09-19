package com.snaptech.legoland;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeTrunkSawmillTest {

	private final Sawmill sawmill = new TreeTrunkSawmill();
	@Test
	void cut1 () {
		int[] treeTrunks = {5,4,1,2,3};
		List<Integer> cutted = sawmill.cut(treeTrunks);
		assertEquals(3, cutted.get(0));
		assertEquals(2, cutted.get(1));
		assertEquals(1, cutted.get(2));
		assertEquals(3, cutted.get(3));
		assertEquals(1, cutted.get(4));
		assertEquals(2, cutted.get(5));
		assertEquals(3, cutted.get(6));
	}

	@Test
	void cut2 () {
		int[] treeTrunks =  {1, 3, 2};
		List<Integer> cutted = sawmill.cut(treeTrunks);
		assertEquals(1, cutted.get(0));
		assertEquals(2, cutted.get(1));
		assertEquals(1, cutted.get(2));
		assertEquals(2, cutted.get(3));
	}

	@Test
	void cut3 () {
		int[] treeTrunks = {1,1,2};
		List<Integer> cutted = sawmill.cut(treeTrunks);
		assertEquals(1, cutted.get(0));
		assertEquals(1, cutted.get(1));
		assertEquals(1, cutted.get(2));
		assertEquals(1, cutted.get(3));
	}
}