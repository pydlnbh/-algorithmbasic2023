package src.class25;

import java.util.Stack;

/**
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 */
public class Code02_AllTimesMinToMax {
	// solution one
	public static int max1(int[] arr) {
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				int minNum = Integer.MAX_VALUE;
				int sum = 0;
				
				for (int k = i; k <= j; k++) {
					sum += arr[k];
					minNum = Math.min(minNum, arr[k]);
				}
				
				max = Math.max(max, minNum * sum);
			}
		}
		
		return max;
	}
	
	// solution two
	public static int max2(int[] arr) {
		int size = arr.length;
		int[] sums = new int[size];
		sums[0] = arr[0];
		
		for (int i = 1; i < size; i++) {
			sums[i] = sums[i - 1] + arr[i];
		}
		
		int max = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < size; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				int j = stack.pop();
				max = Math.max(max, 
				    (stack.isEmpty() 
		    		? sums[i - 1] 
    				: (sums[i - 1] - sums[stack.peek()])) 
				    * arr[j]);
			}
			stack.push(i);
		}
		
		while (!stack.isEmpty()) {
			int j = stack.pop();
			max = Math.max(max, 
				(stack.isEmpty() 
				? sums[size - 1]
				: (sums[size - 1] - sums[stack.peek()])) 
			    * arr[j]);
		}
		
		return max;
	}
	
	// solution two
	public static int maxSumMinProduct(int[] arr) {
		int size = arr.length;
		long[] sums = new long[size];
		sums[0] = arr[0];
		for (int i = 1; i < size; i++) {
			sums[i] = sums[i - 1] + arr[i];
		}
		
		long max = Long.MIN_VALUE;
		int[] stack = new int[size];
		int stackSize = 0;
		
		for (int i = 0; i < size; i++) {
			while (stackSize != 0 && arr[stack[stackSize - 1]] >= arr[i]) {
				int j = stack[--stackSize];
				max = Math.max(max, 
				    (stackSize == 0
				    ? sums[i - 1]
		    		: sums[i - 1] - sums[stack[stackSize - 1]]) * arr[j]);
			}
			stack[stackSize++] = i;
		}
		
		while (stackSize != 0) {
			int j = stack[--stackSize];
			max = Math.max(max, 
			    (stackSize == 0
			    ? sums[size - 1]
	    		: sums[size - 1] - sums[stack[stackSize - 1]]) * arr[j]);
		}
		
		return (int) (max % 1000000007);
	}
	
	// for test
	public static int[] generateRandomArray(int len, int max) {
		int[] arr = new int[(int) (Math.random() * len) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((Math.random() * max) + 1);
		}
		return arr;
	}
	
	// for test
	public static void test() {
		int maxLen = 5;
		int maxValue = 10;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxLen, maxValue);
			
			if (max1(arr) != max2(arr)) {
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}
	
	public static void main(String[] args) {
		test();
	}

}
