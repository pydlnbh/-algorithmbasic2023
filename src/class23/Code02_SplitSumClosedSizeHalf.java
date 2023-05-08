package src.class23;

import src.Solution;

/**
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个 请尽量让两个集合的累加和接近 返回最接近的情况下，较小集合的累加和
 */
public class Code02_SplitSumClosedSizeHalf {
	// solution one
	public static int right(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}

		int sum = 0;
		for (int num : arr) {
			sum += num;
		}

		if ((arr.length & 1) == 0) {
			return process(arr, 0, arr.length / 2, sum / 2);
		} else {
			return Math.max(process(arr, 0, arr.length / 2, sum / 2), process(arr, 0, arr.length / 2 + 1, sum / 2));
		}
	}

	// recursive method
	public static int process(int[] arr, int index, int picks, int rest) {
		if (index == arr.length) {
			return picks == 0 ? 0 : -1;
		} else {
			int p1 = process(arr, index + 1, picks, rest);

			int p2 = -1;
			int next = -1;
			if (arr[index] <= rest) {
				next = process(arr, index + 1, picks - 1, rest - arr[index]);
			}
			if (next != -1) {
				p2 = arr[index] + next;
			}

			return Math.max(p1, p2);
		}
	}
	
	// dynamic program solution
	public static int dp(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		
		sum /= 2;
		int N = arr.length;
		int picks = (N + 1) / 2;
		
		int[][][] dp = new int[N + 1][picks + 1][sum + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= picks; j++) {
				for (int k = 0; k <= sum; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		
		for (int i = 0; i <= sum; i++) {
			dp[N][0][i] = 0;
		}
		
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j <= picks; j++) {
				for (int k = 0; k <= sum; k++) {
					int p1 = dp[i + 1][j][k];
					
					int p2 = -1;
					int next = -1;
					if (j - 1 >= 0 && arr[i] <= k) {
						next = dp[i + 1][j - 1][k - arr[i]];
					}
					
					if (next != -1) {
						p2 = arr[i] + next;
					}
					
					dp[i][j][k] = Math.max(p1, p2);
				}
			}
		}
		
		if ((N & 1) == 0) {
			return dp[0][N / 2][sum];
		} else {
			return Math.max(dp[0][N / 2][sum], dp[0][N / 2 + 1][sum]);
		}
	}

	// for test
	public static int[] generateRandomArray(int len, int max) {
		int length = (int) (Math.random() * len);
		int[] ans = new int[length + 1];
		for (int i = 0; i < length; i++) {
			ans[i] = (int) (Math.random() * max) + 1;
		}
		return ans;
	}

	// for test
	public static void test() {
		int len = 20;
		int max = 50;
		int testTimes = 1000;

		System.out.println("start");

		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(len, max);

			int ans1 = right(arr);
			int ans2 = dp(arr);

			if (ans1 != ans2) {
				Solution.printArray(arr);
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
