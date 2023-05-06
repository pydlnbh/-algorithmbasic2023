package src.class22;

/**
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有： 1+1+1+1、1+1+2、1+3、2+2、4 5种，所以返回5
 */
public class Code03_SplictNumber {
	// solution one
	public static int ways(int n) {
		if (n < 0) {
			return 0;
		}
		
		if (n == 1) {
			return 1;
		}
		
		return process(1, n);
	}
	
	// recursive method one
	public static int process(int pre, int rest) {
		if (rest == 0) {
			return 1;
		}
		
		if (pre > rest) {
			return 0;
		}
		
		int ways = 0;
		for (int first = pre; first <= rest; first++) {
			ways += process(first, rest - first);
		}
		
		return ways;
	}
	
	// dynamic program solution one
	public static int dp1(int n) {
		if (n < 0) {
			return 0;
		}
		
		if (n == 0) {
			return 1;
		}
		
		int[][] dp = new int[n + 1][n + 1];
		for (int pre = 1; pre <= n; pre++) {
			dp[pre][0] = 1;
			dp[pre][pre] = 1;
		}
		
		for (int pre = n - 1; pre >= 1; pre--) {
			for (int rest = pre + 1; rest <= n; rest++) {
				int ways = 0;
				
				for (int first = pre; first <= rest; first++) {
					ways += dp[first][rest - first];
				}
				
				dp[pre][rest] = ways;
			}
		}
		
		return dp[1][n];
	}
	
	// dynamic program solution two
	public static int dp2(int n) {
		if (n < 0) {
			return 0;
		}
		
		if (n == 0) {
			return 1;
		}
		
		int[][] dp = new int[n + 1][n + 1];
		for (int pre = 1; pre <= n; pre++) {
			dp[pre][0] = 1;
			dp[pre][pre] = 1;
		}
		
		for (int pre = n - 1; pre >= 1; pre--) {
			for (int rest = pre + 1; rest <= n; rest++) {
				dp[pre][rest] = dp[pre + 1][rest];
				dp[pre][rest] += dp[pre][rest - pre];
			}
		}
		
		return dp[1][n];
	}
	
	// for test
	public static void test() {
		int max = 50;
		int testTimes = 100;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int num = (int) (Math.random() * max) + 1;
			
			int ans1 = ways(max);
			int ans2 = dp1(max);
			int ans3 = dp2(max);
			
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("num = " + num
				    + ", ans1 = " + ans1
				    + ", ans2 = " + ans2
				    + ", ans3 = " + ans3);
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
