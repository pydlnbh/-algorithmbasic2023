package src.class43;

/*
 * 在"100 game"这个游戏中，两名玩家轮流选择从1到10的任意整数，累计整数和
 * 先使得累计整数和达到或超过100的玩家，即为胜者，如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
 * 例如，两个玩家可以轮流从公共整数池中抽取从1到15的整数（不放回），直到累计整数和 >= 100
 * 给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和）
 * 判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）
 * 你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。
 * Leetcode题目：https://leetcode.com/problems/can-i-win/
 */
public class Code01_CanIWin {
	// solution one
	public static boolean canIWin0(int choose, int total) {
		if (total == 0) {
			return true;
		}
		
		if ((choose * (choose + 1) >> 1) < total) {
			return false;
		}
		
		int[] arr = new int[choose];
		for (int i = 0; i < choose; i++) {
			arr[i] = i + 1;
		}
		
		return process(arr, total);
	}
	
	public static boolean process(int[] arr, int rest) {
		if (rest <= 0) {
			return false;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != -1) {
				int cur = arr[i];
				arr[i] = -1;
				boolean next = process(arr, rest - cur);
				arr[i] = cur;
				if (!next) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	// solution two
	public static boolean canIWin1(int choose, int total) {
		if (total == 0) {
			return true;
		}
		
		if ((choose * (choose + 1) >> 1) < total) {
			return false;
		}
		
		return process1(choose, 0, total);
	}
	
	public static boolean process1(int choose, int status, int rest) {
		if (rest <= 0) {
			return false;
		}
		
		for (int i = 1; i <= choose; i++) {
			if (((1 << i) & status) == 0) {
				if (!process1(choose, (status | (1 << i)), rest - i)) {
					return true;
				}
			}
		}
		
		return false;
	} 
	
	// solution three
	public static boolean canIWin2(int choose, int total) {
		if (total == 0) {
			return true;
		}
		
		if ((choose * (choose + 1) >> 1) < total) {
			return false;
		}
		
		int[] dp = new int[1 << (choose + 1)];
		
		return process2(choose, 0, total, dp);
	}
	
	public static boolean process2(int choose, int status, int rest, int[] dp) {
		if (dp[status] != 0) {
			return dp[status] == 1 ? true : false;
		}
		
		boolean ans = false;
		if (rest > 0) {
			for (int i = 1; i <= choose; i++) {
				if (((1 << i) & status) == 0) {
					if (!process2(choose, (status | (1 << i)), rest - i, dp)) {
						ans = true;
						break;
					}
				}
			}
		}
		
		dp[status] = ans ? 1 : -1;
		return ans;
	}
}
