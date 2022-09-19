package com.snaptech.legoland;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeTrunkSawmillTest {

	@Test
	void cut () {
		int[] treeTrunks = {5,4,1,2,3};
		Sawmill sawmill = new TreeTrunkSawmill();
		List<Integer> cutted = sawmill.cut(treeTrunks);
		assertEquals(3, cutted.get(0));
		assertEquals(2, cutted.get(1));
		assertEquals(1, cutted.get(2));
		assertEquals(3, cutted.get(3));
		assertEquals(1, cutted.get(4));
		assertEquals(2, cutted.get(5));
		assertEquals(3, cutted.get(6));
	}
}