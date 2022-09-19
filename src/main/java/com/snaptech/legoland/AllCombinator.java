package com.snaptech.legoland;

import java.util.ArrayList;
import java.util.List;

public class AllCombinator implements TreeTrunkCombinator {

	@Override
	public List<int[]> combine (final int[] treeTrunks) {
		List<int[]> combinations = new ArrayList<>();
		combine(treeTrunks, 0, treeTrunks.length, combinations);
		return combinations;
	}

	private static void combine (final int[] treeTrunks, final int index, final int length, final List<int[]> combinations) {
		if (index == length)
			combinations.add(treeTrunks.clone());

		for (int i = index; i < length; i++) {
			int temp = treeTrunks[index];
			treeTrunks[index] = treeTrunks[i];
			treeTrunks[i] = temp;
			combine(treeTrunks, index + 1, length, combinations);

			temp = treeTrunks[index];
			treeTrunks[index] = treeTrunks[i];
			treeTrunks[i] = temp;
		}
	}
}
