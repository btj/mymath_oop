package mymath_oop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class MyMathTest {
	
	/**
	 * Returns the maximum of the three given numbers.
	 * 
	 * @post The result equals one of the given numbers.
	 *     | result == x || result == y || result == z
	 * @post The result is not less than the given numbers.
	 *     | x <= result && y <= result && z <= result
	 */
	int max3(int x, int y, int z) {
		if (x < y)
			if (y < z)
				return z;
			else
				return y;
		else
			if (x < z)
				return z;
			else
				return x;
	}
	
	/**
	 * Returns the square root, rounded down, of the given number.
	 * 
	 * @throws IllegalArgumentException if the given value is negative.
	 *     | !(0 <= x)
	 * @post The result is nonnegative.
	 *     | 0 <= result
	 * @post The square of the result is not greater than x.
	 *     | result * result <= x
	 * @post The square of one more than the result is greater than x.
	 *     | x < (result + 1) * (result + 1) 
	 */
	int sqrt(int x) {
		if (x < 0) // Defensive programming.
			throw new IllegalArgumentException("x is negative");
		
		int result = 0;
		while ((result + 1) * (result + 1) <= x)
			result++;
		return result;
	}
	
	/**
	 * Returns the average of the element at index1 of the given array, and the element at index2 of the given array.
	 * 
	 * @pre | array != null
	 * @pre | 0 <= index1 && index1 < array.length
	 * @pre | 0 <= index2 && index2 < array.length
	 * 
	 * @inspects | array
	 * 
	 * @post | result == (array[index1] + array[index2]) / 2
	 */
	int computeAverage(int[] array, int index1, int index2) {
		return (array[index1] + array[index2]) / 2;
	}
	
	/**
	 * Returns the maximum element of the given array.
	 * 
	 * @pre | array != null
	 * @pre | array.length >= 1
	 * 
	 * @inspects | array
	 * 
	 * @post For each index i between 0, inclusive, and array.length, exclusive, the element in array at index i is not greater
	 *       than the result.
	 *     | IntStream.range(0, array.length).allMatch(i -> array[i] <= result)
	 * @post The result is an element of the array.
	 *     | IntStream.range(0, array.length).anyMatch(i -> array[i] == result)
	 */
	int max(int[] array) {
		int result = array[0];
		for (int i = 1; i < array.length; i++)
			if (array[i] > result)
				result = array[i];
		return result;
	}
	
	/**
	 * Swaps the elements at index1 and index2 in the given array.
	 * 
	 * @pre | array != null
	 * @pre | 0 <= index1 && index1 < array.length
	 * @pre | 0 <= index2 && index2 < array.length
	 * 
	 * @mutates | array
	 * 
	 * @post | array[index2] == old(array[index1])
	 * @post | array[index1] == old(array[index2])
	 * @post The elements at indices other than index1 and index2 are unchanged.
	 *     | IntStream.range(0, array.length).allMatch(i -> i == index1 || i == index2 ? true : array[i] == old(array.clone())[i])
	 */
	// A ? B : C means "if A then B else C"
	void swap(int[] array, int index1, int index2) {
		int value1 = array[index1];
		array[index1] = array[index2];
		array[index2] = value1;
	}
	
	int median(int x, int y, int z) {
		// You can assume that the arguments are distinct.
		// TODO: Implement, document (informally and formally), and test
	}
	
	/**
	 * Returns the first index of `needle` in `haystack`, or -1 if `needle` does not appear in `haystack`. 
	 */
	int find(int[] haystack, int needle) {
		// TODO: Implement, document (informally and formally), and test
		// Deal with invalid arguments contractually (i.e. using @pre)
	}
	
	/**
	 * Replaces each element of `array` by its negation.
	 */
	void negate_all(int[] array) {
		// TODO: Implement, document (informally and formally), and test
		// Deal with invalid arguments defensively (i.e. by throwing an exception and using @throws in the documentation)
	}
	
	/**
	 * Inserts `value` into the sequence of values at indices 0 (inclusive) through `n` (exclusive) in `array`, so that
	 * this sequence remains sorted.
	 * 
	 * @pre | array != null
	 * @pre | 0 <= n && n < array.length
	 * @pre | IntStream.range(0, n - 1).allMatch(i -> array[i] <= array[i + 1])
	 * @mutates | array
	 * @post The sequence of elements at indices 0 through n + 1 is in ascending order.
	 *     | IntStream.range(0, n).allMatch(i -> array[i] <= array[i + 1]) 
	 * @post No elements have been removed from the sequence.
	 *     | IntStream.range(0, n + 1).allMatch(i ->
	 *     |     IntStream.range(0, n + 1).filter(j -> array[j] == array[i]).count() ==
	 *     |     IntStream.range(0, n).filter(j -> old(array.clone())[j] == array[i]).count() + (array[i] == value ? 1 : 0)
	 *     | )
	 */
	void insert(int[] array, int n, int value) {
		// TODO: Implement and test.
	}
	
	/**
	 * Sorts the given array.
	 */
	void sort(int[] array) {
		// TODO: Implement (hint: use `insert`), document (informally and formally), and test.
	}
	
	
	@Test
	void testComputeAverage() {
		assertEquals(3, computeAverage(new int[] {-1, 2, -3, 4}, 1, 3));
		assertEquals(3, computeAverage(null, 1, 3));
	}
	
	@Test
	void testSqrt() {
//		assertEquals(5, sqrt(-1));
		assertEquals(0, sqrt(0));
		assertEquals(1, sqrt(1));
		assertEquals(1, sqrt(2));
		assertEquals(1, sqrt(3));
		assertEquals(2, sqrt(4));
		assertEquals(3, sqrt(9));
	}

	@Test
	void testMax3() {
		assertEquals(30, max3(10, 20, 30));
		assertEquals(30, max3(30, 20, 10));
		assertEquals(30, max3(10, 30, 20));
		assertEquals(30, max3(30, 10, 20));
		assertEquals(30, max3(20, 10, 30));
		assertEquals(30, max3(20, 30, 10));
	}

}
