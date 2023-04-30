package src.class21;

import src.Solution;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。 
 * 每个值都认为是一张货币， 即便是值相同的货币也认为每一张都是不同的， 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2 一共就3种方法，所以返回3
 */
public class Code02_CoinsWayEveryPaperDifferent {
	// solution one
	public static int coinWays1(int[] arr, int aim) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		return process(arr, 0, aim);
	}
	
	// recursive method one 
	public static int process(int[] arr, int index, int aim) {
		if (aim < 0) {
			return 0;
		}
		
		if (index == arr.length) {
			return aim == 0 ? 1 : 0;
		} else {
			return process(arr, index + 1, aim) + process(arr, index + 1, aim - arr[index]);
		}
	}
	
	// dynamic program
	public static int coinWays2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 ) {
			return 0;
		}
		
		if (aim == 0) {
			return 1;
		}
		
		int[][] dp = new int[arr.length + 1][aim + 1];
		dp[arr.length][0] = 1;
		
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = 0; j <= aim; j++) {
				dp[i][j] = dp[i + 1][j] + (j - arr[i] >= 0 ? dp[i + 1][j - arr[i]] : 0);
			}
		}
		
		return dp[0][aim];
	}
	
	// for test
	public static int[] generateRandomArray(int len, int max) {
		int[] arr = new int[(int) (Math.random() * len)];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max) + 1;
		}
		
		return arr;
	}
	
	// main
	public static void main(String[] args) {
		int len = 10;
		int max = 10;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(len, max);
			int aim = (int) (Math.random() * max) + 1;
			
			int ans1 = coinWays1(arr, aim);
			int ans2 = coinWays2(arr, aim);
			
			if (ans1 != ans2) {
				System.out.println("ans1 = " + ans1 + ", ans2 = " + ans2);
				System.out.println("aim = " + aim);
				Solution.printArray(arr);
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}
}
