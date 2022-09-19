package com.snaptech.legoland;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllCombinatorTest {

	@Test
	void combine () {

		TreeTrunkCombinator combinator = new AllCombinator();

		List<int[]> combinations = combinator.combine(new int[]{1});
		assertEquals(1, combinations.size());
		assertCombination(combinations, new int[]{1} ,true);

		combinations = combinator.combine(new int[]{1,2});
		assertEquals(2, combinations.size());
		assertCombination(combinations, new int[]{1,2} ,true);
		assertCombination(combinations, new int[]{2,1} ,true);

		combinations = combinator.combine(new int[]{1,2,3});
		assertEquals(6, combinations.size());
		assertCombination(combinations, new int[]{1,2,3} ,true);
		assertCombination(combinations, new int[]{1,3,2} ,true);
		assertCombination(combinations, new int[]{2,1,3} ,true);
		assertCombination(combinations, new int[]{2,3,1} ,true);
		assertCombination(combinations, new int[]{3,1,2} ,true);
		assertCombination(combinations, new int[]{3,2,1} ,true);
	}

	static void assertCombination (final Iterable<int[]> combinations, final int[] combination, final boolean shouldHit) {
		int hitCount = 0;
		for (int[] comb : combinations)
			if(Arrays.equals(comb, combination))
				hitCount++;

		int expectedHitCount = shouldHit ? 1 : 0;
		assertEquals(expectedHitCount, hitCount);
	}
}