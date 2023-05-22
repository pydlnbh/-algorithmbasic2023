package src.class26;

/**
 * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
 * 如果某个字符串，任何0字符的左边都有1紧挨着，认为这个字符串达标 返回有多少达标的字符串
 */
public class Code04_ZeroLeftOneStringNumber {
	// solution one
	public static int getNum1(int n) {
		if (n < 1) {
			return 0;
		}
		
		return process(1, n);
	}
	
	public static int process(int i, int n) {
		if (i == n - 1) {
			return 2;
		}
		
		if (i == n) {
			return 1;
		}
		
		return process(i + 1, n) + process(i + 2, n);
	}
	
	// solution two
	public static int getNum2(int n) {
		if (n < 1) {
			return 0;
		}
		
		if (n == 1) {
			return 1;
		}
		
		int pre = 1;
		int cur = 1;
		int tmp = 0;
		
		for (int i = 2; i <= n; i++) {
			tmp = cur;
			cur += pre;
			pre = tmp;
		}
		
		return cur;
	}
	
	// solution three
	public static int getNum3(int n) {
		if (n < 1) {
			return 0;
		}
		
		if (n == 1 || n == 2) {
			return n;
		}
		
		int[][] base = {{1, 1}, {1, 0}};
		int[][] res = martixPower(base, n - 2);
		
		return 2 * res[0][0] + res[1][0]; 
	}
	
	// martix pow method
	public static int[][] martixPower(int[][] base, int p) {
		int[][] ans = new int[base.length][base[0].length];
		
		for (int i = 0; i < ans.length; i++) {
			ans[i][i] = 1;
		}
		
		int[][] tmp = base;
		
		for (; p != 0; p >>= 1) {
			if ((p & 1) != 0) {
				ans = product(ans, tmp);
			}
			
			tmp = product(tmp, tmp);
		}
		
		return ans;
	}
	
	public static int[][] product(int[][] a, int[][] b) {
		int n = a.length;
		int m = a[0].length;
		int k = b[0].length;
		
		int[][] ans = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int c = 0; c < k; c++) {
					ans[i][j] += a[i][c] * b[c][j];
				}
			}
		}
		
		return ans;
	}
	
	// main
	public static void main(String[] args) {
		int n = 5;
		
		int ans1 = getNum1(n);
		int ans2 = getNum2(n);
		int ans3 = getNum3(n);
		
		System.out.println(ans1);
		System.out.println(ans2);
		System.out.println(ans3);
	}
}
