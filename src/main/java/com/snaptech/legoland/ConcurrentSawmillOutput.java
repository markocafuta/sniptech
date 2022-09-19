package com.snaptech.legoland;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;


public class ConcurrentSawmillOutput implements SawmillOutput {
	@Setter
	@Getter
	private int sawmillIndex;

	private Integer maxProfit;
	private final ConcurrentHashMap<Integer, Collection<String>> orderMap = new ConcurrentHashMap<>();


	public int getProfit () {
		if (maxProfit == null)
			maxProfit = orderMap.keySet().stream().mapToInt(x->x).max().getAsInt();
		return maxProfit;
	}

	public Collection<String> getOrder () {
		return orderMap.get(getProfit());
	}

	public void setOrder (final int profit, final String item) {
		orderMap.compute(profit, (p, order) -> (order == null) ? addOrderItem(new HashSet<>(), item) : addOrderItem(order, item));
	}

	private static Collection<String> addOrderItem (final Collection<String> order, final String item) {
		order.add(item);
		return order;
	}
}
