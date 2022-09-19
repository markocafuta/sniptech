package com.snaptech.input;

public class ParserImpl implements Parser{

	@Override
	public int[] parse (final String input) {
		validate(input);

		String[] sTokens = input.trim().split(" +");
		int[] tokens = convertToIntArray(sTokens);

		validateTreeTrunkCountPerSawmill(tokens);
		return tokens;
	}

	private static void validate (final String input) {
		if (!input.matches("^([0-9]+ *)*$"))
			throw new IllegalArgumentException("Invalid input!");
	}

	private static int[] convertToIntArray (final String[] sTokens) {
		int[] tokens = new int[sTokens.length];

		for (int i = 0; i < sTokens.length; i++) {
			tokens[i] = Integer.parseInt(sTokens[i]);
			validateGreaterThenZero(tokens[i]);
		}
		return tokens;
	}

	private static void validateGreaterThenZero(final int val) {
		if (val <= 0)
			throw new IllegalArgumentException(val + " is not grater then 0!");
	}

	private static void validateTreeTrunkCountPerSawmill (final int[] tokens) {
		if (tokens.length != 1 && tokens[0] != tokens.length - 1)
			throw new IllegalArgumentException("Invalid token length. Expected: " + tokens[0] + "; Actual: " +  (tokens.length - 1));
	}
}
