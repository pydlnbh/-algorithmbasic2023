package src.class46;

//本题测试链接 : https://leetcode.com/problems/burst-balloons/
public class Code01_BurstBalloons {
	// solution one
	public static int maxCoins0(int[] arr) {
		int N = arr.length;
		int[] help = new int[N + 2];
		for (int i = 0; i < N; i++) {
			help[i + 1] = arr[i];
		}
		
		help[0] = 1;
		help[N + 1] = 1;
		
		return func(help, 1, N);
	}
	
	// L-1位置，和R+1位置，永远不越界，并且，[L-1] 和 [R+1] 一定没爆呢！
	// 返回，arr[L...R]打爆所有气球，最大得分是什么
	public static int func(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L - 1] * arr[L] * arr[R + 1];
		}
		
		// 尝试每一种情况，最后打爆的气球，是什么位置
		// L...R
		// L 位置的气球最后打爆
		int max = func(arr, L + 1, R) + arr[L - 1] * arr[L] * arr[R + 1];
		// R 位置的气球最后打爆
		max = Math.max(max, func(arr, L, R - 1) + arr[L - 1] * arr[R] * arr[R + 1]);
		// 尝试所有L...R, 中间的问题, (L, R)
		for (int i = L + 1; i < R; i++) {
			// i 位置的气球，最后打爆
			int left = func(arr, L, i - 1);
			int right = func(arr, i + 1, R);
			int last = arr[L - 1] * arr[i] + arr[R + 1];
			int cur = left + right + last;
			max = Math.max(max, cur);
		}
		
		return max;
	}
	
	// solution two
	public static int maxCoins1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		if (arr.length == 1) {
			return arr[0];
		}
		
		int N = arr.length;
		int[] help = new int[N + 2];
		help[0] = 1;
		help[N + 1] = 1;
		
		for (int i = 0; i < N; i++) {
			help[i + 1] = arr[i];
		}
		
		return process(help, 1, N);
	}
	
	public static int process(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L - 1] * arr[L] * arr[R + 1];
		}
		
		int max = Math.max(arr[L - 1] * arr[L] * arr[R + 1] + process(arr, L + 1, R),
				arr[L - 1] * arr[R] * arr[R + 1] + process(arr, L, R - 1));
		
		for (int i = L + 1; i < R; i++) {
			max = Math.max(max, arr[L - 1] * arr[i] * arr[R + 1] + process(arr, L, i - 1) + process(arr, i + 1, R));
		}
		
		return max;
	}
	
	// solution three
	public static int maxCoins2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		if (arr.length == 1) {
			return arr[0];
		}
		
		int N = arr.length;
		int[] help = new int[N + 2];
		help[0] = 1;
		help[N + 1] = 1;
		for (int i = 0; i < N; i++) {
			help[i + 1] = arr[i];
		}
		
		int[][] dp = new int[N + 2][N + 2];
		
		for (int i = 1; i <= N; i++) {
			dp[i][i] = help[i - 1] * help[i] * help[i + 1];
		}
		
		for (int L = N; L >= 1; L--) {
			for (int R = L + 1; R <= N; R++) {
				int ans = help[L - 1] * help[L] * help[R + 1] + dp[L + 1][R];
				ans = Math.max(ans, help[L - 1] * help[R] * help[R + 1] + dp[L][R - 1]);
				
				for (int i = L + 1; i < R; i++) {
					ans = Math.max(ans, help[L - 1] * help[i] * help[R + 1] + dp[L][i - 1] + dp[i + 1][R]);
				}
				
				dp[L][R] = ans;
			}
		}
		
		return dp[1][N];
	}
}
