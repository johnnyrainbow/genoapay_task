package genoapay;

public class ProfitCalculator {

	/*
	 * Suppose we could access yesterday's stock prices as a list, where: The
	 * indices are the time in minutes past trade opening time, which was 10:00am
	 * local time. The values are the price in dollars of the stock at that time. So
	 * if the stock cost $5 at 11:00am, stock_prices_yesterday[60] = 5. Write an
	 * efficient function that takes an array of stock prices and returns the best
	 * profit could have been made from 1 purchase and 1 sale of 1 stock.
	 * 
	 * You must buy before you sell. You may not buy and sell in the same time step
	 * (at least 1 minute must pass).
	 */

	// NOTE: I have implemented two solutions, one in quadratic time and
	// one in linear time.

	// Linear time O(n)
	public int getMaxProfitLinear(int[] list) {
		int current_lowest_val = 0;
		Integer greatest_diff = null;

		if (list == null || list.length < 2)
			return -1; // List requires not null and at least 2 values

		current_lowest_val = list[0]; // Set to first value in list
		for (int i = 1; i < list.length; i++) {

			int diff = list[i] - current_lowest_val;
			if (greatest_diff == null)
				greatest_diff = diff;

			if (list[i] < current_lowest_val) { // If current value smaller than current lowest value
				current_lowest_val = list[i];
				continue;
			}

			if (diff > greatest_diff) // Check current value against current lowest value
				greatest_diff = diff; // Set new greatest diff
		}
		
		return greatest_diff;
	}

	// Quadratic time O(n^2)
	public int getMaxProfitQuadratic(int[] list) {
		Integer greatest_diff = null;
		if (list == null || list.length < 2)
			return -1; // List requires not null and at least 2 values

		for (int i = 0; i < list.length; i++) {
			Integer diff = null;
			for (int j = i + 1; j < list.length; j++) {
				int delta = list[j] - list[i];
				if (diff == null || delta > diff)
					diff = delta;
			}

			if (diff == null)
				continue;

			if (greatest_diff == null || diff > greatest_diff)
				greatest_diff = diff;
		}
		return greatest_diff;
	}

}