package src.class38;

import java.util.HashMap;

// 给定一个整数组成的无序数组arr，值可能正、可能负、可能0，给定一个整数值K
// 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的，返回其长度
public class Code02_LongestSumSubArrayLength {
	// solution one
	public static int maxLength(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		int len = 0;
		int sum = 0;

		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (map.containsKey(sum - k)) {
				len = Math.max(i - map.get(sum - k), len);
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}

		return len;
	}

	// for test
	public static int right(int[] arr, int k) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				if (valid(arr, i, j, k)) {
					max = Math.max(max, j - i + 1);
				}
			}
		}
		return max;
	}

	// for test
	public static boolean valid(int[] arr, int i, int j, int k) {
		int sum = 0;
		for (int c = i; c <= j; c++) {
			sum += arr[c];
		}
		return sum == k;
	}

	// for test
	public static int[] generateRandomArray(int size, int value) {
		int[] ans = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
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

	// for test
	public static void test() {
		int len = 50;
		int value = 100;
		int testTimes = 100000;

		System.out.println("start");

		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(len, value);
			int k = (int) (Math.random() * value) - (int) (Math.random() * value);
			int ans1 = maxLength(arr, k);
			int ans2 = right(arr, k);

			if (ans1 != ans2) {
				System.out.println("Oops");
				printArray(arr);
				System.out.println("k = " + k);
				System.out.println(ans1);
				System.out.println(ans2);
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
