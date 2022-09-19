package com.snaptech.legoland;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TreeTrunkSawmill implements Sawmill {

	@Override
	public List<Integer> cut (final int[] treeTrunks) {
		List<Integer> cutted = new ArrayList<>();
		int remainder = 0;
		for (final int treeTrunk : treeTrunks)
			remainder = cut(treeTrunk, remainder, cutted);

		return cutted;
	}

	private static int cut (final int treeTrunk, final int remainder, final Collection<Integer> cutted) {
		int blocks = treeTrunk;
		int newReminder = remainder;

		if (newReminder > 0 && blocks + newReminder > 3) {
			int firstCut = 3 - remainder;
			cutted.add(firstCut);
			blocks = treeTrunk - firstCut;
			newReminder = 0;
		}

		int ratio = blocks / 3;
		int modulo = blocks % 3;

		for(int i = 0; i < ratio; i++)
			cutted.add(3);

		if (modulo > 0)
			cutted.add(modulo);

		return (modulo + newReminder) % 3;
	}
}
