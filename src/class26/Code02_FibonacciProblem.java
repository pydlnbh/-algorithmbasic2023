package src.class26;

// 斐波那契数列矩阵乘法方式的实现
public class Code02_FibonacciProblem {
	// solution one
	public static int f1(int n) {
		if (n < 1) {
			return 0;
		}
		
		if (n == 1 || n == 2) {
			return 1;
		}
		
		return f1(n - 1) + f1(n - 2);
	}
	
	// solution two
	public static int f2(int n) {
		if (n < 1) {
			return 0;
		}
		
		if (n == 1 || n == 2) {
			return 1;
		}
		
		int res = 1;
		int pre = 1;
		int tmp = 0;
		
		for (int i = 3; i <= n; i++) {
			tmp = res;
			res = res + pre;
			pre = tmp;
		}
		
		return res;
	}
	
	// solution three
	public static int f3(int n) {
		if (n < 1) {
			return 0;
		}
		
		if (n == 1 || n == 2) {
			return 1;
		}
		
		int[][] base = {{1, 1}, {1, 0}};
		
		int[][] res = martixPower(base, n - 2);
		
		return res[0][0] + res[1][0]; 
	}
	
	public static int[][] martixPower(int[][] m, int p) {
		int[][] res = new int[m.length][m[0].length];
		for (int i = 0; i < res.length; i++) {
			res[i][i] = 1;
		}
		
		int[][] t = m;
		for (; p != 0; p >>= 1) {
			if ((p & 1) != 0) {
				res = product(res, t);
			}
			
			t = product(t, t);
		}
		
		return res;
	}
	
	public static int[][] product(int[][] a, int[][] b) {
		int n = a.length;
		int m = b[0].length;
		int k = a[0].length;
		
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

	public static void main(String[] args) {
		int n = 4;
		int ans1 = f1(n);
		int ans2 = f2(n);
		int ans3 = f3(n);
		
		System.out.println(ans1);
		System.out.println(ans2);
		System.out.println(ans3);
	}

}
