package com.snaptech.legoland;

import java.util.ArrayList;
import java.util.List;

public class TreeTrunkSawmill implements Sawmill {

	@Override
	public List<Integer> cut (final int[] treeTrunks) {
		List<Integer> cutted = new ArrayList<>();
		int remainder = 0;
		for (int i = 0; i < treeTrunks.length; i++)
			remainder = cut(treeTrunks[i], remainder, cutted);

		return cutted;
	}

	private int cut(final int treeTrunk, final int remainder, final List<Integer> cutted) {
		if (isLong(treeTrunk))
			return cutLong(treeTrunk, remainder, cutted);
		else
			return cutShort(treeTrunk, remainder, cutted);
	}

	private boolean isLong (final int treeTrunk) {
		return treeTrunk > 3;
	}

	private int cutShort (final int treeTrunk, final int remainder, final List<Integer> cutted) {
		cutted.add(treeTrunk);
		return  (treeTrunk + remainder) % 3;
	}

	private int cutLong (final int treeTrunk, final int remainder, final List<Integer> cutted) {
		int blocks = treeTrunk;
		if (remainder > 0) {
			int firstBlock = 3 - remainder;
			cutted.add(firstBlock);
			blocks = treeTrunk - firstBlock;
		}

		int ratio = blocks / 3;
		int modulo = blocks % 3;

		for(int i = 0; i < ratio; i++)
			cutted.add(3);

		if (modulo > 0)
			cutted.add(modulo);

		return modulo;
	}
}
