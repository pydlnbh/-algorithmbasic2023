package src.class18;

/**
 * 假设有排成一行的N个位置记为1~N，N一定大于或等于2 开始时机器人在其中的M位置上(M一定是1~N中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置； 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走； 
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种 给定四个参数
 * N、M、K、P，返回方法数
 */
public class Code01_RobotWalk {
	// solution one
	public static int way1(int N, int M, int K, int P) {
		if (N < 2 
	        || M < 1 
		    || M > N 
		    || P < 1 
		    || P > N 
		    || K < 1) {
			return -1;
		}
		
		return process1(N, M, K, P);
	}
	
	// recursive method one
	public static int process1(int N, int M, int K, int P) {
		if (K == 0) {
			return M == P ? 1 : 0;
		}
		
		if (M == 1) {
			return process1(N, 2, K - 1, P);
		}
		
		if (M == N) {
			return process1(N, N - 1, K - 1, P);
		}
		
		return process1(N, M - 1, K - 1, P)
		    + process1(N, M + 1, K - 1, P);
	}
	
	// solution two
	public static int way2(int N, int M, int K, int P) {
		if (N < 2
		    || K < 1
		    || M < 1
		    || M > N
		    || P < 1
		    || P > N) {
			return -1;
		}
		
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				dp[i][j] = -1;
			}
		}
		
		return process2(N, M, K, P, dp);
	}
	
	// recursive method two
	public static int process2(int N, int cur, int rest, int aim, int[][] dp) {
		if (dp[cur][rest] != -1) {
			return dp[cur][rest];
		}
		
		int ans = 0;
		if (rest == 0) {
			ans = cur == aim ? 1 : 0;
		} else if (cur == 1) {
			ans = process2(N, 2, rest - 1, aim, dp);
		} else if (cur == N) {
			ans = process2(N, cur - 1, rest - 1, aim, dp);
		} else {
			ans = process2(N, cur - 1, rest - 1, aim, dp)
			    + process2(N, cur + 1, rest - 1, aim, dp);
		}
		
		dp[cur][rest] = ans;
		return ans;
	}
	
	// solution three
	public static int way3(int N, int start, int K, int aim) {
		if (N < 2
		    || K < 1
		    || start < 1
		    || start > N
		    || aim < 1
		    || aim > N) {
			return -1;
		}
		
		int[][] dp = new int[N + 1][K + 1];
		dp[aim][0] = 1;
		
		for (int rest = 1; rest <= K; rest++) {
			dp[1][rest] = dp[2][rest - 1];
			
			for (int cur = 2; cur <= N - 1; cur++) {
				dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
			}
			
			dp[N][rest] = dp[N - 1][rest - 1];
		}
		
		return dp[start][K];
	}
	
	// main
	public static void main(String[] args) {
		int ans1 = way1(5, 2, 6, 4);
		System.out.println(ans1);
		
		System.out.println("---");
		
		int ans2 = way2(5, 2, 6, 4);
		System.out.println(ans2);
		
		System.out.println("---");
		
		int ans3 = way3(5, 2, 6, 4);
		System.out.println(ans3);
	}
}
