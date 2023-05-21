package src.class26;

/**
 * 奶牛生小牛问题 第一年农场有1只成熟的母牛A，
 * 往后的每年： 
 * 1）每一只成熟的母牛都会生一只母牛 
 * 2）每一只新出生的母牛都在出生的第三年成熟
 * 3）每一只母牛永远不会死 返回N年后牛的数量
 */
public class Code03_NYearsCowNumber {
	// solution one
	public static int f1(int n) {
		if (n < 1) {
			return 0;
		}
		
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		
		return f1(n - 1) + f1(n - 3);
	}
	
	// solution two
	public static int f2(int n) {
		if (n < 1) {
			return 0;
		}
		
		if (n == 1 || n == 2) {
			return n;
		}
		
		int res = 3;
		int pre = 2;
		int prepre = 1;
		int tmp1 = 0;
		int tmp2 = 0;
		
		for (int i = 4; i <= n; i++) {
			tmp1 = res;
			tmp2 = pre;
			res = res + prepre;
			pre = tmp1;
			prepre = tmp2;
		}
		
		return res;
	}
	
	// solution three
	public static int f3(int n) {
		if (n < 1) {
			return 0;
		}
		
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		
		int[][] base = {{1, 1, 0},
				        {0, 0, 1},
				        {1, 0, 0}};
		
		int[][] res = martixPower(base, n - 3);
		
		return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
	}
	
	public static int[][] martixPower(int[][] base, int p) {	
		int[][] ans = new int[base.length][base[0].length];
		
		for (int i = 0; i < base.length; i++) {
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
		int n = 19;
		
		int ans1 = f1(n);
		int ans2 = f2(n);
		int ans3 = f3(n);
		
		System.out.println(ans1);
		System.out.println(ans2);
		System.out.println(ans3);
	}
}
