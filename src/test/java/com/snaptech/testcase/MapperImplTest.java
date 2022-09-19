package com.snaptech.testcase;

import com.snaptech.testcase.datastructure.TestCase;
import com.snaptech.testcase.datastructure.TestCaseResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MapperImplTest {

	@Mock private Processor processor;
	@InjectMocks private MapperImpl mapper;
	@Captor ArgumentCaptor<TestCase> testCaseCaptor;

	@Test
	void mapOneTestCaseWithOneSawmill () {
		mockProcessor();
		mapper.map(new int[]{1});
		mapper.map(new int[]{1, 1});
		mapper.map(new int[]{2});

		TestCase result = captureTestCase(1);

		assertEquals(1, result.getOrdinal());

		int[][] treeTrunks =  result.getTreeTrunks();
		assertEquals(1, treeTrunks.length);
		assertEquals(1, treeTrunks[0].length);
		assertEquals(1, treeTrunks[0][0]);
	}

	@Test
	void mapOneTestCaseWithTwoSawmill() {
		mockProcessor();
		mapper.map(new int[]{2});
		mapper.map(new int[]{2, 2, 3});
		mapper.map(new int[]{1, 5});
		mapper.map(new int[]{2});
		TestCase case1 = captureTestCase(1);

		assertEquals(1, case1.getOrdinal());

		int[][] treeTrunks =  case1.getTreeTrunks();
		assertEquals(2, treeTrunks.length);
		assertEquals(2, treeTrunks[0].length);
		assertEquals(2, treeTrunks[0][0]);
		assertEquals(3, treeTrunks[0][1]);
		assertEquals(1, treeTrunks[1].length);
		assertEquals(5, treeTrunks[1][0]);
	}

	@Test
	void mapTwoTestCaseWithOneSawmill () {
		mockProcessor();
		mapper.map(new int[]{1});
		mapper.map(new int[]{2, 2, 3});
		mapper.map(new int[]{1});
		mapper.map(new int[]{1, 3});
		mapper.map(new int[]{1});

		TestCase result = captureTestCase(2);

		assertEquals(2, result.getOrdinal());

		int[][] treeTrunks =  result.getTreeTrunks();
		assertEquals(1, treeTrunks.length);
		assertEquals(1, treeTrunks[0].length);
		assertEquals(3, treeTrunks[0][0]);
	}

	private void mockProcessor () {
		when(processor.process(any())).thenReturn(new TestCaseResult(1, 5));
	}

	private TestCase captureTestCase (final int times) {
		verify(processor, times(times)).process(testCaseCaptor.capture());
		return testCaseCaptor.getValue();
	}
}