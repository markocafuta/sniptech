package com.snaptech.legoland;

import org.junit.jupiter.api.Test;

import java.util.List;

class AllCombinatorTest {

	@Test
	void combine () {

		TreeTrunkCombinator combinator = new AllCombinator();

		List<int[]> combinations = combinator.combine(new int[]{1,2,3,4});
		combinations.forEach(x -> {
			for (final int j : x)
				System.out.print(j + " ");
			System.out.print(System.lineSeparator());
		});
	}
}