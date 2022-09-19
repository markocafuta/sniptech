package com.snaptech.legoland;

import java.util.Collection;

public interface SawmillOutput {
	int getSawmillIndex();
	void setSawmillIndex(int sawmillIndex);
	Collection<String> getOrder();
	void setOrder (final int profit, final String item);
	int getProfit ();
}
