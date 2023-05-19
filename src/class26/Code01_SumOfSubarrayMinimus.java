package src.class26;

import src.Solution;

// 给定一个数组arr，返回所有子数组最小值的累加和
// https://leetcode.com/problems/sum-of-subarray-minimums/
public class Code01_SumOfSubarrayMinimus {
	// solution one
	public static int subArrayMinSum1(int[] arr) {
		int ans = 0;
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				int min = arr[i];
				
				for (int k = i + 1; k <= j; k++) {
					min = Math.min(min, arr[k]);
				}
				
				ans += min;
			}
		}
		
		return ans;
	}
	
	// solution two
	public static int subArrayMinSum2(int[] arr) {
		int[] left = leftNearLessEqual2(arr);
		
		int[] right = rightNearLess2(arr);
		
		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			int start = i - left[i];
			int end = right[i] - i;
			ans += start * end * arr[i];
		}
		
		return ans;
	}
	
	public static int[] leftNearLessEqual2(int[] arr) {
		int N = arr.length;
		int[] left = new int[N];
		
		for (int i = 0; i < N; i++) {
			int ans = -1;
			
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] <= arr[i]) {
					ans = j;
					break;
				}
			}
			left[i] = ans;
		}
		
		return left;
	}
	
	public static int[] rightNearLess2(int[] arr) {
		int N = arr.length;
		int[] right = new int[N];
		
		for (int i = 0; i < N; i++) {
			int ans = N;
			
			for (int j = i + 1; j < N; j++) {
				if (arr[i] > arr[j]) {
					ans = j;
					break;
				}
			}
			
			right[i] = ans;
		}
		
		return right;
	}
	
	// for test
	public static int sumSubarrayMins(int[] arr) {
		int[] stack = new int[arr.length];
		int[] left = nearLessEqualLeft(arr, stack);
		int[] right = nearLessRight(arr, stack);
		long ans = 0;
		
		for (int i = 0; i < arr.length; i++) {
			long start = i - left[i];
			long end = right[i] - i;
			ans += start * end * (long) arr[i];
			ans %= 1000000007;
		}
		
		return (int) ans;
	}
	
	public static int[] nearLessEqualLeft(int[] arr, int[] stack) {
		int N = arr.length;
		int[] left = new int[N];
		int size = 0;
		
		for (int i = N - 1; i >= 0; i--) {
			while (size != 0 && arr[i] <= arr[stack[size - 1]]) {
				left[stack[--size]] = i;
			}
			stack[size++] = i;
		}
		
		while (size != 0) {
			left[stack[--size]] = -1;
		}
		
		return left;
	}
	
	public static int[] nearLessRight(int[] arr, int[] stack) {
		int N = arr.length;
		int[] right = new int[N];
		int size = 0;
		
		for (int i = 0; i < N; i++) {
			while (size != 0 && arr[stack[size - 1]] > arr[i]) {
				right[stack[--size]] = i;
			}
			
			stack[size++] = i;
		}
		
		while (size != 0) {
			right[stack[--size]] = N;
		}
		
		return right;
	}
	
	// for test
	public static int[] generateRandomArray(int maxLen, int maxValue) {
		int[] ans = new int[(int) (Math.random() * maxLen) + 1];
		
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int)(Math.random() * maxValue);
		}
		
		return ans;
	}
	
	// for test
	public static void test() {
		int maxLen = 100;
		int maxValue = 100;
		int testTimes = 100000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxLen, maxValue);
			
			int ans1 = subArrayMinSum1(arr);
			int ans2 = subArrayMinSum2(arr);
			int ans3 = sumSubarrayMins(arr);
			
			if (ans1 != ans2 || ans1 != ans3) {
				Solution.printArray(arr);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println(ans3);
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
