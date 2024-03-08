package mymath_oop;

public class MyMath {

	/**
	 * Returns the maximum of the three given numbers.
	 * 
	 * @post The result equals one of the given numbers.
	 *     | result == x || result == y || result == z
	 * @post The result is not less than the given numbers.
	 *     | x <= result && y <= result && z <= result
	 */
	static int max3(int x, int y, int z) {
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
	static int sqrt(int x) {
		if (x < 0) // Defensive programming.
			throw new IllegalArgumentException("x is negative");
		
		int result = 0;
		while ((result + 1) * (result + 1) <= x)
			result++;
		return result;
	}

}
