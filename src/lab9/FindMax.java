package lab9;

/**
 *
 * COMP 3021
 *
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29]
another thread the max among the cells [30,59]
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 *
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };
	int val1 = 0;
	int val2 = 0;
	int val3 = 0;
	int max = 0;

	public static void main(String[] args) {
		new FindMax().printMax();
	}

	public void printMax() {
		// this is a single threaded version
		// int max = findMax(0, 29);
		// max = findMax(30, 59);
		// max = findMax(60, 89);

		Thread max1 = new Thread() {
			@Override
			public void run (){
				val1 = findMax(0, 29);
			}
		};
		Thread max2 = new Thread() {
			@Override
			public void run (){
				val2 = findMax(30, 59);
			}

		};
		Thread max3 = new Thread() {
			@Override
			public void run (){
				val3 = findMax(60, 89);
			}
		};
		max1.start();
		max2.start();
		max3.start();

		Thread finalmax = new Thread(){
			@Override
			public void run (){
				if (val1 >= val2 && val1 >= val3){
					max = val1;
				}
				else if (val2 >= val1 && val2 >= val3){
					max = val2;
				}
				else {
					max = val3;
				}
				try {
					max1.join();
					max2.join();
					max3.join();
				} catch (InterruptedException ex) {}
			}
		};
		finalmax.start();
		System.out.println("the max value is " + max);
	}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 *
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
}
