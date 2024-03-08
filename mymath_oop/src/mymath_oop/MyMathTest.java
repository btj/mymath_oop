package mymath_oop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class MyMathTest {
	
	
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
	
	/**
	 * Returns the median of the given numbers.
	 * 
	 * @pre The given numbers are distinct.
	 *     | x != y && y != z && x != z
	 * @post | result == x || result == y || result == z
	 * @post | x < result || y < result || z < result
	 * @post | result < x || result < y || result < z
	 */
	int median(int x, int y, int z) {
		if (x < y)
			if (y < z)
				return y;
			else
				if (x < z)
					return z;
				else
					return x;
		else
			if (x < z)
				return x;
			else
				if (y < z)
					return z;
				else
					return y;
	}
	
	@Test
	void testMedian() {
		assertEquals(20, median(10, 20, 30));
		assertEquals(20, median(20, 10, 30));
		assertEquals(20, median(10, 30, 20));
		assertEquals(20, median(30, 20, 10));
		assertEquals(20, median(30, 10, 20));
		assertEquals(20, median(20, 30, 10));
	}
	
	/**
	 * Returns the first index of `needle` in `haystack`, or -1 if `needle` does not appear in `haystack`.
	 * 
	 * @pre | haystack != null
	 * 
	 * @inspects | haystack
	 * 
	 * @post If `result` equals -1, `needle` does not appear in `haystack`
	 *    | result != -1 || IntStream.range(0, haystack.length).allMatch(i -> haystack[i] != needle)
	 * @post
	 *    | result == -1 ||
	 *    | 0 <= result
	 *    |     && result < haystack.length
	 *    |     && haystack[result] == needle
	 *    |     && IntStream.range(0, result).allMatch(i -> haystack[i] != needle)
	 */
	int find(int[] haystack, int needle) {
		for (int i = 0; i < haystack.length; i++)
			if (haystack[i] == needle)
				return i;
		return -1;
	}
	
	@Test
	void testFind() {
		assertEquals(1, find(new int[] {10, 20, 30}, 20));
		assertEquals(0, find(new int[] {20, 10, 30}, 20));
		assertEquals(2, find(new int[] {20, 10, 30}, 30));
		assertEquals(-1, find(new int[] {20, 10, 30}, 25));
	}
	
	/**
	 * Replaces each element of `array` by its negation.
	 * 
	 * @throws IllegalArgumentException | array == null
	 * 
	 * @mutates | array
	 * 
	 * @post | IntStream.range(0, array.length).allMatch(i -> array[i] == -old(array.clone())[i])
	 */
	void negateAll(int[] array) {
		if (array == null)
			throw new IllegalArgumentException("`array` is null");
		
		for (int i = 0; i < array.length; i++)
			array[i] = -array[i];
	}
	
	@Test
	void testNegateAll() {
		int[] array1 = {10, 20, -30};
		negateAll(array1);
		assertArrayEquals(new int[] {-10, -20, 30}, array1);
		
		assertThrows(IllegalArgumentException.class, () -> negateAll(null));
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
		int i = 0;
		for (; i < n; i++)
			if (value <= array[i])
				break;
		for (int j = n; i < j; j--)
			array[j] = array[j - 1];
		array[i] = value;
	}
	
	@Test
	void testInsert() {
		{
			int[] myArray = {10, 20, 30, 0, 0};
			insert(myArray, 3, 5);
			assertArrayEquals(new int[] {5, 10, 20, 30, 0}, myArray);
		}
		{
			int[] myArray = {10, 20, 30, 0, 0};
			insert(myArray, 3, 15);
			assertArrayEquals(new int[] {10, 15, 20, 30, 0}, myArray);
		}
		{
			int[] myArray = {10, 20, 30, 0, 0};
			insert(myArray, 3, 25);
			assertArrayEquals(new int[] {10, 20, 25, 30, 0}, myArray);
		}
		{
			int[] myArray = {10, 20, 30, 0, 0};
			insert(myArray, 3, 35);
			assertArrayEquals(new int[] {10, 20, 30, 35, 0}, myArray);
		}
	}
	
	/**
	 * Returns the number of occurrences of `value` in `array`.
	 * 
	 * @pre | array != null
	 * @post | result == IntStream.range(0, array.length).filter(i -> array[i] == value).count()
	 */
	long count(int[] array, int value) {
		return IntStream.range(0, array.length).filter(i -> array[i] == value).count();
	}
	
	/**
	 * Sorts the given array.
	 * 
	 * @pre | array != null
	 * 
	 * @mutates | array
	 * 
	 * @post The elements of `array` occur in ascending order.
	 *     | IntStream.range(1, array.length).allMatch(i -> array[i - 1] <= array[i])
	 * @post
	 *     | Arrays.stream(array).allMatch(element ->
	 *     |     count(array, element) == count(old(array.clone()), element)
	 *     | )
	 * 
	 */
	void sort(int[] array) {
		for (int n = 1; n < array.length; n++)
			insert(array, n, array[n]);
	}
	
	@Test
	void testSort() {
		int[] myArray = {20, 5, 30, 10};
		sort(myArray);
		assertArrayEquals(new int[] {5, 10, 20, 30}, myArray);
	}
	
	
	@Test
	void testComputeAverage() {
		assertEquals(3, computeAverage(new int[] {-1, 2, -3, 4}, 1, 3));
	}
	
	@Test
	void testSqrt() {
//		assertEquals(5, sqrt(-1));
		assertEquals(0, MyMath.sqrt(0));
		assertEquals(1, MyMath.sqrt(1));
		assertEquals(1, MyMath.sqrt(2));
		assertEquals(1, MyMath.sqrt(3));
		assertEquals(2, MyMath.sqrt(4));
		assertEquals(3, MyMath.sqrt(9));
	}

	@Test
	void testMax3() {
		assertEquals(30, MyMath.max3(10, 20, 30));
		assertEquals(30, MyMath.max3(30, 20, 10));
		assertEquals(30, MyMath.max3(10, 30, 20));
		assertEquals(30, MyMath.max3(30, 10, 20));
		assertEquals(30, MyMath.max3(20, 10, 30));
		assertEquals(30, MyMath.max3(20, 30, 10));
	}

}
