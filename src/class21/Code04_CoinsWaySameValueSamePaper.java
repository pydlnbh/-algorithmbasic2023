package src.class21;

import java.util.HashMap;
import java.util.Map.Entry;

import src.Solution;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。 
 * 每个值都认为是一张货币， 认为值相同的货币没有任何不同， 返回组成aim的方法数 
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4 方法：1+1+1+1、1+1+2、2+2 一共就3种方法，所以返回3
 */
public class Code04_CoinsWaySameValueSamePaper {
	// auxiliary class
	public static class Info {
		public int[] coins;
		public int[] papers;
		
		public Info(int[] c, int[] p) {
			coins = c;
			papers = p;
		}
	}
	
	// auxiliary method
	public static Info getInfo(int[] arr) {
		HashMap<Integer, Integer> counts = new HashMap<>();
		
		for (int num : arr) {
			if (!counts.containsKey(num)) {
				counts.put(num, 1);
			} else {
				counts.put(num, counts.get(num) + 1);
			}
 		}
		
		int length = counts.size();
		int[] conins = new int[length];
		int[] papers = new int[length];
		int index = 0;
		
		for (Entry<Integer, Integer> entry : counts.entrySet()) {
			conins[index] = entry.getKey();
			papers[index++] = entry.getValue();
		}
		
		return new Info(conins, papers);
	}
	
	// solution one
	public static int coinWays1(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim <= 0) {
			return 0;
		}
		
		Info info = getInfo(arr);
		
		return process(info.coins, info.papers, 0, aim);
	}
	
	// recursive method
	public static int process(int[] coins, int[] papers, int index, int rest) {
		if (index == coins.length) {
			return rest == 0 ? 1 : 0;
		}
		
		int ways = 0;
		for (int i = 0; i * coins[index] <= rest && i <= papers[index]; i++) {
			ways += process(coins, papers, index + 1, rest - i * coins[index]);
		}
		
		return ways;
	}
	
	// solution two
	public static int coinWays2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim <= 0) {
			return 0;
		}
		
		Info info = getInfo(arr);
		int[] coins = info.coins;
		int[] papers = info.papers;
		
		int[][] dp = new int[coins.length + 1][aim + 1];
		dp[coins.length][0] = 1;
		
		for (int i = coins.length - 1; i >= 0; i--) {
			for (int j = 0; j <= aim; j++) {
				int ways = 0;
				for (int k = 0; k * coins[i] <= j && k <= papers[i]; k++) {
					ways += dp[i + 1][j - k * coins[i]];
				}
				dp[i][j] = ways;
			}
		}
		
		return dp[0][aim];
	}
	
	// solution three
	public static int coinWays3(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim <= 0) {
			return 0;
		}
		
		Info info = getInfo(arr);
		int[] coins = info.coins;
		int[] papers = info.papers;
		
		int[][] dp = new int[coins.length + 1][aim + 1];
		dp[coins.length][0] = 1;
		
		for (int i = coins.length - 1; i >= 0; i--) {
			for (int j = 0; j <= aim; j++) {
				dp[i][j] = dp[i + 1][j];
				
				if (j - coins[i] >= 0) {
					dp[i][j] += dp[i][j - coins[i]];
				}
				
				if (j - coins[i] * (papers[i] + 1) >= 0) {
					dp[i][j] -= dp[i + 1][j - coins[i] * (papers[i] + 1)];
				}
			}
		}
		
		return dp[0][aim];
	}
	
	// for test
	public static int[] generateRandomArray(int len, int max) {
		int length = (int) (Math.random() * len);
		
		int[] ans = new int[length];
		
		for (int i = 0; i < length; i++) {
			ans[i] = (int) (Math.random() * max) + 1;
		}
		
		return ans;
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
			
			if (ans1 != ans2 || ans1 != ans3) {
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
