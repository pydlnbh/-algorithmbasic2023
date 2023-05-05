package src.class22;

import src.Solution;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。 返回组成aim的最少货币数
 */
public class Code02_MinCoinsNoLimit {
	// solution one
	public static int minCoins1(int[] arr, int aim) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return process(arr, 0, aim);
	}
	
	// recursive method one
	public static int process(int[] arr, int index, int aim) {
		if (index == arr.length) {
			return aim == 0 ? 0 : Integer.MAX_VALUE;
		} else {
			int ans = Integer.MAX_VALUE;
			for (int i = 0; i * arr[index] <= aim; i++) {
				int next = process(arr, index + 1, aim - i * arr[index]);
				if (next != Integer.MAX_VALUE) {
					ans = Math.min(ans, i + next);
				}
			}
			return ans;
		}
	}
	
	// solution two
	public static int minCoins2(int[] arr, int aim) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 0;
		
		for (int i = 1; i <= aim; i++) {
			dp[N][i] = Integer.MAX_VALUE;
		}
		
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				int ans = Integer.MAX_VALUE;
				
				for (int i = 0; i * arr[index] <= rest; i++) {
					int next = dp[index + 1][rest - i * arr[index]];
					if (next != Integer.MAX_VALUE) {
						ans = Math.min(ans, i + next);
					}
				}
				
				dp[index][rest] = ans;
			}
		}
		
		return dp[0][aim];
	}
	
	// solution three
	public static int minCoins3(int[] arr, int aim) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 0;
		
		for (int i = 1; i <= aim; i++) {
			dp[N][i] = Integer.MAX_VALUE;
		}
		
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				if (rest - arr[index] >= 0
				    && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
					dp[index][rest] = Math.min(dp[index][rest],
					    dp[index][rest - arr[index]] + 1);
				}
			}
		}
		
		return dp[0][aim];
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
		int len = 10;
		int max = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(len, max);
			int aim = (int) (Math.random() * max);
			
			int ans1 = minCoins1(arr, aim);
			int ans2 = minCoins2(arr, aim);
			int ans3 = minCoins3(arr, aim);
			
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("aim = " + aim);
				Solution.printArray(arr);
				System.out.println(
					"ans1 = " + ans1 + 
					", ans2 = " + ans2 + 
					", ans3 = " + ans3);
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
