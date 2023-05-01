package src.class21;

import src.Solution;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。 返回组成aim的方法数 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2 一共就3种方法，所以返回3
 */
public class Code03_CoinsWayNoLimit {
	// solution one
	public static int coinWays1(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		
		return process(arr, 0, aim);
	}
	
	// recursive method
	public static int process(int[] arr, int index, int aim) {
		if (index == arr.length) {
			return aim == 0 ? 1 : 0;
		}
		
		int ways = 0;
		for (int i = 0; i * arr[index] <= aim; i++) {
			ways += process(arr, index + 1, aim - arr[index] * i);
		}
		
		return ways;
	}
	
	// solution two
	public static int coinWays2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		
		int[][] dp = new int[arr.length + 1][aim + 1];
		dp[arr.length][0] = 1;
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = 0; j <= aim; j++) {
				int ways = 0;
				for (int k = 0; k * arr[i] <= j; k++) {
					ways += dp[i + 1][j - arr[i] * k];
				}
				dp[i][j] = ways;
			}
		}
		
		return dp[0][aim];
	}
	
	public static int coinWays3(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		
		int[][] dp = new int[arr.length + 1][aim + 1];
		dp[arr.length][0] = 1;
		
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = 0; j <= aim; j++) {
				dp[i][j] = dp[i + 1][j];
				if (j - arr[i] >= 0) {
					dp[i][j] += dp[i][j - arr[i]];
				}
			}
		}
		
		return dp[0][aim];
	}
	
	// for test
	public static int[] generateRandomArray(int len, int max) {
		int l = (int) (Math.random() * len);
		int[] arr = new int[l];
		for (int i = 0; i < l; i++) {
			arr[i] = (int) (Math.random() * max) + 1;
		}
		return arr;
	}
	
	// for test
	public static void test() {
		int len = 10;
		int max = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(len, max);
			int num = (int) (Math.random() * max) + 1;
			
			int ans1 = coinWays1(arr, num);
			int ans2 = coinWays2(arr, num);
			int ans3 = coinWays3(arr, num);
			
			if (ans1 != ans2 || ans2 != ans3) {
				System.out.println("num = " + num);
				Solution.printArray(arr);
				System.out.println("ans1 = " + ans1 
				    + ", ans2 = " + ans2
				    + ", ans3 = " + ans3);
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
