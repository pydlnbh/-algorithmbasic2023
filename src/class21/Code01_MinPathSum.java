package src.class21;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和 返回最小距离累加和
 */
public class Code01_MinPathSum {
	// solution one
	public static int minPathSum1(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		
		int row = m.length;
		int col = m[0].length;
		int[][] dp = new int[row + 1][col + 1];
		dp[0][0] = m[0][0];
		
		for (int i = 1; i < row; i++) {
			dp[i][0] = dp[i - 1][0] + m[i][0];
		}
		
		for (int j = 1; j < col; j++) {
			dp[0][j] = dp[0][j - 1] + m[0][j];
		}
		
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
			}
		}
		
		return dp[row - 1][col - 1];
	}
	
	// solution two
	public static int minPathSum2(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		
		int row = m.length;
		int col = m[0].length;
		int[] dp = new int[col];
		dp[0] = m[0][0];
		
		for (int i = 1; i < col; i++) {
			dp[i] = dp[i - 1] + m[0][i];
		}
		
		for (int i = 1; i < row; i++) {
			dp[0] += m[i][0];
			for (int j = 1; j < col; j++) {
				dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
			}
		}
		
		return dp[col - 1];
	}
	
	// for test
	public static int[][] generateRandomMartix(int rowSize, int colSize, int max) {
		if (rowSize < 0 || colSize < 0) {
			return null;
		}
		
		int[][] result = new int[rowSize + 1][colSize + 1];
		
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				result[i][j] = (int) (Math.random() * max) + 1;
			}
		}
		
		return result;
	}
	
	// for test
	public static void printMartix(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return;
		}
		
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// for test
	public static void test() {
		int max = 100;
		int rowSize = 10;
		int colSize = 10;
		int testTimes = 1000;
		
		System.out.println("start");
		for (int i = 0; i < testTimes; i++) {
			int[][] m = generateRandomMartix(rowSize, colSize, max);
			
			int ans1 = minPathSum1(m);
			int ans2 = minPathSum2(m);
			
			if (ans1 != ans2) {
				printMartix(m);
				System.out.println("ans1 = " + ans1 + ", ans2 = " + ans2);
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
