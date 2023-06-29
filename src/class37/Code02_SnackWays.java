package src.class37;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]，背包容量为w， 牛牛想知道在总体积不超过背包容量的情况下, 一共有多少种零食放法，体积为0也算一种放法
 * 1 <= n <= 30, 1 <= w <= 2 * 10^9，v[I] (0 <= v[i] <= 10^9）
 */
public class Code02_SnackWays {
	// solution one
	public static int ways1(int[] arr, int w) {
		return process1(arr, 0, w);
	}

	public static int process1(int[] arr, int index, int rest) {
		if (rest < 0) {
			return -1;
		}

		if (index == arr.length) {
			return 1;
		}

		int next1 = process1(arr, index + 1, rest);
		int next2 = process1(arr, index + 1, rest - arr[index]);

		return next1 + (next2 == -1 ? 0 : next2);
	}

	// solution two
	public static int ways2(int[] arr, int w) {
		int N = arr.length;
		int[][] dp = new int[N + 1][w + 1];
		for (int j = 0; j <= w; j++) {
			dp[N][j] = 1;
		}

		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j <= w; j++) {
				dp[i][j] = dp[i + 1][j] + ((j - arr[i]) >= 0 ? dp[i + 1][j - arr[i]] : 0);
			}
		}

		return dp[0][w];
	}

	// solution three
	public static int ways3(int[] arr, int w) {
		int N = arr.length;
		int[][] dp = new int[N][w + 1];
		for (int i = 0; i < N; i++) {
			dp[i][0] = 1;
		}

		if (arr[0] <= w) {
			dp[0][arr[0]] = 1;
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= w; j++) {
				dp[i][j] = dp[i - 1][j] + ((j - arr[i]) >= 0 ? dp[i - 1][j - arr[i]] : 0);
			}
		}

		int ans = 0;
		for (int j = 0; j <= w; j++) {
			ans += dp[N - 1][j];
		}

		return ans;
	}

	// solution one two three for test
	public static void test1() {
		int[] arr = { 4, 3, 2, 9 };
		int w = 8;

		System.out.println(ways1(arr, w));
		System.out.println(ways2(arr, w));
		System.out.println(ways3(arr, w));
	}

	// solution four
	public static long ways4(int[] arr, int bag) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		if (arr.length == 1) {
			return arr[0] <= bag ? 2 : 1;
		}

		int mid = (arr.length - 1) >> 1;
		TreeMap<Long, Long> lmap = new TreeMap<>();
		long ways = process4(arr, 0, 0, mid, bag, lmap);
		TreeMap<Long, Long> rmap = new TreeMap<>();
		ways += process4(arr, 0, mid + 1, arr.length - 1, bag, rmap);
		TreeMap<Long, Long> rpre = new TreeMap<>();
		long pre = 0;

		for (Entry<Long, Long> entry : rmap.entrySet()) {
			pre += entry.getValue();
			rpre.put(entry.getKey(), pre);
		}

		for (Entry<Long, Long> entry : lmap.entrySet()) {
			long lweight = entry.getKey();
			long lways = entry.getValue();
			Long floor = rpre.floorKey(bag - lweight);

			if (floor != null) {
				long rways = rpre.get(floor);
				ways += lways * rways;
			}
		}

		return ways + 1;
	}

	public static long process4(int[] arr, long sum, int index, int end, int bag, TreeMap<Long, Long> map) {
		if (sum > bag) {
			return 0;
		}

		// base case 走到最后一步了
		if (index > end) {
			if (sum != 0) {
				if (!map.containsKey(sum)) {
					map.put(sum, 1L);
				} else {
					map.put(sum, map.get(sum) + 1);
				}

				return 1;
			} else {
				return 0;
			}
		}

		// 不是最后一步
		// 1. 不要当前的位置
		long ways = process4(arr, sum, index + 1, end, bag, map);
		// 2. 要当前的位置
		ways += process4(arr, sum + arr[index], index + 1, end, bag, map);
		return ways;
	}

	// solution four for test
	public static void test2() throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.nval;
			in.nextToken();
			int bag = (int) in.nval;
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				in.nextToken();
				arr[i] = (int) in.nval;
			}

			long ways = ways4(arr, bag);
			out.println(ways);
			out.flush();
		}
	}

	// solution five
	public static long[] arr = new long[31];
	public static int size = 0;
	public static long[] leftSum = new long[1 << 16];
	public static int leftSize = 0;
	public static long[] rightSum = new long[1 << 16];
	public static int rightSize = 0;

	public static long ways5(int bag) {
		if (size == 0) {
			return 0;
		}

		if (size == 1) {
			return arr[0] <= bag ? 2 : 1;
		}

		int mid = size >> 1;
		leftSize = 0;
		dfsLeft(0, mid + 1, 0L);

		rightSize = 0;
		dfsRight(mid + 1, size, 0L);

		Arrays.sort(leftSum, 0, leftSize);
		Arrays.sort(rightSum, 0, rightSize);

		long ans = 0;
		long count = 1;

		for (int i = 1; i < leftSize; i++) {
			if (leftSum[i] != leftSum[i - 1]) {
				ans += count * (long) find(bag - leftSum[i - 1]);
				count = 1;
			} else {
				count++;
			}
		}

		ans += count * (long) find(bag - leftSum[leftSize - 1]);

		return ans;
	}

	public static void dfsLeft(int cur, int end, long sum) {
		if (cur == end) {
			leftSum[leftSize++] = sum;
		} else {
			dfsLeft(cur + 1, end, sum);
			dfsLeft(cur + 1, end, sum + arr[cur]);
		}
	}

	public static void dfsRight(int cur, int end, long sum) {
		if (cur == end) {
			rightSum[rightSize++] = sum;
		} else {
			dfsRight(cur + 1, end, sum);
			dfsRight(cur + 1, end, sum + arr[cur]);
		}
	}

	public static int find(long num) {
		int ans = -1;
		int l = 0;
		int r = rightSize - 1;
		int m = 0;

		while (l <= r) {
			m = (l + r) >> 1;
			if (rightSum[m] <= num) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}

		return ans + 1;
	}

	// solution five for test
	public static void test3() throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			size = (int) in.nval;
			in.nextToken();
			int bag = (int) in.nval;

			for (int i = 0; i < size; i++) {
				in.nextToken();
				arr[i] = (int) in.nval;
			}

			long ways = ways5(bag);
			out.println(ways);
			out.flush();
		}
	}

	// main
	public static void main(String[] args) {
		test1();
	}
}
