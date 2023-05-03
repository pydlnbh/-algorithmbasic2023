package src.class21;

/**
 * 给定5个参数，N，M，row，col，k 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class Code05_BobDie {
	// solution one
	public static double livePosibility1(int row, int col, int k, int n, int m) {
		return (double) process(row, col, k, n, m) / Math.pow(4, k);
	}
	
	// recursive method
	public static int process(int row, int col, int k, int n, int m) {
		if (row < 0 || row >= n || col < 0 || col >= m) {
			return 0;
		}
		
		if (k == 0) {
			return 1;
		}
		
		int up = process(row - 1, col, k - 1, n, m);
		int down = process(row + 1,  col, k - 1, n, m);
		int left = process(row, col - 1, k - 1, n, m);
		int right = process(row, col + 1, k - 1, n, m);
		
		return up + down + left + right;
	}
	
	// solution two
	public static double livePosibility2(int row, int col, int k, int n, int m) {
		long[][][] dp = new long[n][m][k + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dp[i][j][0] = 1;
			}
		}
		
		for (int rest = 1; rest <= k; rest++) {
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < m; c++) {
					dp[r][c][rest] = pick(dp, r - 1, c, rest - 1, n, m);
					dp[r][c][rest] += pick(dp, r + 1, c, rest - 1, n, m);
					dp[r][c][rest] += pick(dp, r, c - 1, rest - 1, n, m);
					dp[r][c][rest] += pick(dp, r, c + 1, rest - 1, n, m);
				}
			}
		}
		
		return (double) dp[row][col][k] / Math.pow(4, k);
	}
	
	// method
	public static long pick(long[][][] dp, int row, int col, int k, int n, int m) {
		if (row < 0 || row >= n || col < 0 || col >= m) {
			return 0;
		}
		
		return dp[row][col][k];
	}
	
	// main
	public static void main(String[] args) {
		System.out.println(livePosibility1(6, 6, 10, 50, 50));
		System.out.println(livePosibility2(6, 6, 10, 50, 50));
	}
}
