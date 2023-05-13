package src.class24;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import src.Solution;

/**
 * 动态规划中利用窗口内最大值或最小值更新结构做优化（难） 
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。 每个值都认为是一张货币，
 * 返回组成aim的最少货币数 注意：因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了
 */
public class Code04_MinCoinsOnePaper {
	// solution one
	public static int minCoins(int[] arr, int aim) {
		return process(arr, 0, aim);
	}
	
	// recursive method one
	public static int process(int[] arr, int index, int rest) {
		if (rest < 0) {
			return Integer.MAX_VALUE;
		}
		
		if (index == arr.length) {
			return rest == 0 ? 0 : Integer.MAX_VALUE;
		} else {
			int p1 = process(arr, index + 1, rest);
			int p2 = process(arr, index + 1, rest - arr[index]);
			
			if (p2 != Integer.MAX_VALUE) {
				p2++;
			}
			
			return Math.min(p1, p2);
		}
	}
	
	// dynamic program solution one
	public static int dp1(int[] arr, int aim) {
		if (aim == 0) {
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
				int p1 = dp[index + 1][rest];
				int p2 = rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : Integer.MAX_VALUE;
				if (p2 != Integer.MAX_VALUE) {
					p2++;
				}
				
				dp[index][rest] = Math.min(p1, p2);
			}
		}
		
		return dp[0][aim];
	}
	
	// auxiliary class
	public static class Info {
		public int[] coins;
		public int[] papers;
		
		public Info(int[] c, int[] p) {
			coins = c;
			papers = p;
		}
	}
	
	// auxiliary class
	public static Info getInfo(int[] arr) {
		HashMap<Integer, Integer> counts = new HashMap<>();
		for (int num : arr) {
			if (!counts.containsKey(num)) {
				counts.put(num, 1);
			} else {
				counts.put(num, counts.get(num) + 1);
			}
		}
		
		int N = counts.size();
		int[] coins = new int[N];
		int[] papers = new int[N];
		int index = 0;
		
		for (Entry<Integer, Integer> entry : counts.entrySet()) {
			coins[index] = entry.getKey();
			papers[index++] = entry.getValue();
		}
		
		return new Info(coins, papers);
	}
	
	// dynamic program solution two
	public static int dp2(int[] arr, int aim) {
		if (aim == 0) {
			return 0;
		}
		
		Info info = getInfo(arr);
		int[] coins = info.coins;
		int[] papers = info.papers;
		
		int N = coins.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 0;
		
		for (int i = 1; i <= aim; i++) {
			dp[N][i] = Integer.MAX_VALUE;
		}
		
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				for (int paper = 1; paper * coins[index] <= aim && paper <= papers[index]; paper++) {
					if (rest - paper * coins[index] >= 0
					    && dp[index + 1][rest - paper * coins[index]] != Integer.MAX_VALUE) {
						dp[index][rest] = Math.min(dp[index][rest], paper + dp[index + 1][rest - paper * coins[index]]);
					}
				}
			}
		}
		
		return dp[0][aim];
	}
	
	// dynamic program solution three
	public static int dp3(int[] arr, int aim) {
		if (aim == 0) {
			return 0;
		}
		
		Info info = getInfo(arr);
		int[] coins = info.coins;
		int[] papers = info.papers;
		int N = coins.length;
		
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 0;
		for (int i = 1; i <= aim; i++) {
			dp[N][i] = Integer.MAX_VALUE;
		}
		
		for (int index = N - 1; index >= 0; index--) {
			for (int mod = 0; mod < Math.min(aim + 1, coins[index]); mod++) {
				LinkedList<Integer> queue = new LinkedList<>();
				queue.add(mod);
				dp[index][mod] = dp[index + 1][mod];
				
				for (int rest = mod + coins[index]; rest <= aim; rest += coins[index]) {
					while (!queue.isEmpty()
					    && (dp[index + 1][queue.peekLast()] == Integer.MAX_VALUE
					    || dp[index + 1][queue.peekLast()] + compensate(queue.peekLast(), rest, coins[index]) >= dp[index + 1][rest])) {
						queue.pollLast();
					}
					
					queue.addLast(rest);
					int overdue = rest - coins[index] * (papers[index] + 1);
					if (queue.peekFirst() == overdue) {
						queue.pollFirst();
					}
					
					if (dp[index + 1][queue.peekFirst()] == Integer.MAX_VALUE) {
						dp[index][rest] = Integer.MAX_VALUE;
					} else {
						dp[index][rest] = dp[index + 1][queue.peekFirst()] + compensate(queue.peekFirst(), rest, coins[index]);
					}
				}
			}
		}
		
		return dp[0][aim];
	}
	
	// auxiliary method
	public static int compensate(int pre, int cur, int coin) {
		return (cur - pre) / coin;
	}
	
	// for test
	public static int[] generateRandomArray(int len, int max) {
		int[] arr = new int[len];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max) + 1;
		}
		
		return arr;
	}
	
	// for test
	public static void test() {
		int maxLen = 20;
		int maxValue = 30;
		int testTimes = 30000;
		
		System.out.println("start");
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxLen, maxValue);
			int num = (int) (Math.random() * maxValue) + 1;
			
			int ans1 = minCoins(arr, num);
			int ans2 = dp1(arr, num);
			int ans3 = dp2(arr, num);
			int ans4 = dp3(arr, num);
			
			if (ans1 != ans2
			    || ans2 != ans3
			    || ans3 != ans4) {
				Solution.printArray(arr);
				System.out.println("ans1 = " + ans1
				    + ", ans2 = " + ans2 
				    + ", ans3 = " + ans3
				    + ", ans3 = " + ans4);
				System.out.println("Oops");
				break;
			}
		}
		System.out.println("end");
		
		System.out.println("=======");
		
		int aim = 0;
		int[] arr = null;
		long start;
		long end;
		int ans2;
		int ans3;
		                    
		System.out.println("performance test start");
		maxLen = 30000;
		maxValue = 20;
		aim = 60000;
		
		arr = generateRandomArray(maxLen, maxValue);
		
		start = System.currentTimeMillis();
		ans2 = dp2(arr, aim);
		end = System.currentTimeMillis();
		System.out.println("dp2 answer: " + ans2 + ", dp2 cost " + (end - start) + " ms");
		
		start = System.currentTimeMillis();
		ans3 = dp3(arr, aim);
		end = System.currentTimeMillis();
		System.out.println("dp3 answer: " + ans3 + ", dp3 cost " + (end - start) + " ms");
		System.out.println("performance test end");
		
		System.out.println("货币大量重复出现情况下");
		maxLen = 20000000;
		aim = 10000;
		maxValue = 10000;
		arr = generateRandomArray(maxLen, maxValue);
		start = System.currentTimeMillis();
		ans3 = dp3(arr, aim);
		end = System.currentTimeMillis();
		System.out.println("dp3 answer: " + ans3 + ", dp3 cost " + (end - start) + " ms");
		
		System.out.println("当货币很少重复时, dp2 比 dp3有常数时间优势 \n当货币大量重复时, dp3时间复杂度优化dp2 \ndp3的优化利用到了窗口内最小值的更新结构");
	}
	
	// main
	public static void main(String[] args) {
		test();
	}
}
