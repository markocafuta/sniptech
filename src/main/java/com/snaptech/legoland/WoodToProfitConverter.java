package com.snaptech.legoland;

public class WoodToProfitConverter implements ProfitConverter {

	@Override
	public int convert (final int value) {
		switch (value) {
			case 1: return -1;
			case 2: return 3;
			case 3: return 1;
			default:
				throw new IllegalArgumentException("Can not convert value: " + value);
		}
	}
}
