package src.class23;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后， 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 
 * 给定一个整数n，返回n皇后的摆法有多少种。 n=1，返回1 n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0 n=8，返回92
 */
public class Code03_NQueues {
	// solution one
	public static int nums1(int n) {
		if (n < 1) {
			return 0;
		}
		
		int[] record = new int[n];
		
		return process1(0, record, n);
	}
	
	// recursive method one
	public static int process1(int i, int[] record, int n) {
		if (i == n) {
			return 1;
		}
		
		int ans = 0;
		for (int j = 0; j < n; j++) {
			if (isValid(record, i, j)) {
				record[i] = j;
				ans += process1(i + 1, record, n);
			}
		}
		
		return ans;
	}
	
	// auxiliary method
	public static boolean isValid(int[] record, int i, int j) {
		for (int k = 0; k < i; k++) {
			if (j == record[k] || Math.abs(record[k] - j) == Math.abs(k - i)) {
				return false;
			}
		}
		
		return true;
	}
	
	// solution two
	public static int nums2(int n) {
		if (n < 1 || n > 32) {
			return 0;
		}
		
		int limit = n == 32 ? -1 : (1 << n) - 1;
		
		return process2(limit, 0, 0, 0);
	}
	
	// recursive method two
	public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
		if (colLim == limit) {
			return 1;
		}
		
		int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
		int rightOne = 0;
		int ans = 0;
		
		while (pos != 0) {
			rightOne = pos & (~pos + 1);
			pos = pos - rightOne;
			ans += process2(limit, 
			    colLim | rightOne,
			    (leftDiaLim | rightOne) << 1,
			    (rightDiaLim | rightOne) >>> 1);
		}
		
		return ans;
	}
	
	// for test
	public static void test() {
		int n = 5;
		
		System.out.println(nums1(n));
		System.out.println(nums2(n));
	}

	// main
	public static void main(String[] args) {
		test();
	}
}
