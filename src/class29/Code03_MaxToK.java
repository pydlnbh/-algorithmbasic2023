package src.class29;

import java.util.Arrays;

import src.Solution;

/**
 * 设计在无序数组中收集最大的前K个数字的算法（根据不同的三个时间复杂度，设计三个算法） 
 * 给定一个无序数组arr中，长度为N，给定一个正数k，返回top k个最大的数 
 * 不同时间复杂度三个方法： 
 * 1）O(N*logN) 
 * 2）O(N + K*logN) 
 * 3）O(n + k*logk)
 */
public class Code03_MaxToK {
	// 时间复杂度: O(N * logN)
	// 排序+收集
	public static int[] maxTopK1(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		
		int N = arr.length;
		k = Math.min(k, N);
		Arrays.sort(arr);
		int[] ans = new int[k];
		for (int i = N - 1, j = 0; j < k; i--, j++) {
			ans[j] = arr[i];
		}
		
		return ans;
	}
	
	// 时间复杂度: O(N + K * logN)
	// 解释: 堆
	public static int[] maxTopK2(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		
		int N = arr.length;
		k = Math.min(k, N);
		for (int i = N - 1; i >= 0; i--) {
			heapify(arr, i, N);
		}
		
		int heapSize = N;
		swap(arr, 0, --heapSize);
		int count = 1;
		
		while (heapSize > 0 && count < k) {
			heapify(arr, 0, heapSize);
			swap(arr, 0, --heapSize);
			count++;
		}
		
		int[] ans = new int[k];
		for (int i = N - 1, j = 0; j < k; i--, j++) {
			ans[j] = arr[i]; 
		}
		
		return ans;
	}
	
	public static void heapify(int[] arr, int index, int size) {
		int left = index * 2 + 1;
		while (left < size) {
			int best = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
			best = arr[best] > arr[index] ? best : index;
			
			if (best == index) {
				break;
			}
			
			swap(arr, best, index);
			index = best;
			left = index * 2 + 1;
		}
	}
	
	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	
	// 时间复杂度: O(n + k * logk)
	public static int[] maxTopK3(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		
		int N = arr.length;
		k = Math.min(k, N);
		int num = minKth(arr, N - k);
		int[] ans = new int[k];
		int index = 0;
		
		for (int i = 0; i < N; i++) {
			if (arr[i] > num) {
				ans[index++] = arr[i];
			}
		}
		
		for (; index < k; index++) {
			ans[index] = num;
		}
		 
		Arrays.sort(ans);
		
		for (int left = 0, right = k - 1; left < right; left++, right--) {
			swap(ans, left, right);
		}
		
		return ans;
	}
	
	public static int minKth(int[] arr, int index) {
		int left = 0;
		int right = arr.length - 1;
		int pivot = 0;
		int[] range = null;
		
		while (left < right) {
			pivot = arr[left + ((int) (Math.random() * (right - left + 1)))];
			range = partition(arr, left, right, pivot);
			
			if (index < range[0]) {
				right = range[0] - 1;
			} else if (index > range[1]) {
				left = range[1] + 1;
			} else {
				return pivot;
			}
		}
		
		return arr[left];
	}
	
	public static int[] copy(int[] arr) {
		int[] ans = new int[arr.length];
		
		for (int i = 0; i < ans.length; i++) {
			ans[i] = arr[i];
		}
		
		return ans;
	}
	
	public static int[] partition(int[] arr, int left, int right, int pivot) {
		int lessL = left - 1;
		int more = right + 1;
		int index = left;
		
		while (index < more) {
			if (arr[index] == pivot) {
				index++;
			} else if (arr[index] < pivot) {
				swap(arr, index++, ++lessL);
			} else {
				swap(arr, index, --more);
			}
		}
		
		return new int[] {lessL + 1, more - 1};
	}
	
	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) (Math.random() * maxSize)];
	
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
		}
		
		return arr;
	}
	
	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null || arr2 != null) && (arr1 != null || arr2 == null)) {
			return false;
		}
		
		if (arr1 == null && arr2 == null) {
			return true;
		}
		
		if (arr1.length != arr2.length) {
			return false;
		}
		
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	// for test
	public static void test() {
		int maxSize = 100;
		int maxValue = 1000;
		int testTimes = 10000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int k = (int) (Math.random() * arr.length) + 1;
			
			int[] arr1 = copy(arr);
			int[] arr2 = copy(arr);
			int[] arr3 = copy(arr);
			
			int[] ans1 = maxTopK1(arr1, k);
			int[] ans2 = maxTopK2(arr2, k);
			int[] ans3 = maxTopK3(arr3, k);
			
			if (isEqual(ans1, ans2) || isEqual(ans2, ans3)) {
				Solution.printArray(arr);
				Solution.printArray(ans1);
				Solution.printArray(ans2);
				Solution.printArray(ans3);
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
