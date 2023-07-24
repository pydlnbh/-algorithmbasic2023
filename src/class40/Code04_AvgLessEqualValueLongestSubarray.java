package src.class40;

import java.util.TreeMap;

// 给定一个数组arr，给定一个值v，求子数组平均值小于等于v的最长子数组长度
public class Code04_AvgLessEqualValueLongestSubarray {
	// solution one
	public static int ways1(int[] arr, int v) {
		int ans = 0;

		for (int L = 0; L < arr.length; L++) {
			for (int R = L; R < arr.length; R++) {
				int sum = 0;
				int k = R - L + 1;

				for (int i = L; i <= R; i++) {
					sum += arr[i];
				}

				double avg = (double) sum / (double) k;

				if (avg <= v) {
					ans = Math.max(ans, k);
				}
			}
		}

		return ans;
	}

	// solution two
	public static int ways2(int[] arr, int v) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		for (int i = 0; i < arr.length; i++) {
			arr[i] -= v;
		}

		TreeMap<Integer, Integer> sortedMap = new TreeMap<>();
		sortedMap.put(0, -1);
		int sum = 0;
		int len = 0;

		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			Integer ceiling = sortedMap.ceilingKey(sum);
			if (ceiling != null) {
				len = Math.max(len, i - sortedMap.get(ceiling));
			} else {
				sortedMap.put(sum, i);
			}
		}

		return len;
	}

	// solution three
	public static int ways3(int[] arr, int v) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		for (int i = 0; i < arr.length; i++) {
			arr[i] -= v;
		}

		return maxLengthAwesome(arr, 0);
	}

	public static int maxLengthAwesome(int[] arr, int k) {
		int N = arr.length;
		int[] sums = new int[N];
		int[] ends = new int[N];
		sums[N - 1] = arr[N - 1];
		ends[N - 1] = N - 1;

		for (int i = N - 2; i >= 0; i--) {
			if (sums[i + 1] < 0) {
				sums[i] = arr[i] + sums[i + 1];
				ends[i] = ends[i + 1];
			} else {
				sums[i] = arr[i];
				ends[i] = i;
			}
		}

		int end = 0;
		int sum = 0;
		int ans = 0;

		for (int i = 0; i < N; i++) {
			while (end < N && sum + sums[end] <= k) {
				sum += sums[end];
				end = ends[end] + 1;
			}

			ans = Math.max(ans, end - i);

			if (end > i) {
				sum -= arr[i];
			} else {
				end = i + 1;
			}
		}

		return ans;
	}

	// for test
	public static int[] randomArray(int maxLen, int maxValue) {
		int len = (int) (Math.random() * maxLen) + 1;
		int[] ans = new int[len];
		for (int i = 0; i < len; i++) {
			ans[i] = (int) (Math.random() * maxValue);
		}
		return ans;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			ans[i] = arr[i];
		}
		return ans;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// main
	public static void main(String[] args) {
		System.out.println("测试开始");
		int maxLen = 20;
		int maxValue = 100;
		int testTime = 500000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(maxLen, maxValue);
			int value = (int) (Math.random() * maxValue);
			int[] arr1 = copyArray(arr);
			int[] arr2 = copyArray(arr);
			int[] arr3 = copyArray(arr);
			int ans1 = ways1(arr1, value);
			int ans2 = ways2(arr2, value);
			int ans3 = ways3(arr3, value);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("测试出错！");
				System.out.print("测试数组：");
				printArray(arr);
				System.out.println("子数组平均值不小于 ：" + value);
				System.out.println("方法1得到的最大长度：" + ans1);
				System.out.println("方法2得到的最大长度：" + ans2);
				System.out.println("方法3得到的最大长度：" + ans3);
				System.out.println("=========================");
			}
		}
		System.out.println("测试结束");
	}
}
