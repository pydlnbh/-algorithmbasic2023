package src.class22;

/**
 * 给定3个参数，N，M，K 怪兽有N滴血，等着英雄来砍自己 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值 求K次打击之后，英雄把怪兽砍死的概率
 */
public class Code01_KillMonster {
	//solution one
	public static double right(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		
		double all = Math.pow(M + 1, K);
		long kill = process(N, M, K);
		
		return (double) kill / all;
	}
	
	// recursive method one
	public static long process(int hp, int attack, int times) {
		if (times == 0) {
			return hp <= 0 ? 1 : 0;
		}
		
		if (hp <= 0) {
			return (long) Math.pow(attack + 1, times);
		}
		
		long ways = 0;
		for (int i = 0; i <= attack; i++) {
			ways += process(hp - i, attack, times - 1);
		}
		
		return ways;
	}
	
	// solution two
	public static double dp1(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		
		double all = Math.pow(M + 1, K);
		long[][] dp = new long[K + 1][N + 1];
		dp[0][0] = 1;
		
		for (int times = 1; times <= K; times++) {
			dp[times][0] = (long) Math.pow(M + 1, times);
			for (int hp = 1; hp <= N; hp++) {
				long ways = 0;
				
				for (int i = 0; i <= M; i++) {
					if (hp - i >= 0) {
						ways += dp[times - 1][hp - i];
					} else {
						ways += (long) Math.pow(M + 1, times - 1);
					}
				}
				
				dp[times][hp] = ways;
			}
		}
		
		long kill = dp[K][N];
		return (double) kill / all;
	}
	
	// solution two
	public static double dp2(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		
		double all = Math.pow(M + 1, K);
		long[][] dp = new long[K + 1][N + 1];
		dp[0][0] = 1;
		
		for (int times = 1; times <= K; times++) {
			dp[times][0] = (long) Math.pow(M + 1, times);
			for (int hp = 1; hp <= N; hp++) {
				dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
				if (hp - 1 - M >= 0) {
					dp[times][hp] -= dp[times - 1][hp - 1 - M];
				} else {
					dp[times][hp] -= Math.pow(M + 1, times - 1);
				}
			}
		}
		
		long kill = dp[K][N];
		return (double) kill / all;
	}
	
	// for test
	public static void test() {
		int nMax = 10;
		int mMax = 10;
		int kMax = 10;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int N = (int) (Math.random() * nMax);
			int M = (int) (Math.random() * mMax);
			int K = (int) (Math.random() * kMax);
			
			double ans1 = right(N, M, K);
			double ans2 = dp1(N, M, K);
			double ans3 = dp2(N, M, K);
			
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("ans1 = " + ans1 + ", ans2 = " + ans2 + ", ans3 = " + ans3);
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
