package src.class23;

import src.Solution;

/**
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，
 * 尽量让两个集合的累加和接近 返回最接近的情况下，较小集合的累加和
 */
public class Code01_SplictSumClosed {
	// solution one
	public static int right(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		return process(arr, 0, num / 2);
	}
	
	// recursive method one
	public static int process(int[] arr, int index, int sum) {
		if (index == arr.length) {
			return 0;
		} else {
			int p1 = process(arr, index + 1, sum);
			
			int p2 = 0;
			if (arr[index] < sum) {
				p2 = arr[index] + process(arr, index + 1, sum - arr[index]);
			}
			
			return Math.max(p1, p2);
		}
	}
	
	// solution two
	public static int dp(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		num /= 2;
		int N = arr.length;
		int[][] dp = new int[N + 1][num + 1];
		
		for (int i = N - 1; i >= 0; i--) {
			for (int rest = 0; rest <= num; rest++) {
				int p1 = dp[i + 1][rest];
				
				int p2 = 0;
				if (arr[i] < rest) {
					p2 = arr[i] + dp[i + 1][rest - arr[i]];
				}
				
				dp[i][rest] = Math.max(p1, p2);
			}
		}
		
		return dp[0][num];
	}

	// for test
	public static int[] generateRandomArray(int len, int max) {
		int length = (int) (Math.random() * len);
		
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			arr[i] = (int) (Math.random() * max) + 1;
		}
		
		return arr;
	}
	
	// for test
	public static void test() {
		int len = 50;
		int max = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(len, max);
			int num = (int) (Math.random() * max) + 1;
			
			int ans1 = right(arr, num);
			int ans2 = dp(arr, num);
			
			if (ans1 != ans2) {
				System.out.println("num = " + num);
				Solution.printArray(arr);
				System.out.println("ans1 = " + ans2 + ", ans2 = " + ans2);
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}
	
	// main
	public static void main(String[] args) {
		test();
	}
}
