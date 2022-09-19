package com.snaptech.testcase;

import com.snaptech.testcase.datastructure.TestCase;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertThrows;


class ValidatorImplTest {

	ValidatorImpl validator = new ValidatorImpl();

	@Test
	void notValid_whenSawmillTreeTrunksNotInitilized (){
		TestCase tc = new TestCase(1,1);
		assertThrows(IllegalArgumentException.class, () -> validator.validate(tc));
	}

	@Test
	void notValid_whenTreeTrunksNotInitialized (){
		TestCase tc = new TestCase(1, 1);
		int[][] treeTrunks = tc.getTreeTrunks();
		treeTrunks[0] = new int[2];

		assertThrows(IllegalArgumentException.class, () -> validator.validate(tc));
	}

	@Test
	void notValid_whenOneTreeTrunkNotInitialized (){
		TestCase tc = new TestCase(1,1);
		int[][] treeTrunks = tc.getTreeTrunks();
		treeTrunks[0] = new int[2];
		treeTrunks[0][0] = 1;

		assertThrows(IllegalArgumentException.class, () -> validator.validate(tc));
	}

	@Test
	void valid () {

		final int sawmillCount = getRandom(1, 10);
		TestCase tc = new TestCase(1, sawmillCount);
		int[][] treeTrunks = tc.getTreeTrunks();

		for(int i = 0; i < sawmillCount; i++) {
			final int treeTrunkCount = getRandom(1, 10);
			treeTrunks[i] = new int[treeTrunkCount];
			for (int j = 0; j < treeTrunkCount; j++) {
				treeTrunks[i][j] = getRandom(1, 10);
			}
		}

		validator.validate(tc);
	}

	private static int getRandom (final int min, final int max) {
		return ThreadLocalRandom
			.current()
			.nextInt(min, max);
	}
}